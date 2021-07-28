package bookings.view;

import bookings.model.Flights;
import bookings.storage.JDBC.JFlightsDAO;
import bookings.storage.JDBC.JDBC_Storage;
import bookings.storage.api.DAO;
import bookings.storage.hibernate.HFlightsDAO;
import bookings.storage.hibernate.HibernateCreator;
import bookings.view.api.IFlightsService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Класс FlightsService.
 * реализует интервейс @IFlightsService
 * получает данные из таблиц полетов, а также размер данных
 * @version 1.1
 */
public class FlightsService implements IFlightsService {
    /**Храним синглтон*/
    private final static IFlightsService instance = new FlightsService();
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static IFlightsService getInstance() {
        return instance;
    }

    /**
     * получает лист полетов по фильтру
     * @param param
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public List<Flights> getFlights(Map<String, String[]> param, int limit, int offset) {
        List<Flights> flightsList = null;
        DAO<Flights> flightsDAO = new HFlightsDAO(HibernateCreator.getInstance().openSession());
        try {
            flightsList = flightsDAO.findAllByFilter(param,limit,offset);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flightsList;
    }

    /**
     * получает количество записей полетов по фильтру
     * @param param
     * @return
     */
    @Override
    public int getSizeFlights(Map<String, String[]> param) {
        DAO<Flights> flightsDAO = new JFlightsDAO(JDBC_Storage.getInstance());
        try {
            return flightsDAO.getSize(param);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
