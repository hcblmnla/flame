package flame.util;

public abstract class UnknownArgumentException extends Exception {

    public UnknownArgumentException(final String type, final String argument) {
        super("Unknown %s: '%s'".formatted(type, argument));
    }
}
