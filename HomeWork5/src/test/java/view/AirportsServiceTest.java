package view;

import bookings.model.Airports;
import bookings.view.AirporstService;
import bookings.view.api.IAirportsService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirportsServiceTest {
    @Test
    public void getAirports() {
        IAirportsService airporstService = AirporstService.getInstance();
        List<Airports> listAirports = airporstService.getAirports();
        assertTrue(listAirports.size() > 10);
    }

}
