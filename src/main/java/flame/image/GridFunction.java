package flame.image;

@FunctionalInterface
public interface GridFunction<I> {

    I apply(int x, int y);
}
