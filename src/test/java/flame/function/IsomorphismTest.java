package flame.function;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class IsomorphismTest {

    private static Stream<Arguments> provideIsomorphismParams() {
        return Stream.of(
            Arguments.of(
                160, 100, 130, 100,
                2, 0, 1, -10
            ),
            Arguments.of(
                100, 200, 100, 150,
                -4, 0, 0, Integer.MAX_VALUE
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideIsomorphismParams")
    void isomorphismShouldDoCorrectTransform(
        final int width,
        final int height,
        final int deX,
        final int deY,
        final double invX1,
        final double invY1,
        final double invX2,
        final double invY2
    ) {
        // Given
        var random = new Random();

        // When
        var isomorphism = Isomorphism.biUnit(width, height);

        // Then
        for (int i = 0; i < 100; i++) {
            assertThat(isomorphism.test(isomorphism.randomUnitPoint(random)))
                .isTrue();
        }

        assertThat(isomorphism.test(new Point(invX1, invY1)))
            .isEqualTo(isomorphism.test(new Point(invX2, invY2)))
            .isFalse();

        // When
        var point = new Point(1, 1);

        // Then
        assertThat(isomorphism.deUnitPoint(point, width, height))
            .isEqualTo(new IntPoint(deX, deY));
    }
}
