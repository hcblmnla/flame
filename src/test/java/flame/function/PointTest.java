package flame.function;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    private static final double eps = 0.05;
    private final Offset<Double> offset = Offset.offset(eps);

    @Test
    void pointShouldSupportsCorrectMonadBehavior() {
        // Given
        var point = new Point(-4, 3);

        // When
        var pointPlus1 = point.apply(param -> param + 1);
        var pointMulMinus2 = point.apply(-2);

        var rotatedPoint = point.rotated(Math.PI / 2);

        double square = point.square();
        double r = point.r();

        // Then
        closeTo(pointPlus1.x(), -3);
        closeTo(pointMulMinus2.y(), -6);

        closeTo(rotatedPoint.x(), -3);
        closeTo(rotatedPoint.y(), -4);

        closeTo(square, 25);
        closeTo(r, 5);

        closeTo(point.theta(), -0.9);
        closeTo(point.phi(), 2.5);
    }

    @TestFactory
    void closeTo(final double found, final double expected) {
        assertThat(found)
            .isCloseTo(expected, offset);
    }
}
