package view;

import bookings.view.AirporstService;
import bookings.view.api.IAirportsService;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AirportsServiceTest {
    @Test
    public void getAirports() {
        IAirportsService airporstService = AirporstService.getInstance();
        List<Map<String, String>> listAirports = airporstService.getAirports();
        assertTrue(listAirports.size() > 10);
    }

}
