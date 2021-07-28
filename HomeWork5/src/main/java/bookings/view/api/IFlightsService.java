package bookings.view.api;

import bookings.model.Flights;

import java.util.List;
import java.util.Map;
/**
 * Интерфейс IFlightsService.
 * интерфейс для реализации листа полетов
 * @version 1.1
 */
public interface IFlightsService {
    public List<Flights> getFlights(Map<String,String[]> param, int limit, int offset);
    public int getSizeFlights(Map<String,String[]> param);
}
