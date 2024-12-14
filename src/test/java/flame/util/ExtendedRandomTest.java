package flame.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static flame.util.ExtendedRandom.element;
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
            () -> element(empty, random)
        );
    }

    @Test
    void elementShouldReturnRandomListItem() {
        // Given
        var set = new HashSet<>();
        var random = new Random();
        var list = Stream.iterate(0, i -> i + 1)
            .limit(100)
            .toList();

        // When
        for (int i = 0; i < 100; i++) {
            set.add(element(list, random));
        }

        // Then
        assertThat(set.size())
            .isGreaterThan(1);
    }
}
