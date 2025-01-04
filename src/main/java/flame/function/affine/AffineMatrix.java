package flame.function.affine;

import java.util.Random;
import java.util.function.BooleanSupplier;

import static flame.function.Isomorphism.randomUnit;

public record AffineMatrix(double a, double b, double d, double e)
    implements BooleanSupplier {

    public static AffineMatrix random(final Random random) {
        AffineMatrix mx;
        do {
            mx = new AffineMatrix(
                randomUnit(random),
                randomUnit(random),
                randomUnit(random),
                randomUnit(random)
            );
        } while (!mx.getAsBoolean());
        return mx;
    }

    private static double square(final double x) {
        return x * x;
    }

    @Override
    public boolean getAsBoolean() {
        double sum1 = square(a) + square(d);
        double sum2 = square(b) + square(e);
        return sum1 < 1 && sum2 < 1 && sum1 + sum2 < 1 + square(a * e - b * d);
    }
}
