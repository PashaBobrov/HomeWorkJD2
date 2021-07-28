package bookings.view;

import bookings.model.Airports;
import bookings.storage.api.DAO;
import bookings.storage.hibernate.HAirportsDAO;
import bookings.storage.hibernate.HibernateCreator;
import bookings.view.api.IAirportsService;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс AirporstService.
 * реализует интервейс @IAirportsService
 * получает данные из таблиц аерапортов
 * @version 1.1
 */
public class AirporstService implements IAirportsService {
    /**Храним синглтон*/
    private final static IAirportsService instance = new AirporstService();
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static IAirportsService getInstance() {
        return instance;
    }

    /**
     * Получает лист аеропортов
     * @return List<Airports>
     */
    public List<Airports> getAirports() {
        List<Airports> airportsList = null;
        DAO<Airports> airportsDAO = new HAirportsDAO(HibernateCreator.getInstance().openSession());
        try {
            airportsList = airportsDAO.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return airportsList;
    }
}
