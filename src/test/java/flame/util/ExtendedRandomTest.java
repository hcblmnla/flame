package flame.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExtendedRandomTest {

    @Test
    void elementShouldThrowExceptionOnEmptyList() {
        // Given
        var empty = List.of();
        var random = new Random();

        // Then
        assertThrows(
            IllegalArgumentException.class,
            () -> ExtendedRandom.element(empty, random)
        );
    }

    @Test
    void elementShouldReturnRandomListItem() {
        // Given
        var set = new HashSet<>();
        var random = new Random();
        var list = IntStream.range(0, 100)
            .boxed()
            .toList();

        // When
        for (int i = 0; i < 100; i++) {
            set.add(ExtendedRandom.element(list, random));
        }

        // Then
        assertThat(set.size())
            .isGreaterThan(1);
    }
}
