package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class DiscVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double alpha = Math.PI * point.r();

        return new Point(
            sin(alpha),
            cos(alpha)
        )
            .apply(point.theta() / Math.PI);
    }
}
