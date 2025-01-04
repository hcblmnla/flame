package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class CrossVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double x = point.x();
        double y = point.y();

        return new Point(x, y)
            .apply(Math.sqrt(1 / pow2(pow2(x) - pow2(y))));
    }
}
