package flame.function.variation.dependent;

import flame.function.Point;
import flame.function.affine.Affine;
import flame.function.variation.AbstractVariation;

public class RingsVariation extends AbstractVariation {

    @Override
    public Point apply(final Point point, final Affine affine) {
        double theta = point.theta();
        double r = point.r();
        double c2 = pow2(affine.free().c());

        return new Point(
            cos(theta),
            sin(theta)
        )
            .apply((r + c2) % (2 * c2) - c2 + r * (1 - c2));
    }
}
