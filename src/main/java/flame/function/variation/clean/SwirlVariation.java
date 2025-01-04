package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class SwirlVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double r2 = point.square();
        double s = sin(r2);
        double c = cos(r2);

        double x = point.x();
        double y = point.y();

        return new Point(
            x * s - y * c,
            x * c + y * s
        );
    }
}
