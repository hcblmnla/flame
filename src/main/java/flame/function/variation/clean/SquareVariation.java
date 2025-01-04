package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class SquareVariation extends CleanVariation {

    private final static double HALF_DELTA = 0.5;
    private final Random random;

    @Override
    public Point apply(final Point point) {
        return new Point(
            psi(random) - HALF_DELTA,
            psi(random) - HALF_DELTA
        );
    }
}
