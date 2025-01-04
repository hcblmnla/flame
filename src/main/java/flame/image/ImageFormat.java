package flame.image;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Set of the supported image formats.
 * Alpha parameter supports only by {@link ImageFormat#PNG}.
 *
 * @author alnmlbch
 */
@RequiredArgsConstructor
@Getter
public enum ImageFormat {
    BMP("bmp"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    private final String extension;
}
