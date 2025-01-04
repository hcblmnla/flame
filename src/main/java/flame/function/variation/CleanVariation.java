package flame.function.variation;

import flame.function.Point;
import flame.function.affine.Affine;

public abstract class CleanVariation extends AbstractVariation {

    public abstract Point apply(Point point);

    @Override
    public Point apply(final Point point, final Affine affine) {
        return apply(point);
    }
}
