package flame.image;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public final class ColoredImage extends AbstractColoredImage<ColoredPixel[]> {

    public ColoredImage(final int width, final int height, final int compression, final Color background) {
        super(width, height, compression, background);
    }

    @Override
    public ColoredPixel[] grid() {
        return new ColoredPixel[height() * width()];
    }

    @Override
    public ColoredPixel pixel(final int x, final int y) {
        return grid[coordinate(x, y)];
    }

    @Override
    public void pixel(final int x, final int y, final ColoredPixel value) {
        grid[coordinate(x, y)] = value;
    }

    @Override
    public ColoredPixel max(final Comparator<? super ColoredPixel> comparator) {
        return Arrays.stream(grid)
            .max(comparator)
            .orElseGet(() -> new ColoredPixel(ColoredPixel.DEFAULT_COLOR));
    }
}
