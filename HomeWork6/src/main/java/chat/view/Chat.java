package chat.view;

import chat.model.User;
import chat.storage.api.IChatRepository;
import chat.model.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для храннения и получения объектов типа Message.
 * @version 1.1
 */

public class Chat {
    /** синглтон, поле для хранения единственного объекта класса  */
    public final IChatRepository repository;

    public Chat(IChatRepository repository) {
        this.repository = repository;
    }
    /**
     * Конструктор - создание нового объекта
     */
    /** геттер синглтона
     * @return объект класса
     */

    /** добавления нового сообщенния в список
     * @param  message - сообщение
     */
    public void add(Message message) {
        repository.save(message);
    }

    /**
     * Функция возвращает список сообщения пользователю
     * @param user - логин пользователя
     * @return возвращает список сообщений
     */
    public List<Message> getMessagelist(String user) {

        Message message = new Message();
        message.setUserTo(user);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("userTo", match -> match.exact());
        Example<Message> example = Example.of(message, matcher);
        List<Message> messagelist  = repository.findAll(example);

        return messagelist;
    }
}
