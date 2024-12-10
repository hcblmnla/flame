package flame.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public record CompressedImage(ColoredPixel[][] grid, int width, int height, int compression)
    implements Image<ColoredPixel> {

    public CompressedImage(final int width, final int height, final Color background, final int compression) {
        this(
            new ColoredPixel[height * compression][width * compression],
            width * compression,
            height * compression,
            compression
        );
        var backgroundPixel = new ColoredPixel(background);
        forEach((x, y) -> pixel(x, y, backgroundPixel));
    }

    @Override
    public ColoredPixel pixel(final int x, final int y) {
        return grid[y][x];
    }

    @Override
    public void pixel(final int x, final int y, final ColoredPixel value) {
        grid[y][x] = value;
    }

    @Override
    public ColoredPixel max(final Comparator<? super ColoredPixel> comparator) {
        return Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .max(comparator)
            .orElseGet(() -> new ColoredPixel(ColoredPixel.DEFAULT_COLOR));
    }

    @Override
    public BufferedImage toBufferedImage() {
        int decWidth = width / compression;
        int decHeight = height / compression;

        var image = new BufferedImage(decWidth, decHeight, ColoredPixel.SUPPORTED_FORMAT);
        for (int y = 0; y < decHeight; y++) {
            for (int x = 0; x < decWidth; x++) {
                final List<ColoredPixel> pixels = new ArrayList<>();

                for (int dy = 0; dy < compression; dy++) {
                    pixels.addAll(
                        Arrays
                            .asList(grid[y * compression + dy])
                            .subList(x * compression, (x + 1) * compression)
                    );
                }
                image.setRGB(x, y, ColoredPixel.average(pixels).get().getRGB());
            }
        }
        return image;
    }
}
