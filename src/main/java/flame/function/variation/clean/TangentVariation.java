package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class TangentVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(
            sin(x) / cos(y),
            Math.tan(y)
        );
    }
}
