package view;

import bookings.model.Flights;
import bookings.view.FlightsService;
import bookings.view.api.IFlightsService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FligtsServiceTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    public void getFlights(Map<String, String[]> inParam) {
        IFlightsService flightsService = FlightsService.getInstance();
        List<Flights> listFlights = flightsService.getFlights(inParam,25,0);
        assertEquals(25,listFlights.size());
    }

    static Stream<Arguments> dataProvider() {
        Map<String, String[]> hm = new HashMap<>();
        hm.put("airportDeparture", new String[]{"DME"});
        hm.put("airportArrival", new String[]{"KZN"});
        hm.put("dateDeparture", new String[]{""});
        hm.put("dateArrival", new String[]{""});

        return Stream.of(
                Arguments.of(hm)
        );
    }

}
