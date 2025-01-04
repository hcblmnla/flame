package flame.function;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public record Point(double x, double y)
    implements Function<DoubleUnaryOperator, Point>, DoubleFunction<Point> {

    @Override
    public Point apply(final DoubleUnaryOperator mapper) {
        return new Point(mapper.applyAsDouble(x), mapper.applyAsDouble(y));
    }

    @Override
    public Point apply(final double scalar) {
        return apply(param -> param * scalar);
    }

    public Point rotated(final double theta) {
        final double sin = Math.sin(theta);
        final double cos = Math.cos(theta);

        return new Point(x * cos - y * sin, y * cos + x * sin);
    }

    public double square() {
        return x * x + y * y;
    }

    public double r() {
        return Math.sqrt(square());
    }

    public double theta() {
        return Math.atan2(x, y);
    }

    public double phi() {
        return Math.atan2(y, x);
    }
}
