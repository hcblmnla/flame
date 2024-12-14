package flame.function;

import flame.function.affine.Affine;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class VariationTest {

    private static final Random RANDOM = new Random();
    private static final Affine AFFINE = Affine.defaults(RANDOM).getFirst();

    private static Stream<String> provideAllVariationsNames() {
        return Stream.concat(
            VariationSelector.clean().stream(),
            VariationSelector.random().stream()
        );
    }

    @ParameterizedTest
    @MethodSource("provideAllVariationsNames")
    void allVariationsShouldReturnNonNullPoint(final String name) {
        // Given
        var variation = VariationSelector.select(name, RANDOM);
        var point = new Point(-1, 1);

        // Then
        assertThat(variation.apply(point, AFFINE))
            .isExactlyInstanceOf(Point.class)
            .isNotNull();
    }
}
