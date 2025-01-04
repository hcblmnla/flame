package flame.function.variation.dependent;

import flame.function.Point;
import flame.function.affine.Affine;
import flame.function.variation.AbstractVariation;

public class WavesVariation extends AbstractVariation {

    @Override
    public Point apply(final Point point, final Affine affine) {
        double x = point.x();
        double y = point.y();

        return new Point(
            x + affine.matrix().b() * sin(y / pow2(affine.free().c())),
            y + affine.matrix().e() * sin(x / pow2(affine.free().f()))
        );
    }
}
