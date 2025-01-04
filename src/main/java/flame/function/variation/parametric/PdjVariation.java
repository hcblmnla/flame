package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PdjVariation extends CleanVariation {

    private final double a; // p1
    private final double b; // p2
    private final double c; // p3
    private final double d; // p4

    public PdjVariation() {
        this(1, 2, 3, 4);
    }

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(
            sin(a * y) - cos(b * x),
            sin(c * x) - cos(d * y)
        );
    }
}
