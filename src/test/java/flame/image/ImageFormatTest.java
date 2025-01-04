package flame.image;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageFormatTest {

    @ParameterizedTest
    @ValueSource(strings = {"BMP", "PNG", "JPG", "JPEG"})
    void imageFormatOfShouldReturnCorrectFormat(final String extension) {
        assertThat(ImageFormat.valueOf(extension).extension())
            .isEqualToIgnoringCase(extension);
    }

    @ParameterizedTest
    @ValueSource(strings = {"bmp", "txt", "mp4", "wav"})
    void imageFormatOfShouldThrowExceptionForUnknownFormat(final String extension) {
        assertThrows(
            IllegalArgumentException.class,
            () -> ImageFormat.valueOf(extension)
        );
    }
}
