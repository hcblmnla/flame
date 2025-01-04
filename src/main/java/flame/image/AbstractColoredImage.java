package flame.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract sealed class AbstractColoredImage<G>
    implements Image<ColoredPixel>
    permits ColoredImage, AtomicColoredImage {

    protected final G grid;
    private final int width;
    private final int height;
    private final int compression;

    protected AbstractColoredImage(
        final int width,
        final int height,
        final int compression,
        final Color background
    ) {
        this.width = width;
        this.height = height;
        this.compression = compression;
        grid = grid();
        var backgroundPixel = new ColoredPixel(background);
        forEach((x, y) -> pixel(x, y, backgroundPixel));
    }

    protected abstract G grid();

    protected int coordinate(final int x, final int y) {
        return y * width() + x;
    }

    public int width() {
        return width * compression;
    }

    public int height() {
        return height * compression;
    }

    @Override
    public BufferedImage toBufferedImage() {
        int decWidth = width() / compression;
        int decHeight = height() / compression;

        var image = new BufferedImage(decWidth, decHeight, ColoredPixel.SUPPORTED_FORMAT);
        for (int y = 0; y < decHeight; y++) {
            for (int x = 0; x < decWidth; x++) {
                final List<ColoredPixel> pixels = new ArrayList<>();

                for (int dy = 0; dy < compression; dy++) {
                    for (int dx = 0; dx < compression; dx++) {
                        pixels.add(pixel(x * compression + dx, y * compression + dy));
                    }
                }
                image.setRGB(x, y, ColoredPixel.average(pixels).get().getRGB());
            }
        }
        return image;
    }
}
