package chat.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @ParameterizedTest
    @MethodSource("dataProviderFio")
    void getFIO(String inVal, String outVal) {
        assertEquals(outVal, new User(inVal,"","",new Date()).getFio());
    }

    @ParameterizedTest
    @MethodSource("dataProviderLogin")
    void getLogin(String inVal, String outVal) {
        assertEquals(outVal, new User("",inVal,"",new Date()).getLogin());
    }

    @ParameterizedTest
    @MethodSource("dataProviderAcces")
    void getAcсess(String inVal) {
        assertTrue(new User("","",inVal,new Date()).getAcсess(inVal));
    }

    static Stream<Arguments> dataProviderFio() {
        return Stream.of(
                Arguments.of("Иванов Иван Иванович", "Иванов Иван Иванович"),
                Arguments.of("Петров Петр Петрович", "Петров Петр Петрович"),
                Arguments.of("Сидоров С.С", "Сидоров С.С")
        );
    }
    static Stream<Arguments> dataProviderLogin() {
        return Stream.of(
                Arguments.of("Squad", "Squad"),
                Arguments.of("Login", "Login"),
                Arguments.of("Admin", "Admin")
        );
    }

    static Stream<Arguments> dataProviderAcces() {
        return Stream.of(
                Arguments.of("qwerty"),
                Arguments.of("123"),
                Arguments.of("planB")
        );
    }


}