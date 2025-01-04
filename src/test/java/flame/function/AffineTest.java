package flame.function;

import flame.function.affine.Affine;
import flame.function.affine.AffineFree;
import flame.function.affine.AffineMatrix;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AffineTest {

    private static final Random RANDOM = new Random();

    private final Affine affine = new Affine(
        new AffineMatrix(1, 0, 0.5, 0.5),
        new AffineFree(3, 4),
        Color.BLACK
    );

    private static Stream<Affine> provideInitialAffine() {
        return Stream.concat(
            Affine.defaults(RANDOM).stream(),
            Affine.random(RANDOM).stream()
        );
    }

    private static Stream<Arguments> providePoints() {
        return Stream.of(
            Arguments.of(1, 0, 4, 4.5),
            Arguments.of(0, 1, 3, 4.5),
            Arguments.of(1, 1, 4, 5),
            Arguments.of(2, 3, 5, 6.5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInitialAffine")
    void affineMatrixShouldBeCorrect(final Affine affine) {
        assertThat(affine.matrix().getAsBoolean())
            .isTrue();
    }

    @ParameterizedTest
    @MethodSource("providePoints")
    void affineShouldDoCorrectTransform(double x1, double y1, double x2, double y2) {
        assertThat(affine.apply(new Point(x1, y1)))
            .isEqualTo(new Point(x2, y2));
    }
}
