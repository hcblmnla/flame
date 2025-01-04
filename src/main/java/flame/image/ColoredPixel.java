package flame.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;

public record ColoredPixel(int red, int green, int blue, int hitCount)
    implements Supplier<Color>, Function<IntUnaryOperator, ColoredPixel> {

    public static final int SUPPORTED_FORMAT = BufferedImage.TYPE_INT_RGB;
    public static final int MAX_COLOR_VALUE = 255;

    public static final Color DEFAULT_COLOR = Color.BLACK;

    public ColoredPixel(final int red, final int green, final int blue) {
        this(red, green, blue, 0);
    }

    public ColoredPixel(final Color color) {
        this(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static ColoredPixel average(final List<ColoredPixel> pixels) {
        int red = 0;
        int green = 0;
        int blue = 0;

        for (var pixel : pixels) {
            red += pixel.red;
            green += pixel.green;
            blue += pixel.blue;
        }

        int size = pixels.size();
        return new ColoredPixel(red / size, green / size, blue / size);
    }

    public static Color randomColor(final Random random) {
        return new Color(
            random.nextInt(MAX_COLOR_VALUE + 1),
            random.nextInt(MAX_COLOR_VALUE + 1),
            random.nextInt(MAX_COLOR_VALUE + 1)
        );
    }

    @Override
    public Color get() {
        return new Color(red, green, blue);
    }

    @Override
    public ColoredPixel apply(final IntUnaryOperator mapper) {
        return color(mapper.applyAsInt(red), mapper.applyAsInt(green), mapper.applyAsInt(blue));
    }

    public ColoredPixel color(final int red, final int green, final int blue) {
        return new ColoredPixel(red, green, blue, hitCount);
    }

    public ColoredPixel color(final Color color) {
        return color(color.getRed(), color.getGreen(), color.getBlue());
    }

    public ColoredPixel hit() {
        return new ColoredPixel(red, green, blue, hitCount + 1);
    }
}
