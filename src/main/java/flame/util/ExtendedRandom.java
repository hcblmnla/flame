package flame.util;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class ExtendedRandom {

    public static <V> V element(final List<V> list, final Random random) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        return list.get(random.nextInt(list.size()));
    }
}
