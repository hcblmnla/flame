package logic;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class ZermeloTest {

    public static <A> List<A> empty() {
        return List.of();
    }

    public static <A> List<A> pair(final A l, final A r) {
        return List.of(l, r);
    }

    public static <A> List<A> flatten(final List<List<A>> lists) {
        return lists.stream()
            .flatMap(Collection::stream)
            .toList();
    }

    public static <A> List<List<A>> powerSet(final List<A> list) {
        if (list.isEmpty()) {
            return List.of(empty());
        }

        final A first = list.getFirst();
        final List<List<A>> tailSet = powerSet(list.subList(1, list.size()));

        return concat(
            tailSet.stream()
                .map(sub -> concat(List.of(first), sub))
                .toList(),
            tailSet
        );
    }

    public static <A> Function<List<A>, List<A>> filter(final Predicate<? super A> predicate) {
        return list -> list.stream()
            .filter(predicate)
            .toList();
    }

    /**
     * flatten(pair(a, b))
     */
    private static <A> List<A> concat(final List<A> a, final List<A> b) {
        return flatten(pair(a, b));
    }

    /**
     * filter(P(b) := forall x. x in a -> b in x)(flatten A)
     */
    public static <A> List<A> a(final List<List<A>> a) {
        return filter(
            (A b) -> a.stream()
                .allMatch(x -> x.contains(b))
        )
            .apply(flatten(a));
    }

    //////////////////////////////////////////////////

    /**
     * filter(P(x) := not x in b)(A)
     */
    public static <A> List<A> b(final List<A> a, final List<A> b) {
        return filter((A x) -> !b.contains(x)).apply(a);
    }

    /**
     * flatten(pair(a \ b, b \ a))
     */
    public static <A> List<A> bSym(final List<A> a, final List<A> b) {
        return concat(b(a, b), b(b, a));
    }

    /**
     * P(a) := not a == empty & forall x. x in a -> a \ {x} == empty
     */
    private static <A> boolean sizeIsOne(final List<A> a) {
        return !a.isEmpty() && a.stream()
            .allMatch(x -> b(a, List.of(x)).isEmpty());
    }

    /**
     * P2(a) := forall x. x in a -> P(a \ {x})
     */
    private static <A> boolean sizeIsTwo(final List<A> a) {
        return a.stream()
            .allMatch(x -> sizeIsOne(b(a, List.of(x))));
    }

    /**
     * flatten(pair(a * {0}, b * {1}))
     */
    public static <A> List<List<A>> c(final List<A> a, final List<A> b, final A zero, final A one) {
        return concat(
            d(a, List.of(zero)),
            d(b, List.of(one))
        );
    }

    /**
     * filter(P(set) := sizeIsTwo(set) & sizeIsOne(set \ b))(powerSet(flatten(pair(a, b))))
     */
    public static <A> List<List<A>> d(final List<A> a, final List<A> b) {
        return filter((List<A> set) -> sizeIsTwo(set) && sizeIsOne(b(set, b)))
            .apply(powerSet(concat(a, b)));
    }

    @Test
    void flattenTest() {
        assertThat(flatten(List.of(
            List.of(1, 2, 3),
            List.of(4),
            List.of(5, 6)
        )))
            .isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    void powerSetTest() {
        assertThat(powerSet(List.of(1, 2, 3)))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    List.of(),
                    List.of(1),
                    List.of(2),
                    List.of(3),
                    List.of(1, 2),
                    List.of(1, 3),
                    List.of(2, 3),
                    List.of(1, 2, 3)
                )
            );
    }

    @Test
    void aTest() {
        assertThat(a(List.of(
            List.of("a", "b", "c"),
            List.of("b", "c"),
            List.of("b", "x")
        )))
            .containsOnly("b");
    }

    @Test
    void bTest() {
        final List<Integer> a = List.of(1, 2, 3, 4, 5);
        final List<Integer> b = List.of(4, 7, 8, 9);

        assertThat(b(a, b))
            .isEqualTo(List.of(1, 2, 3, 5));

        assertThat(bSym(a, b))
            .isEqualTo(List.of(1, 2, 3, 5, 7, 8, 9));

        assertThat(b(List.of(1, 1, 1), List.of(1)))
            .isEqualTo(List.of());
    }

    @Test
    void cTest() {
        assertThat(c(
            List.of("a", "bc", "de"),
            List.of("x", "y"),
            "_ZERO_", "_ONE_"
        ))
            .containsOnly(
                List.of("a", "_ZERO_"),
                List.of("bc", "_ZERO_"),
                List.of("de", "_ZERO_"),
                List.of("x", "_ONE_"),
                List.of("y", "_ONE_")
            );
    }

    @Test
    void dTest() {
        assertThat(d(
            List.of(1, 2, 3),
            List.of(5, 6, 7, 8)
        ))
            .containsExactlyInAnyOrderElementsOf(
                List.of(
                    List.of(1, 5),
                    List.of(1, 6),
                    List.of(1, 7),
                    List.of(1, 8),
                    List.of(2, 5),
                    List.of(2, 6),
                    List.of(2, 7),
                    List.of(2, 8),
                    List.of(3, 5),
                    List.of(3, 6),
                    List.of(3, 7),
                    List.of(3, 8)
                )
            );
    }
}
