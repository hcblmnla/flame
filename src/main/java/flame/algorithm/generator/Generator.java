package flame.algorithm.generator;

import flame.function.LinearFunction;
import flame.function.Variation;
import flame.image.Image;

import java.util.List;

@FunctionalInterface
public interface Generator<I, F extends LinearFunction> {

    Image<I> generate(
        List<F> linear,
        List<Variation<F>> nonLinear,
        int samples,
        int iterations,
        int symmetries,
        int nThreads
    ) throws InterruptedException;
}
