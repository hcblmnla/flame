package flame.function.variation.dependent;

import flame.function.Point;
import flame.function.affine.Affine;
import flame.function.variation.AbstractVariation;

public class PopcornVariation extends AbstractVariation {

    @Override
    public Point apply(final Point point, final Affine affine) {
        double x = point.x();
        double y = point.y();

        return new Point(
            x + affine.free().c() * sin(Math.tan(3 * y)),
            y + affine.free().f() * sin(Math.tan(3 * x))
        );
    }
}
