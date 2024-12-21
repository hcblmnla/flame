package flame.function.variation.parametric;

import flame.function.Point;
import flame.function.variation.CleanVariation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PerspectiveVariation extends CleanVariation {

    private static final double DEFAULT_ANGLE = Math.PI / 6;
    private static final double DEFAULT_DIST = 2;

    private final double angle; // p1
    private final double dist;  // p2

    public PerspectiveVariation() {
        this(DEFAULT_ANGLE, DEFAULT_DIST);
    }

    @Override
    public Point apply(final Point point) {
        double y = point.y();

        return new Point(
            point.x(),
            y * cos(angle)
        )
            .apply(dist / (dist - y * sin(angle)));
    }
}
