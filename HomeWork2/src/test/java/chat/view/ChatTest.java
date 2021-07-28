package chat.view;

import chat.model.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    @Test
    void getINSTANCE() {
        assertNotNull(Chat.getINSTANCE());
    }

    @Test
    void add() {
        try {
            Chat.getINSTANCE().add(new Message("","",""));
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void getMessagelist(String val) {
        try {
            Chat chat = Chat.getINSTANCE();
            chat.add(new Message(val,"",""));
            assertTrue(chat.getMessagelist(val).size() > 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Admin"),
                Arguments.of("User")
        );
    }
}