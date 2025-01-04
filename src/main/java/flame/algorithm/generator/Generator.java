package flame.algorithm.generator;

import flame.function.LinearFunction;
import flame.function.Variation;
import flame.image.Image;

import java.util.List;

/**
 * A common interface for image generation.
 *
 * @param <I> type of the image pixels
 * @param <F> type of the linear function, e.g. flame generators are using
 *            {@link flame.function.affine.Affine}, which contain nonlinear methods
 * @author alnmlbch
 */
@FunctionalInterface
public interface Generator<I, F extends LinearFunction> {

    Image<I> generate(
        List<F> linear,
        List<Variation<F>> nonLinear,
        int samples,
        int iterations,
        int symmetries
    ) throws InterruptedException;
}
