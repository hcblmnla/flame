package flame.algorithm.generator;

import flame.image.ColoredImage;
import flame.image.ColoredPixel;
import flame.image.Image;
import lombok.experimental.SuperBuilder;

import java.util.Collections;

@SuperBuilder
public final class SingleThreadGenerator extends AbstractFlameGenerator {

    @Override
    public void handleSamples(final int samples, final Runnable sampler) {
        Collections.nCopies(samples, sampler)
            .forEach(Runnable::run);
    }

    @Override
    public Image<ColoredPixel> empty() {
        return new ColoredImage(width, height, compression, background);
    }
}
