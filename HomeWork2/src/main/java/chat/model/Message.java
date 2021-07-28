package chat.model;

import java.util.Date;

/**
 * Класс описания данных Message.
 * @version 1.1
 */
public class Message {
    private String userTo;
    private String userFrom;
    private String text;
    private Date date;

    /**
     * Конструктор - создание нового объекта
     * @param userTo - Имя пользователя, который отправил сообщения
     * @param userFrom - имя получателя сообщения
     * @param text - текст сообщения
     */
    public Message(String userTo, String userFrom, String text) {
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.date = new Date();
        this.text = text;
    }

    /** геттер для поля userFrom
     * @return userFrom
     */
    public String getUserFrom() {
        return userFrom;
    }

    /** геттер для поля userTo
     * @return userTo
     */
    public String getUserTo() {
        return userTo;
    }

    /** геттер для поля text
     * @return text
     */
    public String getText() {
        return text;
    }

    /** геттер для поля date
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
