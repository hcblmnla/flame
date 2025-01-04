package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class PieVariation extends CleanVariation {

    private final double slices;    // p1
    private final double rotation;  // p2
    private final double thickness; // p3

    private final Random random;

    public PieVariation(final Random random) {
        this(6, Math.PI / 6, 0.5, random);
    }

    @Override
    public Point apply(final Point point) {
        double t1 = trunc(psi(random) * slices + 0.5);
        double t2 = rotation + 2 * Math.PI / slices * (t1 + psi(random) * thickness);

        return new Point(
            cos(t2),
            sin(t2)
        )
            .apply(psi(random));
    }
}
