package bookings.view;

import bookings.storage.JDBC_Storage;
import bookings.storage.api.IJDBC_Storage;
import bookings.view.api.IAirportsService;

import java.util.List;
import java.util.Map;

/**
 * Класс AirporstService.
 * реализует интервейс @IAirportsService
 * получает данные из таблиц аерапортов
 * @version 1.1
 */
public class AirporstService implements IAirportsService {
    /**Храним синглтон*/
    private final static IAirportsService instance = new AirporstService();
    /**Храним соединение с БД*/
    private final IJDBC_Storage jdbc;

    /**
     * Конструктор - создание нового объекта
     */
    private AirporstService() {
        this.jdbc = JDBC_Storage.getInstance();
    }
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static IAirportsService getInstance() {
        return instance;
    }

    /** Получение листа с данными аеропортов
     * @return List<Map<String, String>>
     */
    public List<Map<String, String>> getAirports() {
        return jdbc.getAirports();
    }
}
