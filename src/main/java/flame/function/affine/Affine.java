package flame.function.affine;

import flame.function.LinearFunction;
import flame.function.Point;
import flame.util.ExtendedRandom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static flame.image.ColoredPixel.randomColor;

@RequiredArgsConstructor
@Getter
public class Affine implements LinearFunction {

    private static final int DEFAULT_COUNT = 8;

    // TODO: remove and use file with colors reading
    private static final List<Color> DEFAULT_COLORS = List.of(
//        new Color(255, 0, 0),
//        new Color(255, 255, 0),
        new Color(0, 93, 255),
        new Color(0, 255, 170),
        new Color(255, 255, 255),
        new Color(221, 0, 255),
        new Color(124, 70, 255)
//        new Color(189, 137, 0),
//        new Color(255, 195, 47),
//        new Color(255, 223, 143),
//        new Color(0, 100, 10),
//        new Color(57, 255, 0)
    );

    private final AffineMatrix matrix;
    private final AffineFree free;

    private final Color color;

    public Affine(final Color color, final Random random) {
        this(
            AffineMatrix.random(random),
            AffineFree.random(random),
            color
        );
    }

    private Affine(final List<Color> colors, final Random random) {
        this(ExtendedRandom.element(colors, random), random);
    }

    public Affine(final Random random) {
        this(randomColor(random), random);
    }

    private static List<Affine> generate(final Supplier<Affine> supplier) {
        return Stream.generate(supplier)
            .limit(DEFAULT_COUNT)
            .toList();
    }

    public static List<Affine> defaults(final Random random) {
        return generate(() -> new Affine(DEFAULT_COLORS, random));
    }

    public static List<Affine> random(final Random random) {
        return generate(() -> new Affine(random));
    }

    @Override
    public Point apply(final Point point) {
        var x = point.x();
        var y = point.y();

        return new Point(
            matrix.a() * x + matrix.b() * y + free.c(),
            matrix.d() * x + matrix.e() * y + free.f()
        );
    }
}
