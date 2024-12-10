package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class HorseshoeVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(
            (x - y) * (x + y),
            2 * x * y
        )
            .apply(1 / point.r());
    }
}
