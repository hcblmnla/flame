package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class CylinderVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        return new Point(
            sin(point.x()),
            point.y()
        );
    }
}
