package chat.view;

import chat.Storage.DataSourceCreator;
import chat.Storage.JDBCUserDAO;
import chat.Storage.api.DAO;
import chat.model.User;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFlow {
    /** синглтон, поле для хранения единственного объекта класса  */
    private static final UserFlow INSTANCE = new UserFlow();
    /** список для хранения сообщений*/
    //private List<User> userlist = new ArrayList<>();
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
        DAO<User> userDAO = new JDBCUserDAO(DataSourceCreator.getInstance());
        try {
            userDAO.save(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * Функция возвращает пользователя по логину
     * @param login - логин пользователя
     * @return возвращает пользователя, если есть, в противном случае null
     */
    public User getUser(String login) {

        User result = null;
        DAO<User> userDAO =   new JDBCUserDAO(DataSourceCreator.getInstance());
        try {
            result = userDAO.get(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
//        return userlist.stream()
//                .filter((u)->u.getLogin().equals(login))
//                .findFirst().orElse(null);
    }

    /**
     * Функция возвращает true, если пользователя нет в списке пользователей
     * @param login - логин пользователя
     * @return возвращает true, если есть false
     */
    public boolean isNewUser(String login) {
        User result = null;
        DAO<User> userDAO =   new JDBCUserDAO(DataSourceCreator.getInstance());
        try {
            result = userDAO.get(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result==null;

    }


}
