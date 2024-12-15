package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;

public class BubbleVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        double normalizer = 4 / (point.square() + 4);

        return new Point(
            point.x(),
            point.y()
        )
            .apply(normalizer);
    }
}
