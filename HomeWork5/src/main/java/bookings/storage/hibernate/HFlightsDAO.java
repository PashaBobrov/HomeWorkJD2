package bookings.storage.hibernate;

import bookings.model.Flights;
import bookings.storage.api.DAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Класс HFlightsDAO
 * Реализуюет интерфейс DAO через Hibernate.
 * Получает данные таблиц Flights
 * Соединение через HibernateCreator
 * @version 1.1
 */
public class HFlightsDAO implements DAO<Flights> {
    private Session session;

    /**
     * конструктор
     * @param session
     */
    public HFlightsDAO(Session session) {
        this.session = session;
    }

    /**
     * заглушка
     * @param flights
     * @return
     * @throws SQLException
     */
    @Override
    public Flights save(Flights flights) throws SQLException {
        return null;
    }

    /**
     * заглушка
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Flights get(Serializable id) throws SQLException {
        return null;
    }

    /**
     * Получение листа полетов по фильтру
     * @param param
     * @param limit
     * @param offset
     * @return
     * @throws SQLException
     */
    @Override
    public List<Flights> findAllByFilter(Map<String, String[]> param, int limit, int offset) throws SQLException {
        String sql = "FROM flights\n" +
                "WHERE departure_airport = :param1 AND arrival_airport = :param2 AND 1=1 AND 2=2\n" +
                "ORDER BY flight_id ASC";
        String param1 = param.get("airportDeparture")[0];
        String param2 = param.get("airportArrival")[0];
        Date param3 = getSQLDate(param.get("dateDeparture")[0]);
        Date param4 = getSQLDate(param.get("dateArrival")[0]);
        if (param3 != null) {
            sql = sql.replace("1=1","date_trunc('day',flights.scheduled_departure) = :param3");
        }
        if (param4 != null) {
            sql = sql.replace("2=2","date_trunc('day',flights.scheduled_arrival) = :param4");
        }

        Query query = session.createQuery(sql);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setParameter("param1", param1);
        query.setParameter("param2", param2);
        if (param3 != null) {
            query.setParameter("param3", param3);
        }
        if (param4 != null) {
            query.setParameter("param4", param4);
        }

        List<Flights> flightsList = query.list();
        return flightsList;
    }

    /**
     * получение количество записей полетов по фильтру
     * @param param
     * @return
     * @throws SQLException
     */
    @Override
    public int getSize(Map<String, String[]> param) throws SQLException {
        String sql = " SELECT count(*) FROM flights\n" +
                "WHERE departure_airport = :param1 AND arrival_airport = :param2 AND 1=1 AND 2=2";
        String param1 = param.get("airportDeparture")[0];
        String param2 = param.get("airportArrival")[0];
        Date param3 = getSQLDate(param.get("dateDeparture")[0]);
        Date param4 = getSQLDate(param.get("dateArrival")[0]);
        if (param3 != null) {
            sql = sql.replace("1=1","date_trunc('day',flights.scheduled_departure) = :param3");
        }
        if (param4 != null) {
            sql = sql.replace("2=2","date_trunc('day',flights.scheduled_arrival) = :param4");
        }
        Query query = session.createQuery(sql);

        query.setParameter("param1", param1);
        query.setParameter("param2", param2);
        if (param3 != null) {
            query.setParameter("param3", param3);
        }
        if (param3 != null) {
            query.setParameter("param4", param4);
        }

        int count = (int) query.uniqueResult();
        return count;
    }

    /**
     * получение листа полетов
     * @return
     * @throws SQLException
     */
    @Override
    public List<Flights> getAll() throws SQLException {
        Query query = session.createQuery(" FROM flights");
        List<Flights> flightsList = query.list();
        return flightsList;
    }

    /**
     * заглушка
     * @param flights
     * @throws SQLException
     */
    @Override
    public void update(Flights flights) throws SQLException {

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

    /**
     * получение даты из строки
     * @param param строка
     * @return Date
     */
    static private Date getSQLDate(String param) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        try {
            //java.sql.Timestamp timeStampDate = new Timestamp(format.parse(param).getTime());
            return new Date(format.parse(param).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
