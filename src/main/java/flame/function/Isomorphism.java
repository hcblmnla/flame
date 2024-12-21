package flame.function;

import java.util.Random;
import java.util.function.Predicate;

public record Isomorphism(double minX, double minY, double maxX, double maxY)
    implements Predicate<Point> {

    public static double randomUnit(final Random random) {
        return random.nextDouble(-1.0, 1.0);
    }

    public static Isomorphism biUnit(final int width, final int height) {
        double maxX = (double) width / height;
        double maxY = 1;

        if (height > width) {
            maxY = 1 / maxX;
            maxX = 1;
        }
        return new Isomorphism(-maxX, -maxY, maxX, maxY);
    }

    public Point randomUnitPoint(final Random random) {
        return new Point(
            random.nextDouble(minX, maxX),
            random.nextDouble(minY, maxY)
        );
    }

    public IntPoint deUnitPoint(final Point point, final int width, final int height) {
        return new IntPoint(
            width - (int) (width * ((maxX - point.x()) / (maxX - minX))),
            height - (int) (height * ((maxY - point.y()) / (maxY - minY)))
        );
    }

    @Override
    public boolean test(final Point point) {
        var x = point.x();
        var y = point.y();
        return minX <= x && x <= maxX && minY <= y && y <= maxY;
    }
}
