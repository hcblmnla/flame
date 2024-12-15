package flame.algorithm.processor;

import flame.image.Image;

import java.util.function.Consumer;

/**
 * Performs in-place {@link Image} processing.
 *
 * @param <I> type of the pixels
 * @author alnmlbch
 */
@FunctionalInterface
public interface ImageProcessor<I> extends Consumer<Image<I>> {
}
