package bookings.view.api;

import bookings.storage.JDBC_Storage;
import bookings.storage.api.IJDBC_Storage;
import bookings.view.AirporstService;

import java.util.List;
import java.util.Map;
/**
 * Интерфейс IFlightsService.
 * интерфейс для реализации листа полетов
 * @version 1.1
 */
public interface IFlightsService {
    public List<Map<String,String>> getFlights(Map<String,String[]> param,int limit, int offset);
    public int getSizeFlights(Map<String,String[]> param);
}
