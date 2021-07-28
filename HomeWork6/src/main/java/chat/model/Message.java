package chat.model;

import org.hibernate.annotations.GenerationTime;

import javax.annotation.processing.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Класс описания данных Message.
 * @version 1.1
 */

@Entity(name = "Message")
@Table(name = "messages", schema = "chat")
public class Message implements Serializable {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "userto")
    private String userTo;
    @Column(name = "userfrom")
    private String userFrom;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
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

    public Message() {
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

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
