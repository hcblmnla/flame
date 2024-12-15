package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class PolarVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        return new Point(
            point.theta() * 2 / Math.PI,
            point.r() - 1
        );
    }
}
