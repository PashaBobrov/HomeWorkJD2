package chat.Storage;

import chat.Storage.api.DAO;
import chat.model.Message;
import chat.model.User;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс JDBCUserDAO.
 * Реализуюет интерфейс DAO через JDBC.
 * получает соединение с хранилищем данных
 * получает данные из таблицы users
 * @version 1.1
 */
public class JDBCUserDAO implements DAO<User> {
    Connection connection;

    /**
     * конструктор, полчение соединения
     * @param ds
     */
    public JDBCUserDAO(DataSource ds) {
        try {
            this.connection = ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * сохраниет в хранилище юзера
     * @param user
     * @return User
     * @throws SQLException
     */
    @Override
    public User save(User user) throws SQLException {
        String sql = "INSERT INTO chat.users VALUES (?, ?, ?,?);";
        //List<Message> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,user.getFIO());
            ps.setString(2,user.getLogin());
            ps.setString(3,user.getPassword());
            ps.setDate(4,new Date(user.getDateOfBirth().getTime()));
            int rs = ps.executeUpdate();
        }
        return user;
    }

    /**
     * получает лист юзеров
     * @return List<User>
     * @throws SQLException
     */
    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM chat.users;";
        List<User> list = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("FIO")
                        ,rs.getString("login")
                        ,rs.getString("password")
                        ,rs.getDate("dateofbirth")));
            }
        }
        return list;
    }

    /**
     * получает юзера по id
     * @param id
     * @return User
     * @throws SQLException
     */
    @Override
    public User get(Serializable id) throws SQLException {
        String sql = "SELECT * FROM chat.users WHERE login = ?;";
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,id.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(rs.getString("FIO")
                        ,rs.getString("login")
                        ,rs.getString("password")
                        ,rs.getDate("dateofbirth"));
            }
        }
        return result;
    }

    /**
     * заглушка
     * @param user
     * @throws SQLException
     */
    @Override
    public void update(User user) throws SQLException {

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

}

