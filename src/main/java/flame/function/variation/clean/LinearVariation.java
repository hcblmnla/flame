package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class LinearVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        return new Point(
            point.x(),
            point.y()
        );
    }
}
