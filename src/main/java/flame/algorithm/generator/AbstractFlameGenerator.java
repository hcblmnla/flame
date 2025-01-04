package flame.algorithm.generator;

import flame.algorithm.processor.ImageProcessor;
import flame.function.Isomorphism;
import flame.function.Variation;
import flame.function.affine.Affine;
import flame.image.ColoredPixel;
import flame.image.Image;
import flame.util.ExtendedRandom;
import lombok.experimental.SuperBuilder;

import java.awt.*;
import java.util.List;
import java.util.Random;

@SuperBuilder
public abstract sealed class AbstractFlameGenerator
    implements Generator<ColoredPixel, Affine>
    permits SingleThreadGenerator, MultiThreadGenerator {

    private static final int THRESHOLD = 20;

    protected final int width;
    protected final int height;
    protected final int compression;

    protected final Color background;
    protected final int nThreads;

    private final ImageProcessor<ColoredPixel> processor;
    private final Random random;

    public static ColoredPixel updateColor(final Affine affine, final ColoredPixel pixel) {
        var color = affine.color();
        if (pixel.hitCount() == 0) {
            return pixel.color(color);
        }

        return pixel.color(
            (pixel.red() + color.getRed()) / 2,
            (pixel.green() + color.getGreen()) / 2,
            (pixel.blue() + color.getBlue()) / 2
        );
    }

    protected abstract void handleSamples(int samples, Runnable sampler)
        throws InterruptedException;

    protected abstract Image<ColoredPixel> empty();

    @Override
    public Image<ColoredPixel> generate(
        final List<Affine> linear,
        final List<Variation<Affine>> nonLinear,
        final int samples,
        final int iterations,
        final int symmetries
    ) throws InterruptedException {
        var image = empty();
        var isomorphism = Isomorphism.biUnit(width, height);

        handleSamples(samples, () -> sample(
            linear,
            nonLinear,
            iterations,
            symmetries,
            image,
            isomorphism
        ));

        processor.accept(image);
        return image;
    }

    private void sample(
        final List<Affine> linear,
        final List<Variation<Affine>> nonLinear,
        final int iterations,
        final int symmetries,
        final Image<ColoredPixel> image,
        final Isomorphism isomorphism
    ) {
        var point = isomorphism.randomUnitPoint(random);
        for (int iteration = -THRESHOLD; iteration < iterations; iteration++) {
            var affine = ExtendedRandom.element(linear, random);
            point = ExtendedRandom
                .element(nonLinear, random)
                .apply(affine.apply(point), affine);

            if (iteration < 0) {
                continue;
            }
            double angle = 0;
            for (int sym = 0; sym < symmetries; sym++, angle += Math.PI * 2 / symmetries) {
                var rotated = isomorphism.deUnitPoint(
                    point.rotated(angle),
                    width * compression,
                    height * compression
                );

                int x = rotated.x();
                int y = rotated.y();
                if (!image.contains(x, y)) {
                    continue;
                }

                var pixel = image.pixel(x, y);
                image.pixel(x, y, updateColor(affine, pixel).hit());
            }
        }
    }
}
