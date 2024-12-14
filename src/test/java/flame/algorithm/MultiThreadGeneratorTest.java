package flame.algorithm;

import flame.algorithm.generator.AbstractFlameGenerator;
import flame.algorithm.generator.MultiThreadGenerator;
import flame.algorithm.generator.SingleThreadGenerator;
import flame.algorithm.processor.LogGammaCorrection;
import flame.function.Variation;
import flame.function.affine.Affine;
import flame.function.variation.clean.SphericalVariation;
import flame.image.ColoredPixel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static flame.algorithm.generator.AbstractFlameGenerator.updateColor;
import static org.assertj.core.api.Assertions.assertThat;

class MultiThreadGeneratorTest {

    private static AbstractFlameGenerator getFromBuilder(
        final AbstractFlameGenerator.AbstractFlameGeneratorBuilder<?, ?> builder
    ) {
        return builder
            .width(1000)
            .height(1000)
            .compression(1)
            .processor(new LogGammaCorrection())
            .background(Color.BLACK)
            .random(ThreadLocalRandom.current())
            .build();
    }

    private static long measureExecutionTime(final Callable<?> callable) throws Exception {
        long start = System.nanoTime();
        callable.call();
        return System.nanoTime() - start;
    }

    @Test
    void handleShouldWorkWithSingleThreadToo() throws InterruptedException {
        // Given
        var single = SingleThreadGenerator.builder().build();
        var multi = MultiThreadGenerator.builder().build();
        var counter = new AtomicInteger();

        // When
        single.handleSamples(1, counter::incrementAndGet);
        multi.handleSamples(1, counter::incrementAndGet);

        // Then
        assertThat(counter.get())
            .isEqualTo(2);
    }

    @Test
    void handleShouldSubmitAllTasks() throws InterruptedException {
        // Given
        var generator = MultiThreadGenerator.builder().build();
        var counter = new AtomicInteger();
        int threads = 12;

        // When
        generator.handleSamples(threads, counter::incrementAndGet);

        // Then
        assertThat(counter.get())
            .isEqualTo(12);
    }

    @Test
    void multiThreadingShouldWorkFasterThenSingle() throws Exception {
        // Given
        var single = getFromBuilder(SingleThreadGenerator.builder());
        var multi = getFromBuilder(MultiThreadGenerator.builder());
        var random = ThreadLocalRandom.current();

        var affine = Affine.defaults(random);
        var vars = List.of((Variation<Affine>) new SphericalVariation());

        int samples = 100_000;
        int it = 10;

        // When
        long singleTime = measureExecutionTime(() -> single.generate(affine, vars, samples, it, 1, 1));
        long multiTime = measureExecutionTime(() -> multi.generate(affine, vars, samples, it, 1, 8));

        // Then
        assertThat(multiTime)
            .isLessThan(singleTime);
    }

    @Test
    void updateColorShouldCalculateAverageColor() {
        // Given
        var random = ThreadLocalRandom.current();
        var affine = new Affine(new Color(10, 10, 10), random);
        var pixel = new ColoredPixel(new Color(20, 20, 20));

        // When
        var first = updateColor(affine, pixel);
        var second = updateColor(affine, pixel.hit());

        // Then
        assertThat(first.get())
            .isEqualTo(affine.color());

        assertThat(second)
            .isEqualTo(new ColoredPixel(15, 15, 15, 1));
    }
}
