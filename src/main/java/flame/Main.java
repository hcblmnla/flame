package flame;

import lombok.experimental.UtilityClass;
import picocli.CommandLine;

@UtilityClass
public class Main {

    public static void main(final String[] args) {
        new CommandLine(FlameCommand.class).execute(args);
    }
}
