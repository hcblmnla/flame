package flame.algorithm;

import flame.algorithm.generator.AbstractFlameGenerator;
import flame.algorithm.generator.MultiThreadGenerator;
import flame.algorithm.generator.SingleThreadGenerator;
import flame.function.affine.Affine;
import flame.image.ColoredPixel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class MultiThreadGeneratorTest {

    @Test
    void handleShouldWorkWithSingleThreadToo() throws InterruptedException {
        // Given
        var single = SingleThreadGenerator.builder().nThreads(1).build();
        var multi = MultiThreadGenerator.builder().nThreads(8).build();
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
        var generator = MultiThreadGenerator.builder().nThreads(8).build();
        var counter = new AtomicInteger();
        int threads = 12;

        // When
        generator.handleSamples(threads, counter::incrementAndGet);

        // Then
        assertThat(counter.get())
            .isEqualTo(12);
    }

    @Test
    void updateColorShouldCalculateAverageColor() {
        // Given
        var random = ThreadLocalRandom.current();
        var affine = new Affine(new Color(10, 10, 10), random);
        var pixel = new ColoredPixel(new Color(20, 20, 20));

        // When
        var first = AbstractFlameGenerator.updateColor(affine, pixel);
        var second = AbstractFlameGenerator.updateColor(affine, pixel.hit());

        // Then
        assertThat(first.get())
            .isEqualTo(affine.color());

        assertThat(second)
            .isEqualTo(new ColoredPixel(15, 15, 15, 1));
    }
}
