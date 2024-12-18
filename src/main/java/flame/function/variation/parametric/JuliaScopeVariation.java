package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class JuliaScopeVariation extends CleanVariation {

    private static final double DEFAULT_POWER = 1;
    private static final double DEFAULT_DIST = 1;

    private final double power; // p1
    private final double dist;  // p2

    private final Random random;

    public JuliaScopeVariation(final Random random) {
        this(DEFAULT_POWER, DEFAULT_DIST, random);
    }

    @Override
    public Point apply(final Point point) {
        double p3 = trunc(Math.abs(power) * psi(random));
        double t = (delta(random) * point.phi() + 2 * Math.PI * p3) / power;

        return new Point(
            cos(t),
            sin(t)
        )
            .apply(Math.pow(point.r(), dist / power));
    }
}
