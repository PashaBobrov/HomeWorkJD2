package bookings.storage.JDBC;

import bookings.model.Airports;
import bookings.model.Flights;
import bookings.storage.api.DAO;
import bookings.storage.api.IJDBC_Storage;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс HFlightsDAO
 * Реализуюет интерфейс DAO через JDBC.
 * Получает данные таблиц Flights
 * Соединение через IJDBC_Storage
 * @version 1.1
 */
public class JFlightsDAO implements DAO<Flights> {
    Connection connection;

    public JFlightsDAO(IJDBC_Storage ds) {
        this.connection = ds.getConnection();
    }
    @Override
    public Flights save(Flights flights) throws SQLException {
       return null;
    }

    @Override
    public Flights get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public List<Flights> getAll() throws SQLException {
        String sql = "SELECT * FROM bookings.flights;";
        List<Flights> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flights flights = new Flights();
                flights.setFlightId(rs.getInt("flights_id"));
                flights.setFlightNo(rs.getString("flights_no"));
                flights.setScheduledArrival(rs.getString("scheduled_departure"));
                flights.setDepartureAirport(rs.getString("departure_airport"));
                flights.setArrivalAirport(rs.getString("arrival_airport"));
                flights.setStatus(rs.getString("status"));
                flights.setAircraftCode(rs.getString("aircraft_code"));
                flights.setActualDeparture(rs.getString("actual_departure"));
                flights.setAircraftCode(rs.getString("actual_arrival"));

                list.add(flights);
            }
        }
        return list;
    }

    @Override
    public List<Flights> findAllByFilter(Map<String, String[]> param,int limit, int offset) throws SQLException {

        String sql = " SELECT * FROM bookings.flights\n" +
                "WHERE departure_airport = ? AND arrival_airport = ? AND 1=1 AND 2=2\n" +
                "ORDER BY flight_id ASC LIMIT ? OFFSET ?";
        String param1 = param.get("airportDeparture")[0];
        String param2 = param.get("airportArrival")[0];
        Date param3 = getSQLDate(param.get("dateDeparture")[0]);
        Date param4 = getSQLDate(param.get("dateArrival")[0]);
        List<Flights> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            int iParam = 0;
            stm.setString(++iParam,param1);
            stm.setString(++iParam,param2);
            if (param3 != null) {
                stm.setDate(++iParam,  param3);
            }
            if (param4 != null) {
                stm.setDate(++iParam,  param4);
            }
            if (limit != 0) {
                stm.setInt(++iParam,limit);
                stm.setInt(++iParam,offset);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flights flights = new Flights();
                flights.setFlightId(rs.getInt("flight_id"));
                flights.setFlightNo(rs.getString("flight_no"));
                flights.setScheduledDeparture(rs.getString("scheduled_departure"));
                flights.setScheduledArrival(rs.getString("scheduled_arrival"));
                flights.setDepartureAirport(rs.getString("departure_airport"));
                flights.setArrivalAirport(rs.getString("arrival_airport"));
                flights.setStatus(rs.getString("status"));
                flights.setAircraftCode(rs.getString("aircraft_code"));
                flights.setActualDeparture(rs.getString("actual_departure"));
                flights.setAircraftCode(rs.getString("actual_arrival"));

                list.add(flights);
            }
        }
        return list;
    }

    @Override
    public void update(Flights flights) throws SQLException {

    }

    public int getSize(Map<String,String[]> param) throws SQLException {
        String sql = " SELECT count(*) FROM bookings.flights\n" +
                "WHERE departure_airport = ? AND arrival_airport = ? AND 1=1 AND 2=2";
        String param1 = param.get("airportDeparture")[0];
        String param2 = param.get("airportArrival")[0];
        Date param3 = getSQLDate(param.get("dateDeparture")[0]);
        Date param4 = getSQLDate(param.get("dateArrival")[0]);
        if (param3 != null) {
            sql = sql.replace("1=1","date_trunc('day',flights.scheduled_departure) = ?");
        }
        if (param4 != null) {
            sql = sql.replace("2=2","date_trunc('day',flights.scheduled_arrival) = ?");
        }
        int result = 0;
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            int iParam = 0;
            stm.setString(++iParam,param1);
            stm.setString(++iParam,param2);
            if (param3 != null) {
                stm.setDate(++iParam,  param3);
            }
            if (param4 != null) {
                stm.setDate(++iParam,  param4);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result = Integer.parseInt(rs.getString("count"));
            }
        }
        return result;
    }


    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    static private Date getSQLDate(String param) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd/MM/yyyy");
        try {
            //java.sql.Timestamp timeStampDate = new Timestamp(format.parse(param).getTime());
            return new Date(format.parse(param).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
