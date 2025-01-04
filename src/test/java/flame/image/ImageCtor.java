package flame.image;

import java.awt.*;

@FunctionalInterface
interface ImageCtor<I> {

    Image<I> apply(int width, int height, int compression, Color background);
}
