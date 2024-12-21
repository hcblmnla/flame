package flame.function;

import flame.function.variation.clean.CylinderVariation;
import flame.function.variation.clean.DiscVariation;
import flame.function.variation.clean.HeartVariation;
import flame.function.variation.clean.HyperbolicVariation;
import flame.function.variation.clean.JuliaVariation;
import flame.function.variation.clean.LinearVariation;
import flame.function.variation.clean.NoiseVariation;
import flame.function.variation.clean.PolarVariation;
import flame.function.variation.clean.PowerVariation;
import flame.function.variation.clean.SinusoidalVariation;
import flame.function.variation.clean.SphericalVariation;
import flame.function.variation.clean.SpiralVariation;
import flame.function.variation.clean.SwirlVariation;
import flame.function.variation.clean.TangentVariation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VariationSelectorTest {

    private static final Random RANDOM = new Random();

    private static Stream<Arguments> provideKnownVariation() {
        return Stream.of(
            // clean
            Arguments.of("cylinder", CylinderVariation.class),
            Arguments.of("disc", DiscVariation.class),
            Arguments.of("power", PowerVariation.class),
            Arguments.of("heart", HeartVariation.class),
            Arguments.of("hyperbolic", HyperbolicVariation.class),
            Arguments.of("linear", LinearVariation.class),
            Arguments.of("polar", PolarVariation.class),
            Arguments.of("sinusoidal", SinusoidalVariation.class),
            Arguments.of("spherical", SphericalVariation.class),
            Arguments.of("spiral", SpiralVariation.class),
            Arguments.of("swirl", SwirlVariation.class),
            Arguments.of("tangent", TangentVariation.class),
            // random
            Arguments.of("julia", JuliaVariation.class),
            Arguments.of("noise", NoiseVariation.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"__NO_NAME__", "mp3", "Haskell"})
    void selectorShouldThrowExceptionWhenNameIsUnknown(final String name) {
        assertThrows(
            UnknownVariationException.class,
            () -> VariationSelector.select(name, RANDOM)
        );
    }

    @ParameterizedTest
    @MethodSource("provideKnownVariation")
    void selectorShouldSupportsSomeKnownVariation(final String name, final Class<?> instance) {
        assertThat(VariationSelector.select(name, RANDOM))
            .isExactlyInstanceOf(instance);
    }
}
