package bookings.storage.JDBC;

import bookings.storage.api.IJDBC_Storage;

import java.sql.*;
/**
 * Класс JDBC_Storage.
 * реализует интервейс @IJDBC_Storage
 * получает соединение с хранилищем данных
 * @version 1.1
 */
public class JDBC_Storage implements IJDBC_Storage {
    static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/edu";
    static final String CONNECTION_USER = "postgres";
    static final String CONNECTION_PASS = "1234";

    private static final JDBC_Storage INSTANCE = new JDBC_Storage();
    /**Храним соединение с БД*/
    private Connection connection;
    /**
     * Конструктор - создание нового объекта
     */
    private JDBC_Storage JDBC_Storage() {
        return this;
    }
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static JDBC_Storage getInstance() {
        return INSTANCE;
    }
    /** Получение подключения к хранилищу
     * @return connection
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(CONNECTION_URL,CONNECTION_USER,CONNECTION_PASS);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }


}
