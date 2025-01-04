package flame.algorithm.processor;

import flame.image.ColoredPixel;
import flame.image.Image;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

/**
 * Logarithmic gamma correction of images consisting of {@link ColoredPixel}.
 * Cleans up the image and changes the brightness, contrast.
 *
 * @author alnmlbch
 */
@RequiredArgsConstructor
public class LogGammaCorrection implements ImageProcessor<ColoredPixel> {

    private static final double DEFAULT_GAMMA = 2.2;
    private final double gamma;

    /**
     * Creates processor with default (more stable) gamma parameter.
     */
    public LogGammaCorrection() {
        this(DEFAULT_GAMMA);
    }

    @Override
    public void accept(final Image<ColoredPixel> image) {
        double max = Math.log10(
            image
                .max(Comparator.comparing(ColoredPixel::hitCount))
                .hitCount()
        );
        image.forEachApply((x, y) -> {
            var pixel = image.pixel(x, y);

            if (pixel.hitCount() == 0) {
                return pixel;
            }

            double normalizer = reversedGammaPow(Math.log10(pixel.hitCount()) / max);
            return pixel.apply(color -> (int) (color * normalizer));
        });
    }

    /**
     * {@link Math#pow} analogy with reversed gamma.
     *
     * @param num the base
     * @return the value {@code a}<sup>{@code b}</sup>.
     */
    public double reversedGammaPow(final double num) {
        return Math.pow(num, 1 / gamma);
    }
}
