import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @ParameterizedTest
//    @CsvSource({"null,1,1"})
    void Horse(){
        assertThrows(IllegalArgumentException.class,
                ()-> Horse());
    }

}