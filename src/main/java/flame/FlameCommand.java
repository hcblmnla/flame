package flame;

import flame.algorithm.generator.MultiThreadGenerator;
import flame.algorithm.generator.SingleThreadGenerator;
import flame.algorithm.processor.LogGammaCorrection;
import flame.function.UnknownVariationException;
import flame.function.VariationSelector;
import flame.function.affine.Affine;
import flame.image.ColoredPixel;
import flame.image.ImageFormat;
import flame.image.ImageUtils;
import flame.image.resolution.Resolution;
import flame.util.UnknownArgumentException;
import org.pmw.tinylog.Logger;
import picocli.CommandLine;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Command-line interface for generating fractal flame images.
 *
 * @author alnmlbch
 */
@CommandLine.Command(name = "flame", mixinStandardHelpOptions = true)
public class FlameCommand implements Callable<Void> {

    @CommandLine.Option(names = {"-r", "--resolution"}, defaultValue = "fhd")
    String resolution;

    @CommandLine.Option(names = {"-v", "--variations"}, arity = "1..*", defaultValue = "spherical")
    List<String> variations;

    @CommandLine.Option(names = {"-s", "--samples"}, defaultValue = "1000")
    String samples;

    @CommandLine.Option(names = {"-i", "--iterations"}, defaultValue = "100000")
    String iterations;

    @CommandLine.Option(names = {"-sym", "--symmetries"}, defaultValue = "1")
    String symmetries;

    @CommandLine.Option(names = {"-t", "--nThreads"}, defaultValue = "8")
    String nThreads;

    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "png")
    String extension;

    /**
     * Executes the fractal flame image generation process with the provided arguments.
     *
     * @param writer     logging writer
     * @param resolution image resolution
     * @param variations user's list of strings names
     * @param samples    point count
     * @param iterations iterations count
     * @param symmetries symmetries count
     * @param nThreads   threads count
     * @param format     image format (with extension)
     * @throws UnknownVariationException if variations contains unknown name
     * @throws IOException               if I/O exception occurred
     * @throws InterruptedException      if multithreading exception occurred
     */
    private static void callWithArguments(
        final Writer writer,
        final Resolution resolution,
        final List<String> variations,
        final int samples,
        final int iterations,
        final int symmetries,
        final int nThreads,
        final ImageFormat format
    ) throws UnknownVariationException, IOException, InterruptedException {
        var random = ThreadLocalRandom.current();
        var generator = (nThreads == 1 ? SingleThreadGenerator.builder() : MultiThreadGenerator.builder())

            .width(resolution.width())
            .height(resolution.height())
            .compression(resolution.compression())

            .processor(new LogGammaCorrection())
            .background(ColoredPixel.DEFAULT_COLOR)
            .random(random)
            .nThreads(nThreads)
            .build();

        var affine = Affine.defaults(random);
        var sj = new StringJoiner("-");
        var vs = variations.stream()
            .peek(sj::add)
            .map(v -> VariationSelector.select(v, random))
            .toList();

        var filename = "%s-%d-%d".formatted(sj.toString(), samples, iterations);
        log(writer, "Generating %s in %d thread(s)", filename, nThreads);

        long start = System.nanoTime();
        var image = generator.generate(affine, vs, samples, iterations, symmetries);

        long time = System.nanoTime() - start;
        var path = Path.of("generated");
        log(writer, "Generating time is %.2f seconds", time / 1e9);

        try {
            if (ImageUtils.saveImage(image, path, filename, format)) {
                log(writer, "Image saved to /%s/", path);
            } else {
                log(writer, "Could not save image to /%s/", path);
            }
        } catch (IOException e) {
            log(writer, "Image saving was cancelled, reason: %s", e.getMessage());
        }
    }

    private static void log(
        final Writer writer,
        final String message,
        final Object... args
    ) throws IOException {
        writer.write("> %s%n".formatted(message).formatted(args));
        writer.flush();
    }

    @Override
    public Void call() {
        try (var writer = new BufferedWriter(new OutputStreamWriter(
            System.err, StandardCharsets.UTF_8
        ))) {
            try {
                callWithArguments(
                    writer,
                    Resolution.of(resolution),
                    variations,
                    Integer.parseInt(samples),
                    Integer.parseInt(iterations),
                    Integer.parseInt(symmetries),
                    Integer.parseInt(nThreads),
                    ImageFormat.valueOf(extension.toUpperCase())
                );
            } catch (final NumberFormatException e) {
                log(writer, "Could not parse number parameters");
            } catch (final InterruptedException e) {
                log(writer, "Multi-thread exception occurred");
            } catch (final UnknownArgumentException | IllegalArgumentException e) {
                log(writer, e.getMessage());
            }
        } catch (final IOException e) {
            Logger.error("Critical I/O exception occurred, reason: {}", e.getMessage());
        }
        return null;
    }
}
