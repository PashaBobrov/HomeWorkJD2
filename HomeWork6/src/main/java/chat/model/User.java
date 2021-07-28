package chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс описания данных User.
 * @version 1.1
 */
@Entity(name = "User")
@Table(name = "users", schema = "chat")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    /**
     * Конструктор - создание нового объекта
     * @param fio - ФИО пользователя
     * @param login - логин пользователя
     * @param password - пароль пользователя
     * @param dateOfBirth - дата рождения пользователя
     */
    public User(String fio, String login, String password, Date dateOfBirth) {
        this.fio = fio;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }


    public User() {
        this.fio = fio;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    /** геттер для поля FIO
     * @return FIO
     */
    public String getFio() {
        return fio;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFIO(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    /** геттер для поля password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    /** геттер для поля dateOfBirth
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Функция возвращает объект преобразованный в строку
     * @return возвращает объект в виде строку
     */
    @Override
    public String toString() {
        return "User{" +
                "fio='" + fio + '\'' +
                '}';
    }
}
