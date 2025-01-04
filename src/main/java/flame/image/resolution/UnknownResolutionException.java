package flame.image.resolution;

import flame.util.UnknownArgumentException;

public class UnknownResolutionException extends UnknownArgumentException {

    public UnknownResolutionException(final String resolution) {
        super("resolution", resolution);
    }
}
