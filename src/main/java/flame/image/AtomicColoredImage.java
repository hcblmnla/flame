package flame.image;

import java.awt.*;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class AtomicColoredImage extends AbstractColoredImage<AtomicReferenceArray<ColoredPixel>> {

    public AtomicColoredImage(final int width, final int height, final int compression, final Color background) {
        super(width, height, compression, background);
    }

    @Override
    protected AtomicReferenceArray<ColoredPixel> grid() {
        return new AtomicReferenceArray<>(new ColoredPixel[height() * width()]);
    }

    @Override
    public ColoredPixel pixel(final int x, final int y) {
        return grid.get(coordinate(x, y));
    }

    @Override
    public void pixel(final int x, final int y, final ColoredPixel value) {
        grid.set(coordinate(x, y), value);
    }

    @Override
    public ColoredPixel max(final Comparator<? super ColoredPixel> comparator) {
        ColoredPixel max = grid.get(0);
        for (int i = 1; i < grid.length(); i++) {
            var pixel = grid.get(i);
            if (comparator.compare(max, pixel) < 0) {
                max = pixel;
            }
        }
        return max;
    }
}
