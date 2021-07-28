package chat.view;

import chat.Storage.DataSourceCreator;
import chat.Storage.JDBCMessageDAO;
import chat.Storage.api.DAO;
import chat.model.Message;
import chat.model.User;

import java.sql.SQLException;
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
    //private List<Message> messagelist = new ArrayList<>();

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
        DAO<Message> messageDAO = new JDBCMessageDAO(DataSourceCreator.getInstance());
        try {
            messageDAO.save(message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Функция возвращает список сообщения пользователю
     * @param user - логин пользователя
     * @return возвращает список сообщений
     */
    public List<Message> getMessagelist(String user) {
        List<Message> messagelist = null;
        DAO<Message> messageDAO = new JDBCMessageDAO(DataSourceCreator.getInstance());
        try {
            messagelist = messageDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messagelist.stream()
                .filter((m) -> m.getUserTo().equals(user))
                .collect(Collectors.toList());
    }
}
