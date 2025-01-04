package flame.image;

import flame.image.resolution.Resolution;
import flame.image.resolution.UnknownResolutionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResolutionTest {

    @ParameterizedTest
    @ValueSource(strings = {"mp4, txt, java, ex, hs"})
    void resolutionShouldNotSupportUnknownFormats(final String unknown) {
        assertThrows(
            UnknownResolutionException.class,
            () -> Resolution.of(unknown)
        );
    }

    @Test
    void resolutionSupportsGeometricFormats() throws UnknownResolutionException {
        // Given
        var resolution = Resolution.of("square");

        // Then
        assertThat(resolution.width())
            .isEqualTo(resolution.height());
    }

    @Test
    void resolutionOfShouldSupportsFullHDFormats() throws UnknownResolutionException {
        // Given
        var fhd = Resolution.of("fhd");
        var uhd = Resolution.of("uhd");
        var uhd8 = Resolution.of("uhd8");

        // Then
        assertThat(fhd.width() * 4)
            .isEqualTo(uhd.width() * 2)
            .isEqualTo(uhd8.width());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8})
    void resolutionOfShouldSupportsKFormats(final int k) throws UnknownResolutionException {
        // Given
        var nk = Resolution.of(k + "k");

        // When
        int width = nk.width();
        int height = nk.height();
        int compression = nk.compression();

        // Then
        assertThat(width)
            .isEqualTo(1024 * k);

        assertThat(height)
            .isEqualTo(width / 2);

        assertThat(width * compression)
            .isLessThan(10_000);
    }
}
