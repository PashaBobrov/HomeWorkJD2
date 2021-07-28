package chat.view;

import chat.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserFlowTest {

//    @Test
//    void getINSTANCE() {
//        assertNotNull(UserFlow.getINSTANCE());
//    }
//
//    @Test
//    void add() {
//        try {
//            UserFlow.getINSTANCE().add(new User("user","user666","123",new Date()));
//            assertTrue(true);
//        } catch (Exception e) {
//            assertTrue(false);
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource("dataProvider")
//    void getUser(String val) {
//        UserFlow.getINSTANCE().add(new User("",val,"",new Date()));
//        assertNotNull(UserFlow.getINSTANCE().getUser(val));
//    }
//
//    @ParameterizedTest
//    @MethodSource("dataProvider")
//    void isNewUser(String val) {
//        UserFlow.getINSTANCE().add(new User("",val,"",new Date()));
//        assertFalse(UserFlow.getINSTANCE().isNewUser(val));
//    }
//
//    static Stream<Arguments> dataProvider() {
//        return Stream.of(
//                Arguments.of("Admin"),
//                Arguments.of("User")
//        );
//    }
}