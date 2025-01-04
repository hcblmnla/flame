package flame.image;

import java.awt.image.BufferedImage;
import java.util.Comparator;

/**
 * Extended image that can be reduced to {@link BufferedImage}.
 *
 * @param <I> type of the pixels
 * @author alnmlbch
 */
public interface Image<I> {

    /**
     * Width parameter.
     *
     * @return width of the image
     */
    int width();

    /**
     * Height parameter.
     *
     * @return height of the image
     */
    int height();

    /**
     * Checks that the coordinates are in the image.
     *
     * @return {@code true} if coordinates are in the image, else {@code false}.
     */
    default boolean contains(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    /**
     * Pixel getter.
     *
     * @param x width coordinate
     * @param y height coordinate
     * @return pixel from {@code (x, y)} coordinate
     */
    I pixel(int x, int y);

    /**
     * Pixel setter.
     *
     * @param x width coordinate
     * @param y height coordinate
     */
    void pixel(int x, int y, I value);

    /**
     * Accepts {@link GridConsumer} for all pixels in the image.
     *
     * @param consumer accepter
     */
    default void forEach(final GridConsumer consumer) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                consumer.accept(x, y);
            }
        }
    }

    default void forEachApply(final GridFunction<I> mapper) {
        forEach((x, y) -> pixel(x, y, mapper.apply(x, y)));
    }

    I max(Comparator<? super I> comparator);

    /**
     * Converts the image to {@link BufferedImage}
     *
     * @return buffered image
     */
    BufferedImage toBufferedImage();
}
