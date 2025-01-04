package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class ExponentialVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double piy = Math.PI * point.y();

        return new Point(
            cos(piy),
            sin(piy)
        )
            .apply(Math.exp(point.x() - 1));
    }
}
