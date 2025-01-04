package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class SpiralVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double theta = point.theta();
        double r = point.r();

        return new Point(
            cos(theta) + sin(r),
            sin(theta) - cos(r)
        )
            .apply(1 / r);
    }
}
