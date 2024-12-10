package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.AbstractVariation;
import flame.function.variation.CleanVariation;

public class SinusoidalVariation extends CleanVariation {

    @Override
    public Point apply(final Point point) {
        return point.apply(AbstractVariation::sin);
    }
}
