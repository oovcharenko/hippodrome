import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    @NullSource
    void hippodromeWithNullParameter(List<Horse> horses) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void getHorses() {
    }

    @Test
    void moveTest() {

    }

    @Test
    void getWinner() {
    }
}