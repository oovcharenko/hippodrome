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
        try {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

            if (name == null) {
                assertEquals("Name cannot be null.", exception.getMessage());
            } else if (name.isBlank()) {
                assertEquals("Name cannot be blank.", exception.getMessage());
            }
        } catch (Exception exception) {
            fail();
        }
    }

    @ParameterizedTest
    @CsvSource({"-1,1", "1,-1"})
    void horseWithNegativeSpeedOrDistance(double speed, double distance) {
        try {
            Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", speed, distance));

            if (speed < 0)
                assertEquals("Speed cannot be negative.", exception.getMessage());
            else if (distance < 0)
                assertEquals("Distance cannot be negative.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
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
            new Horse("name", 3, 30).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.1", "0.2", "99.9"})
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double speed = 3;
            double distance = 30;
            Horse horse = new Horse("фаворит", speed, distance);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(distance + speed * random, horse.getDistance());
        }
    }
}
