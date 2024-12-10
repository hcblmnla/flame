package flame.algorithm.generator;

import flame.image.ColoredImage;
import flame.image.ColoredPixel;
import flame.image.Image;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SingleThreadGenerator extends AbstractGenerator {

    @Override
    public void handleSamples(final int threads, final Runnable sampler) {
        sampler.run();
    }

    @Override
    public Image<ColoredPixel> empty() {
        return new ColoredImage(width, height, compression, background);
    }
}
