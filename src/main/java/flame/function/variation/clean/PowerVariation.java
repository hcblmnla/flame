package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class PowerVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double theta = point.theta();

        return new Point(
            cos(theta),
            sin(theta)
        )
            .apply(Math.pow(point.r(), sin(theta)));
    }
}
