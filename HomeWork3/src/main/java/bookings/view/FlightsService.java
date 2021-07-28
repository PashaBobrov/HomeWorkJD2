package bookings.view;

import bookings.storage.JDBC_Storage;
import bookings.storage.api.IJDBC_Storage;
import bookings.view.api.IAirportsService;
import bookings.view.api.IFlightsService;

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
    /**Храним соединение с БД*/
    private final IJDBC_Storage jdbc;

    /**
     * Конструктор - создание нового объекта
     */
    private FlightsService() {
        this.jdbc = JDBC_Storage.getInstance();
    }
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static IFlightsService getInstance() {
        return instance;
    }

    /** Получение листа с данными полетов
     * @return List<Map<String, String>>
     */
    public List<Map<String,String>> getFlights() {
        return jdbc.getAirports();
    }

    /** Получение листа с данными полетов по фильтру
     * @param param - мапа параметров
     * @param limit - лимит
     * @param offset - смещение, начальный элемент
     * @return List<Map<String, String>>
     */
    @Override
    public List<Map<String, String>> getFlights(Map<String, String[]> param, int limit, int offset) {
        return jdbc.getFlights(param, limit, offset);
    }

    /** Получение листа с данными полетов по фильтру
     * @param param - мапа параметров
     * @return List<Map<String, String>>
     */
    @Override
    public int getSizeFlights(Map<String, String[]> param) {
        return jdbc.getSizeFlights(param);
    }
}
