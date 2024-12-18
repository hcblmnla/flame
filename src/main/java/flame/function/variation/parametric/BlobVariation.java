package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlobVariation extends CleanVariation {

    private static final double DEFAULT_HIGH = 1.5;
    private static final double DEFAULT_LOW = 0.5;
    private static final double DEFAULT_WAVES = 4;

    private final double high;  // p1
    private final double low;   // p2
    private final double waves; // p3

    public BlobVariation() {
        this(DEFAULT_HIGH, DEFAULT_LOW, DEFAULT_WAVES);
    }

    @Override
    public Point apply(final Point point) {
        double theta = point.theta();

        return new Point(
            cos(theta),
            sin(theta)
        )
            .apply(point.r())
            .apply(low + (high - low) / 2 * (sin(waves * theta) + 1));
    }
}
