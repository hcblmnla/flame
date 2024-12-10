package flame.function;

public class UnknownVariationException extends RuntimeException {

    public UnknownVariationException(final String variation) {
        super("Unknown variation: '" + variation + "'");
    }
}
