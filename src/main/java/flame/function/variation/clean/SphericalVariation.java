package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class SphericalVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        return point.apply(1 / point.square());
    }
}
