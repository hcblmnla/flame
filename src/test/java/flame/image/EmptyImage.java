package flame.image;

import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.util.Comparator;

@NoArgsConstructor
class EmptyImage implements Image<Integer> {

    @Override
    public int width() {
        return 1;
    }

    @Override
    public int height() {
        return 1;
    }

    @Override
    public Integer pixel(final int x, final int y) {
        return 1;
    }

    @Override
    public void pixel(final int x, final int y, final Integer value) {
    }

    @Override
    public Integer max(final Comparator<? super Integer> comparator) {
        return 1;
    }

    @Override
    public BufferedImage toBufferedImage() {
        return new BufferedImage(width(), height(), ColoredPixel.SUPPORTED_FORMAT);
    }
}
