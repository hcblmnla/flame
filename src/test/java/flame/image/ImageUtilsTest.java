package flame.image;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.IOException;
import java.nio.file.Path;

import static flame.image.ImageUtils.saveImage;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {

    @TempDir
    private Path tempDir;

    @ParameterizedTest
    @EnumSource(ImageFormat.class)
    void saveImageShouldCreatesNewFileAlways(final ImageFormat format) throws IOException {
        // Given
        var image = new EmptyImage();
        var filename = "tempName";

        // Then
        for (int i = 0; i < 10; i++) {
            assertThat(saveImage(image, tempDir, filename, format))
                .isTrue();
        }
    }

    @ParameterizedTest
    @EnumSource(ImageFormat.class)
    void saveImageShouldReturnFalseIfPathIsNotDirectory(final ImageFormat format) throws IOException {
        // Given
        var path = Path.of(".gitignore");
        var image = new EmptyImage();
        var filename = "tempName";

        // Then
        assertThat(saveImage(image, path, filename, format))
            .isFalse();
    }
}
