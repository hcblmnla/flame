package flame.algorithm.generator;

import flame.image.AtomicColoredImage;
import flame.image.ColoredPixel;
import flame.image.Image;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuperBuilder
public class MultiThreadGenerator extends AbstractGenerator {

    private static final int AWAITING_MINUTES = 5;

    @Override
    public void handleSamples(final int threads, final Runnable sampler)
        throws InterruptedException {
        // newVirtualThreadPerTaskExecutor ?
        try (var executor = Executors.newFixedThreadPool(threads)) {
            Collections.nCopies(threads, sampler)
                .forEach(executor::submit);
            executor.shutdown();
            if (!executor.awaitTermination(AWAITING_MINUTES, TimeUnit.MINUTES)) {
                throw new InterruptedException("Timed out");
            }
        }
    }

    @Override
    public Image<ColoredPixel> empty() {
        return new AtomicColoredImage(width, height, compression, background);
    }
}
