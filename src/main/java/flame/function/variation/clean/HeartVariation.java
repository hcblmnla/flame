package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class HeartVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double r = point.r();
        double rTheta = r * point.theta();

        return new Point(
            sin(rTheta),
            -cos(rTheta)
        )
            .apply(r);
    }
}
