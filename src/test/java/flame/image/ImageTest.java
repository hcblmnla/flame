package flame.image;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTest {

    private static Stream<ImageCtor<ColoredPixel>> provideImageCtors() {
        return Stream.of(
            ColoredImage::new,
            AtomicColoredImage::new
        );
    }

    @ParameterizedTest
    @MethodSource("provideImageCtors")
    void imageShouldContainsCorrectImage(final ImageCtor<ColoredPixel> ctor) {
        // Given
        int width = 100;
        int height = 60;
        var background = Color.BLACK;
        int compression = 7;

        // When
        var image = ctor.apply(width, height, compression, background);

        // Then
        assertThat(image.width() / width)
            .isEqualTo(image.height() / height)
            .isEqualTo(compression);

        image.forEach((x, y) -> assertThat(image.pixel(x, y).get())
            .isEqualTo(background));

        for (int y = 0; y < height * compression; y++) {
            for (int x = 0; x < width * compression; x++) {
                assertThat(image.contains(x, y))
                    .isTrue();
            }
        }

        assertThat(image.contains(-1, 0))
            .isEqualTo(image.contains(-1, -1))
            .isEqualTo(image.contains(1, -1))
            .isEqualTo(image.contains(Integer.MAX_VALUE, 10))
            .isEqualTo(image.contains(10, 1_000_000))
            .isFalse();

        // When
        image.pixel(0, 0, new ColoredPixel(1, 1, 1, 10));
        image.pixel(699, 419, new ColoredPixel(2, 2, 2, 11));

        var buffered = image.toBufferedImage();

        // Then
        assertThat(image.max(Comparator.comparing(ColoredPixel::hitCount)).hitCount())
            .isEqualTo(11);

        assertThat(buffered.getWidth())
            .isEqualTo(width);

        assertThat(buffered.getHeight())
            .isEqualTo(height);

        // When
        image.forEachApply((_, _) -> new ColoredPixel(Color.BLUE));

        // Then
        image.forEach((x, y) -> assertThat(image.pixel(x, y).get())
            .isEqualTo(Color.BLUE));
    }
}
