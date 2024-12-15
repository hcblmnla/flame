package flame.function.variation;

import flame.function.Variation;
import flame.function.affine.Affine;

import java.util.Random;

public abstract class AbstractVariation implements Variation<Affine> {

    protected static double pow2(double param) {
        return param * param;
    }

    protected static double pow3(double param) {
        return param * param * param;
    }

    protected static double sin(double param) {
        return Math.sin(param);
    }

    protected static double cos(double param) {
        return Math.cos(param);
    }

    protected static double omega(final Random random) {
        return random.nextBoolean() ? 0 : Math.PI;
    }

    protected static double delta(final Random random) {
        return random.nextBoolean() ? -1 : 1;
    }

    protected static double psi(final Random random) {
        return random.nextDouble();
    }

    protected static int trunc(double param) {
        return (int) param;
    }
}
