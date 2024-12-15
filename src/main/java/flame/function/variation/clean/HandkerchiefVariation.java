package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class HandkerchiefVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double theta = point.theta();
        double r = point.r();

        return new Point(
            sin(theta + r),
            cos(theta - r)
        )
            .apply(r);
    }
}
