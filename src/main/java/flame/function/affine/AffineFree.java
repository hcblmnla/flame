package flame.function.affine;

import java.util.Random;

import static flame.function.Isomorphism.randomUnit;

public record AffineFree(double c, double f) {

    public static AffineFree random(final Random random) {
        return new AffineFree(randomUnit(random), randomUnit(random));
    }
}
