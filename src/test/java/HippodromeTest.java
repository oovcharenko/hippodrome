import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void hippodromeNullHorsesException() {
        try {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
            assertEquals("Horses cannot be null.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }
    @Test
    void hippodromeEmptyHorsesException() {
        try {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
            assertEquals("Horses cannot be empty.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse" + i, 1, 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        horses.stream().forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinnerTest() {
        Horse horse1 = new Horse("horse", 1, 1);
        Horse horse2 = new Horse("horse", 1, 10);
        Horse horse3 = new Horse("horse", 1, 20);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertSame(horse3, hippodrome.getWinner());
    }
}