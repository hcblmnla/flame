package flame.function;

import flame.function.affine.Affine;
import flame.function.variation.clean.BentVariation;
import flame.function.variation.clean.BlurVariation;
import flame.function.variation.clean.BubbleVariation;
import flame.function.variation.clean.CosineVariation;
import flame.function.variation.clean.CrossVariation;
import flame.function.variation.clean.CylinderVariation;
import flame.function.variation.clean.DiamondVariation;
import flame.function.variation.clean.DiscVariation;
import flame.function.variation.clean.ExVariation;
import flame.function.variation.clean.ExponentialVariation;
import flame.function.variation.clean.EyefishVariation;
import flame.function.variation.clean.FisheyeVariation;
import flame.function.variation.clean.GaussianVariation;
import flame.function.variation.clean.HandkerchiefVariation;
import flame.function.variation.clean.HeartVariation;
import flame.function.variation.clean.HorseshoeVariation;
import flame.function.variation.clean.HyperbolicVariation;
import flame.function.variation.clean.JuliaVariation;
import flame.function.variation.clean.LinearVariation;
import flame.function.variation.clean.NoiseVariation;
import flame.function.variation.clean.PolarVariation;
import flame.function.variation.clean.PowerVariation;
import flame.function.variation.clean.SinusoidalVariation;
import flame.function.variation.clean.SphericalVariation;
import flame.function.variation.clean.SpiralVariation;
import flame.function.variation.clean.SquareVariation;
import flame.function.variation.clean.SwirlVariation;
import flame.function.variation.clean.TangentVariation;
import flame.function.variation.dependent.FanVariation;
import flame.function.variation.dependent.PopcornVariation;
import flame.function.variation.dependent.RingsVariation;
import flame.function.variation.dependent.WavesVariation;
import flame.function.variation.parametric.BlobVariation;
import flame.function.variation.parametric.CurlVariation;
import flame.function.variation.parametric.JuliaScopeVariation;
import flame.function.variation.parametric.PdjVariation;
import flame.function.variation.parametric.PerspectiveVariation;
import flame.function.variation.parametric.PieVariation;
import flame.function.variation.parametric.RadialBlurVariation;
import flame.function.variation.parametric.Rings2Variation;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Map.entry;

@UtilityClass
public class VariationSelector {

    private static final Map<String, Supplier<Variation<Affine>>> CLEAN = Map.ofEntries(
        entry("bent", BentVariation::new),
        entry("bubble", BubbleVariation::new),
        entry("cosine", CosineVariation::new),
        entry("cross", CrossVariation::new),
        entry("cylinder", CylinderVariation::new),
        entry("diamond", DiamondVariation::new),
        entry("disc", DiscVariation::new),
        entry("ex", ExVariation::new),
        entry("exponential", ExponentialVariation::new),
        entry("eyefish", EyefishVariation::new),
        entry("fisheye", FisheyeVariation::new),
        entry("handkerchief", HandkerchiefVariation::new),
        entry("heart", HeartVariation::new),
        entry("horseshoe", HorseshoeVariation::new),
        entry("hyperbolic", HyperbolicVariation::new),
        entry("linear", LinearVariation::new),
        entry("polar", PolarVariation::new),
        entry("power", PowerVariation::new),
        entry("sinusoidal", SinusoidalVariation::new),
        entry("spherical", SphericalVariation::new),
        entry("spiral", SpiralVariation::new),
        entry("swirl", SwirlVariation::new),
        entry("tangent", TangentVariation::new),
        entry("fan", FanVariation::new),
        entry("popcorn", PopcornVariation::new),
        entry("rings", RingsVariation::new),
        entry("waves", WavesVariation::new),
        entry("rings2", Rings2Variation::new),
        entry("curl", CurlVariation::new),
        entry("perspective", PerspectiveVariation::new),
        entry("blob", BlobVariation::new),
        entry("pdj", PdjVariation::new)
    );

    private static final Map<String, Function<Random, Variation<Affine>>> RANDOM = Map.ofEntries(
        entry("blur", BlurVariation::new),
        entry("gaussian", GaussianVariation::new),
        entry("julia", JuliaVariation::new),
        entry("noise", NoiseVariation::new),
        entry("square", SquareVariation::new),
        entry("radial", RadialBlurVariation::new),
        entry("julia-scope", JuliaScopeVariation::new),
        entry("pie", PieVariation::new)
    );

    public static Set<String> clean() {
        return CLEAN.keySet();
    }

    public static Set<String> random() {
        return RANDOM.keySet();
    }

    public static Variation<Affine> select(final String variation, final Random random)
        throws UnknownVariationException {
        var rnd = RANDOM.get(variation);
        if (rnd != null) {
            return rnd.apply(random);
        }
        var clean = CLEAN.get(variation);
        if (clean != null) {
            return clean.get();
        }
        throw new UnknownVariationException(variation);
    }
}
