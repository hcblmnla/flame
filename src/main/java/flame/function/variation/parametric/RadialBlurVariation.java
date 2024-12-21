package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class RadialBlurVariation extends CleanVariation {

    private static final double DEFAULT_ANGLE = Math.PI / 6;

    private final double angle;
    private final Random random;

    public RadialBlurVariation(final Random random) {
        this(DEFAULT_ANGLE, random);
    }

    @Override
    public Point apply(final Point point) {
        double p1 = angle * Math.PI / 2; // p1
        double t1 = random.nextGaussian();

        double t2 = point.phi() + t1 * sin(p1);
        double t3 = t1 * cos(p1) - 1;
        double r = point.r();

        return new Point(
            r * cos(t2) + t3 * point.x(),
            r * sin(t2) + t3 * point.y()
        );
    }
}
