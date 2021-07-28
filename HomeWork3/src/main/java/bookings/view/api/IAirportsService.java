package bookings.view.api;

import java.util.List;
import java.util.Map;
/**
 * Интерфейс IAirportsService.
 * интерфейс для реализации листа аеропортов
 * @version 1.1
 */
public interface IAirportsService {
    List<Map<String, String>> getAirports();
}
