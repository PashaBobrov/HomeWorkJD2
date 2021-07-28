package chat.Storage;

import chat.Storage.api.DAO;
import chat.model.Message;
import org.postgresql.core.ConnectionFactory;
import org.postgresql.core.v3.ConnectionFactoryImpl;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс JDBCMessageDAO.
 * Реализуюет интерфейс DAO через JDBC.
 * получает соединение с хранилищем данных
 * получает данные из таблицы Message
 * @version 1.1
 */
public class JDBCMessageDAO implements DAO<Message> {
    Connection connection;

    /**
     * конструктор, полчение соединения
     * @param ds
     */
    public JDBCMessageDAO(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * сохрание сообщения в хранилище
     * @param message
     * @return Message
     * @throws SQLException
     */
    @Override
    public Message save(Message message) throws SQLException {
        String sql = "INSERT INTO chat.messages VALUES (?, ?, ?,?);";
        List<Message> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,message.getUserTo());
            ps.setString(2,message.getUserFrom());
            ps.setString(3,message.getText());
            ps.setDate(4,new Date(message.getDate().getTime()));

            int rs = ps.executeUpdate();

        }
        return message;
    }

    /**
     * получение листа сообщений
     * @return List<Message
     * @throws SQLException
     */
    @Override
    public List<Message> getAll() throws SQLException {
        String sql = "SELECT * FROM chat.messages;";
        List<Message> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Message(rs.getString("userTo")
                        ,rs.getString("userFrom"),rs.getString("text")));
            }
        }
        return list;
    }

    /**
     * заглушка
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Message get(Serializable id) throws SQLException {
        return null;
    }

    /**
     * заглушка
     * @param message
     * @throws SQLException
     */
    @Override
    public void update(Message message) throws SQLException {

    }

    /**
     * заглушка
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    /**
     * Получение даты из строки
     * @param param
     * @return Date
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
}

