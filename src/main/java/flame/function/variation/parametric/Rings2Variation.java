package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rings2Variation extends CleanVariation {

    private final static double DEFAULT_VAL = 0.6;
    private final double val; // p1

    public Rings2Variation() {
        this(DEFAULT_VAL);
    }

    @Override
    public Point apply(final Point point) {
        double p = pow2(val);
        double r = point.r();
        double theta = point.theta();

        return new Point(
            sin(theta),
            cos(theta)
        )
            .apply(r - 2 * p * trunc((r + p) / (2 * p)) + r * (1 - p));
    }
}
