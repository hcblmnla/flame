package flame.function.variation.dependent;

import flame.function.Point;
import flame.function.affine.Affine;
import flame.function.variation.AbstractVariation;

public class FanVariation extends AbstractVariation {

    @Override
    public Point apply(final Point point, final Affine affine) {
        double t = Math.PI * pow2(affine.free().c());
        double f = affine.free().f();

        double theta = point.theta();
        double alpha = theta + (t / 2) * ((theta + f) % t > t / 2 ? -1 : 1);

        return new Point(
            cos(alpha),
            sin(alpha)
        )
            .apply(point.r());
    }
}
