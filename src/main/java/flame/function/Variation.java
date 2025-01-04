package flame.function;

import java.util.function.BiFunction;

@FunctionalInterface
public interface Variation<T extends LinearFunction> extends BiFunction<Point, T, Point> {
}
