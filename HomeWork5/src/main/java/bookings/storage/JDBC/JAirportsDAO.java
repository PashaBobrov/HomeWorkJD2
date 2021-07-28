package bookings.storage.JDBC;

import bookings.model.Airports;
import bookings.storage.api.DAO;
import bookings.storage.api.IJDBC_Storage;
import com.google.inject.spi.Message;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс JAirportsDAO.
 * Реализуюет интерфейс DAO через JDBC.
 * получает соединение с хранилищем данных
 * получает данные из таблиц аерапортов и полетов
 * @version 1.1
 */
public class JAirportsDAO implements DAO<Airports> {
    Connection connection;

    public JAirportsDAO(IJDBC_Storage ds) {
        this.connection = ds.getConnection();
    }
    @Override
    public Airports save(Airports airports) throws SQLException {
       return null;
    }

    @Override
    public Airports get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public int getSize(Map<String, String[]> param) throws SQLException {
        return 0;
    }

    @Override
    public List<Airports> getAll() throws SQLException {
        String sql = " SELECT ml.airport_code,\n" +
                "    ml.airport_name ->> 'ru' AS airport_name,\n" +
                "    ml.city ->> 'ru' AS city,\n" +
                "    ml.coordinates,\n" +
                "    ml.timezone\n" +
                "    FROM bookings.airports_data ml  ORDER BY city;";
        List<Airports> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Airports airports = new Airports();
                airports.setAirportCode(rs.getString("airport_code"));
                airports.setAirportName(rs.getString("airport_name"));
                airports.setCity(rs.getString("city"));
                airports.setCoordinates(rs.getString("coordinates"));
                airports.setTimezone(rs.getString("timezone"));

                list.add(airports);
            }
        }
        return list;
    }

    @Override
    public List<Airports>  findAllByFilter(Map<String, String[]> param,int limit, int offset) throws SQLException {
        String sql = "SELECT * FROM bookings.airports_data;";
        List<Airports> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Airports airports = new Airports();
                airports.setAirportCode(rs.getString("airport_code"));
                airports.setAirportName(rs.getString("airport_name"));
                airports.setCity(rs.getString("city"));
                airports.setCoordinates(rs.getString("coordinates"));
                airports.setTimezone(rs.getString("timezone"));

                list.add(airports);
            }
        }
        return list;
    }

    @Override
    public void update(Airports airports) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }
}
