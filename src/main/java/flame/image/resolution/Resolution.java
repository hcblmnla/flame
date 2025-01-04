package flame.image.resolution;

public record Resolution(int width, int height, int compression) {

    public static Resolution of(final String resolution) throws UnknownResolutionException {
        return switch (resolution) {
            case "fhd" -> new Resolution(1920, 1080, 3);
            case "uhd" -> new Resolution(3840, 2160, 2);
            case "uhd8" -> new Resolution(7680, 4320, 1);
            case "2k" -> new Resolution(2048, 1024, 3);
            case "4k" -> new Resolution(4096, 2048, 2);
            case "8k" -> new Resolution(8192, 4096, 1);
            case "square" -> new Resolution(1440, 1440, 3);
            default -> throw new UnknownResolutionException(resolution);
        };
    }
}
