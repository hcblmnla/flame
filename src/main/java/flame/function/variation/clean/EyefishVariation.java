package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class EyefishVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double normalizer = 2 / (point.r() + 1);

        return new Point(
            point.x(),
            point.y()
        )
            .apply(normalizer);
    }
}
