package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class BentVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        if (x < 0) {
            x *= 2;
        }
        if (y < 0) {
            y /= 2;
        }

        return new Point(x, y);
    }
}
