package flame.image;

/**
 * Represents an operation that accepts a grid coordinates and returns no result.
 *
 * @author alnmlbch
 */
@FunctionalInterface
public interface GridConsumer {

    void accept(int x, int y);
}
