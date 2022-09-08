import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void horseWithNullOrEmptyName(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1.0, 1.0)
        );

        if (name == null) {
            assertEquals("Name cannot be null.", exception.getMessage());
        } else if (name.isBlank()) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

    }

    @ParameterizedTest
    @CsvSource({"-1,1", "1,-1"})
    void horseWithNegativeSpeedOrDistance(double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name", speed, distance)
        );

        if (speed < 0)
            assertEquals("Speed cannot be negative.", exception.getMessage());
        else if (distance < 0)
            assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getHorseNameTest() {
        String horseName = "Horse1";
        Horse horse = new Horse(horseName, 1, 1);
        assertEquals(horse.getName(), horseName);
    }

    @Test
    void getHorseSpeedTest() {
        double horseSpeed = 2.7;
        Horse horse = new Horse("Horse1", horseSpeed, 1);
        assertEquals(horse.getSpeed(), horseSpeed);
    }

    @Test
    void getHorseDistanceTest() {
        double horseDistance = 3;
        Horse horse = new Horse("Horse1", 1, horseDistance);
        assertEquals(horse.getDistance(), horseDistance, "Parameter distance not equals getDistance().");

        Horse horse1 = new Horse("Horse1", 1);
        assertEquals(horse1.getDistance(), 0, "getDistance() should return 0.");
    }

    @Test
    void moveUseGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
