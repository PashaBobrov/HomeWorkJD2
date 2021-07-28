package bookings.view.api;

import bookings.model.Airports;

import java.util.List;
import java.util.Map;
/**
 * Интерфейс IAirportsService.
 * интерфейс для реализации листа аеропортов
 * @version 1.1
 */
public interface IAirportsService {
    List<Airports> getAirports();
}
