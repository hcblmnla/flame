package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurlVariation extends CleanVariation {

    private final double c1; // p1
    private final double c2; // p2

    public CurlVariation() {
        this(0.4, 0.7);
    }

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        double t1 = 1 + c1 * x + c2 * (pow2(x) - pow2(y));
        double t2 = c1 * y + 2 * c2 * x * y;

        return new Point(
            x * t1 + y * t2,
            y * t1 - x * t2
        )
            .apply(1 / (pow2(t1) + pow2(t2)));
    }
}
