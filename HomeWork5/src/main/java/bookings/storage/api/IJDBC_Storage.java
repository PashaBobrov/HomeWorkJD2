package bookings.storage.api;

import java.sql.*;
/**
 * Интерфейс IJDBC_Storage.
 * Объект доступа сoeдинению с JDBC хранилищем
 * @version 1.1
 */
public interface IJDBC_Storage {
    public Connection getConnection();
}
