package chat.model;

import java.util.Date;

/**
 * Класс описания данных User.
 * @version 1.1
 */
public class User {
    private String FIO;
    private String login;
    private String password;
    private Date dateOfBirth;

    /**
     * Конструктор - создание нового объекта
     * @param FIO - ФИО пользователя
     * @param login - логин пользователя
     * @param password - пароль пользователя
     * @param dateOfBirth - дата рождения пользователя
     */
    public User(String FIO, String login, String password, Date dateOfBirth) {
        this.FIO = FIO;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    /** геттер для поля FIO
     * @return FIO
     */
    public String getFIO() {
        return FIO;
    }

    /** геттер для поля login
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /** проверка пароля  польователя
     * @param password - строка пароль
     * @return true, если пароль верен
     */
    public boolean getAcсess(String password) {
        return password.equals(this.password);
    }

    /**
     * Функция возвращает объект преобразованный в строку
     * @return возвращает объект в виде строку
     */
    @Override
    public String toString() {
        return "User{" +
                "FIO='" + FIO + '\'' +
                '}';
    }
}
