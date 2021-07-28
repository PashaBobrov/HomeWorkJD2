package chat.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @ParameterizedTest
    @MethodSource("dataProvider")
    void getUserFrom(String val) {
        assertEquals(val,new Message("",val,"").getUserFrom());
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void getUserTo(String val) {
        assertEquals(val,new Message(val,"","").getUserTo());
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void getText(String val) {
        assertEquals(val,new Message("","",val).getText());
    }

    @ParameterizedTest
    @MethodSource("dataProviderDate")
    void getDate(Date outVal) {
        assertEquals(outVal.getTime(),new Message("","","").getDate().getTime(),1f);
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Admin"),
                Arguments.of("User")
        );
    }

    static Stream<Arguments> dataProviderDate() {
        return Stream.of(
                Arguments.of(new Date()),
                Arguments.of(new Date())
        );
    }
}