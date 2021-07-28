package bookings.storage.api;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс DAO.
 * Объект доступа к данным - это в основном объект или интерфейс,
 * который обеспечивает доступ к базовой базе данных или любому другому постоянному хранилищу.
 * @version 1.1
 */
public interface DAO<T> {
    T save(T t) throws SQLException;
    T get(Serializable id) throws SQLException;
    List<T> getAll() throws SQLException;
    void update(T t) throws SQLException;
    int delete(Serializable id) throws SQLException;
}
