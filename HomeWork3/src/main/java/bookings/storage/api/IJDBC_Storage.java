package bookings.storage.api;

import bookings.storage.JDBC_Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс IJDBC_Storage.
 * Объект доступа к данным airports и flights
 * @version 1.1
 */
public interface IJDBC_Storage {
    public List<Map<String,String>> getAirports();
    public List<Map<String,String>> getFlights(Map<String,String[]> param,int limit, int offset);
    public int getSizeFlights(Map<String,String[]> param);
}
