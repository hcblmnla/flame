package flame.function;

public class UnknownVariationException extends IllegalArgumentException {

    public UnknownVariationException(final String variation) {
        super("Unknown variation: '" + variation + "'");
    }
}
