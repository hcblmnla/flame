package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class JuliaVariation extends CleanVariation {

    private final Random random;

    @Override
    public Point apply(final Point point) {
        double half = point.theta() / 2;

        return new Point(
            cos(half + omega(random)),
            sin(half + omega(random))
        )
            .apply(Math.sqrt(point.r()));
    }
}
