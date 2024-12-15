package flame.algorithm;

import flame.algorithm.processor.LogGammaCorrection;
import flame.image.ColoredImage;
import flame.image.ColoredPixel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class LogGammaCorrectionTest {

    @Test
    void acceptShouldCorrectUpdateImage() {
        // Given
        var grid = new ColoredPixel[][]{
            {new ColoredPixel(1, 1, 1, 100), new ColoredPixel(10, 10, 10, 10)},
            {new ColoredPixel(10, 10, 10, 10), new ColoredPixel(10, 10, 10, 1)}
        };
        var processed = new ColoredPixel[][]{
            {new ColoredPixel(1, 1, 1, 100), new ColoredPixel(2, 2, 2, 10)},
            {new ColoredPixel(2, 2, 2, 10), new ColoredPixel(0, 0, 0, 1)}
        };

        var image = new ColoredImage(2, 2, 1, Color.BLACK);
        var processor = new LogGammaCorrection(0.5);

        // When
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                image.pixel(x, y, grid[x][y]);
            }
        }
        processor.accept(image);

        // Then
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                Assertions.assertThat(image.pixel(x, y))
                    .isEqualTo(processed[y][x]);
            }
        }
    }
}
