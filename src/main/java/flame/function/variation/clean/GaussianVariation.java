package flame.function.variation.clean;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class GaussianVariation extends CleanVariation {

    private final Random random;

    @Override
    public Point apply(final Point point) {
        double pi2 = Math.PI * 2;

        return new Point(
            cos(pi2 * psi(random)),
            sin(pi2 * psi(random))
        )
            .apply(random.nextGaussian());
    }
}
