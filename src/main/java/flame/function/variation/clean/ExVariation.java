package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class ExVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double theta = point.theta();
        double r = point.r();

        double p0 = sin(theta + r);
        double p1 = cos(theta - r);

        return new Point(
            pow3(p0) + pow3(p1),
            pow3(p0) - pow3(p1)
        )
            .apply(r);
    }
}
