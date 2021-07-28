package bookings.storage;

import bookings.storage.api.IJDBC_Storage;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс JDBC_Storage.
 * реализует интервейс @IJDBC_Storage
 * получает соединение с хранилищем данных
 * получает данные из таблиц аерапортов и полетов
 * @version 1.1
 */
public class JDBC_Storage implements IJDBC_Storage {
    static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/edu";
    static final String CONNECTION_USER = "postgres";
    static final String CONNECTION_PASS = "1234";
    static final String SQLSelectAir = " SELECT ml.airport_code,\n" +
            "    ml.airport_name ->> 'ru' AS airport_name,\n" +
            "    ml.city ->> 'ru' AS city,\n" +
            "    ml.coordinates,\n" +
            "    ml.timezone\n" +
            "    FROM bookings.airports_data ml  ORDER BY city;";

    /**
     * синглтон на объект класса
     */
    private static final JDBC_Storage INSTANCE = new JDBC_Storage();
    /**Храним соединение с БД*/
    private Connection connection;
    /**
     * Конструктор - создание нового объекта
     */
    private JDBC_Storage JDBC_Storage() {
        return this;
    }
    /**
     *реализация паттерна синглтон получение объекта
     */
    public static JDBC_Storage getInstance() {
        return INSTANCE;
    }

    /** Получение подключения к хранилищу
     * @return connection
     */
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(CONNECTION_URL,CONNECTION_USER,CONNECTION_PASS);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    /** Получение листа аеропортов
     * @return  List<Map<String,String>>
     */
    public List<Map<String,String>> getAirports() {
        List<Map<String,String>> result = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery(SQLSelectAir);){
            ResultSetMetaData meta = rs.getMetaData();
            int iColumnCount = meta.getColumnCount();
            while (rs.next()){
                Map<String,String> stringMap = new HashMap<>();
                result.add(stringMap);
                for (int i = 1; i <= iColumnCount; i++) {
                    stringMap.put(meta.getColumnName(i),rs.getObject(i).toString());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /** Получение листа полетов по запросу с фильтрами
     * @param sql - текст запроса
     * @param param - мапа параметров
     * @param limit - лимит
     * @param offset - смещение, начальный элемент
     * @return  List<Map<String,String>>
     */
    public List<Map<String,String>> getRequestFligts(String sql,Map<String,String[]> param,int limit, int offset)  {
        List<Map<String,String>> result = new ArrayList<>();

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

        try (PreparedStatement ps = getConnection().prepareStatement(sql);){
            int iParam = 0;
            ps.setString(++iParam,param1);
            ps.setString(++iParam,param2);
            if (param3 != null) {
                ps.setDate(++iParam,  param3);
            }
            if (param4 != null) {
                ps.setDate(++iParam,  param4);
            }
            if (limit != 0) {
                ps.setInt(++iParam,limit);
                ps.setInt(++iParam,offset);
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int iColumnCount = meta.getColumnCount();
            while (rs.next()){
                Map<String,String> stringMap = new HashMap<>();
                result.add(stringMap);
                for (int i = 1; i <= iColumnCount; i++) {
                    stringMap.put(meta.getColumnName(i),toString(rs.getObject(i)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }


    /** Получение количество записей полетов с фильтрами
     * @param param - мапа параметров
     * @return  int количетво полетов
     */
    public int getSizeFlights(Map<String,String[]> param)  {
        String sql = " SELECT count(*) FROM bookings.flights\n" +
                "WHERE departure_airport = ? AND arrival_airport = ? AND 1=1 AND 2=2";
        List<Map<String,String>> resReq = getRequestFligts(sql,param,0,0);
        int result = Integer.parseInt(resReq.get(0).get("count"));
        return result;
    }

    /** Получение листа полетов по запросу с фильтрами, подготовка SQL запроса
     * делегирует реализацию getFlights
     * @param param - мапа параметров
     * @param limit - лимит
     * @param offset - смещение, начальный элемент
     * @return  List<Map<String,String>>
     */
    public List<Map<String,String>> getFlights(Map<String,String[]> param,int limit, int offset)  {
        List<Map<String,String>> result = new ArrayList<>();
        String sql = " SELECT * FROM bookings.flights\n" +
                "WHERE departure_airport = ? AND arrival_airport = ? AND 1=1 AND 2=2\n" +
                "ORDER BY flight_id ASC LIMIT ? OFFSET ?";
        return getRequestFligts(sql,param,limit, offset);
    }

    /** Получение даты из строки
     * @param param - строка с датой
     * @return  Date
     */
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

    static private String toString(Object obj) {
        if (obj != null) {
            return obj.toString();
        } else {
            return "";
        }
    }

}
