package sessionOrCookies.dto;

import java.io.Serializable;

/**
 * Класс данных Person.
 * @version 1.1
 */
public class Person implements Serializable {
    private String lastName;
    private String firstName;
    private int age;

    /**
     * Конструктор - создание нового объекта
     * @param firstName - имя
     * @param lastName - фамилия
     * @param age - возраст
     */
    public Person(String firstName, String lastName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    /** геттер для поля lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /** геттер для поля firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /** геттер для поля age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Функция возвращает объект преобразованный в строку
     * @return возвращает объект в виде строку
     */
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName +
                ", lastName=" + lastName +
                ", age='" + age +
                '}';
    }
}
