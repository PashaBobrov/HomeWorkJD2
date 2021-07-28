package chat.view;

import chat.model.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для храннения и получения объектов типа Message.
 * @version 1.1
 */

public class Chat {
    /** синглтон, поле для хранения единственного объекта класса  */
    private static final Chat INSTANCE = new Chat();
    /** список для хранения сообщений*/
    private List<Message> messagelist = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта
     */
    private Chat Chat() {
        return this;
    }

    /** геттер синглтона
     * @return объект класса
     */
    public static Chat getINSTANCE() {
        return INSTANCE;
    }

    /** добавления нового сообщенния в список
     * @param  message - сообщение
     */
    public void add(Message message) {
        messagelist.add(message);
    }

    /**
     * Функция возвращает список сообщения пользователю
     * @param user - имя пользователя
     * @return возвращает список сообщений
     */
    public List<Message> getMessagelist(String user) {
        return messagelist.stream()
                .filter((m) -> m.getUserTo().equals(user))
                .collect(Collectors.toList());
    }
}
