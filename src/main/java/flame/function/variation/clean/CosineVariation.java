package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class CosineVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double pix = Math.PI * point.x();
        double y = point.y();

        return new Point(
            cos(pix) * Math.cosh(y),
            -sin(pix) * Math.sinh(y)
        );
    }
}
