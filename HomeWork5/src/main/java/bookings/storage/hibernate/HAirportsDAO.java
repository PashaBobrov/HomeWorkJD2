package bookings.storage.hibernate;

import bookings.model.Airports;
import bookings.storage.api.DAO;
import bookings.storage.api.IJDBC_Storage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Класс HAirportsDAO
 * Реализуюет интерфейс DAO через Hibernate.
 * Получает данные таблиц Airports
 * Соединение через HibernateCreator
 * @version 1.1
 */
public class HAirportsDAO implements DAO<Airports> {
    private Session session;

    /**
     * конструктор
     * @param session
     */
    public HAirportsDAO(Session session) {
        this.session = session;
    }

    /**
     * Заглушка
     * @param airports
     * @return
     * @throws SQLException
     */
    @Override
    public Airports save(Airports airports) throws SQLException {
        return null;
    }

    /**
     * Заглушка
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Airports get(Serializable id) throws SQLException {
        return null;
    }

    /**
     * заглушка
     * @param param
     * @param limit
     * @param offset
     * @return
     * @throws SQLException
     */
    @Override
    public List findAllByFilter(Map param, int limit, int offset) throws SQLException {
        return null;
    }

    /**
     * заглушка
     * @param param
     * @return
     * @throws SQLException
     */
    @Override
    public int getSize(Map param) throws SQLException {
        return 0;
    }

    /**
     * получение листа аеропортов
     * @return List<Airports>
     * @throws SQLException
     */
    @Override
    public List<Airports> getAll() throws SQLException {
        Session session = HibernateCreator.getInstance().openSession();
        Query<Airports> query = session.createNamedQuery("airports",Airports.class);
        List<Airports> airportsList = query.list();
        return airportsList;
    }

    /**
     * заглушка
     * @param airports
     * @throws SQLException
     */
    @Override
    public void update(Airports airports) throws SQLException {

    }

    /**
     * заглушка
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }
}
