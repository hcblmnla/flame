package flame.image;

import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * {@link Image} utilities class.
 *
 * @author alnmlbch
 */
@UtilityClass
public class ImageUtils {

    /**
     * Builds the generated image name.
     *
     * @param filename name from user
     * @param index    unused index of generated file
     * @param format   image extension
     * @return built image name
     */
    public static String imageName(final String filename, final int index, final ImageFormat format) {
        return "%s_generated%d.%s".formatted(filename, index, format.extension());
    }

    private static IntFunction<Path> pathResolver(final Path path, final String filename, final ImageFormat format) {
        return index -> path.resolve(imageName(filename, index, format));
    }

    private static int findFirstUnusedIndex(final Path path, final String filename, final ImageFormat format) {
        var resolver = pathResolver(path, filename, format);

        return Stream.iterate(1, index -> index + 1)
            .filter(index -> !Files.exists(resolver.apply(index)))
            .findFirst()
            .orElse(1);
    }

    /**
     * Saves {@link Image} in {@code path} directory
     * with {@code filename} name and {@link ImageFormat} extension.
     *
     * @param image    an image to be saved
     * @param path     a path to be saved to
     * @param filename image name
     * @param format   image format
     * @return {@code true}, if image was saved, else {@code false}
     * @throws IOException if an error occurs during saving image
     */
    public static boolean saveImage(
        final Image<?> image,
        final Path path,
        final String filename,
        final ImageFormat format
    ) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        if (!Files.isDirectory(path)) {
            return false;
        }
        int index = findFirstUnusedIndex(path, filename, format);
        var file = pathResolver(path, filename, format)
            .apply(index)
            .toFile();
        return ImageIO.write(image.toBufferedImage(), format.extension(), file);
    }
}
