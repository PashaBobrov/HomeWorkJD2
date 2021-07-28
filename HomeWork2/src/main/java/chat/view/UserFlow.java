package chat.view;

import chat.model.Message;
import chat.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFlow {
    /** синглтон, поле для хранения единственного объекта класса  */
    private static final UserFlow INSTANCE = new UserFlow();
    /** список для хранения сообщений*/
    private List<User> userlist = new ArrayList<>();
    /**
     * Конструктор - создание нового объекта
     */
    private UserFlow UserFlow() {
        return this;
    }

    /** геттер синглтона
     * @return объект класса
     */
    public static UserFlow getINSTANCE() {
        return INSTANCE;
    }

    /** добавления нового пользователя в список
     * @param  user - пользователь
     */
    public void add(User user) {
        userlist.add(user);
    }
    /**
     * Функция возвращает пользователя по логину
     * @param login - логин пользователя
     * @return возвращает пользователя, если есть, в противном случае null
     */
    public User getUser(String login) {
        return userlist.stream()
                .filter((u)->u.getLogin().equals(login))
                .findFirst().orElse(null);
    }

    /**
     * Функция возвращает true, если пользователя нет в списке пользователей
     * @param login - логин пользователя
     * @return возвращает true, если есть false
     */
    public boolean isNewUser(String login) {
        return userlist.stream()
                .filter((u)->u.getLogin().equals(login))
                .findFirst().isEmpty();
    }


}
