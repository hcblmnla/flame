package flame.image;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ColoredPixelTest {

    @Test
    void colorCtorsShouldReturnsNotHitPixel() {
        // Given
        var random = new Random();
        var color = ColoredPixel.randomColor(random);
        var color2 = ColoredPixel.randomColor(random);

        // When
        var pixel = new ColoredPixel(color.getRed(), color.getGreen(), color.getBlue());

        // Then
        assertThat(pixel)
            .isEqualTo(new ColoredPixel(color));

        assertThat(pixel.hitCount())
            .isZero();

        assertThat(pixel.get())
            .isEqualTo(color);

        assertThat(pixel.color(color2).get())
            .isEqualTo(color2);
    }

    @Test
    void coloredPixelAverageShouldReturnsCorrectPixel() {
        // Given
        var list = List.of(
            new ColoredPixel(0, 1, 2),
            new ColoredPixel(10, 19, 18),
            new ColoredPixel(4, 8, 8),
            new ColoredPixel(0, 0, 0),
            new ColoredPixel(7, 7, 7),
            new ColoredPixel(70, 70, 70),
            new ColoredPixel(1, 1, 1)
        );

        // When
        var avg = ColoredPixel.average(list);

        // Then
        assertThat(avg.hitCount())
            .isZero();

        assertThat(avg.red())
            .isEqualTo(13);

        assertThat(avg.green())
            .isEqualTo(avg.blue())
            .isEqualTo(15);

        assertThat(avg.apply(color -> color - 10))
            .isEqualTo(new ColoredPixel(3, 5, 5));
    }

    @Test
    void hitShouldIncrementHitCount() {
        // Given
        var pixel = new ColoredPixel(0, 1, 2);

        // When
        for (int i = 0; i < 100; i++) {
            pixel = pixel.hit();
        }

        assertThat(pixel.hitCount())
            .isEqualTo(100);
    }
}
