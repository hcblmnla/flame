package flame.algorithm.generator;

import flame.function.affine.Affine;
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
    public void setPixel(
        final int x,
        final int y,
        final Image<ColoredPixel> image,
        final Affine affine
    ) {
        var pixel = image.pixel(x, y);
        image.pixel(x, y, updateColor(affine, pixel).hit());
    }
}
