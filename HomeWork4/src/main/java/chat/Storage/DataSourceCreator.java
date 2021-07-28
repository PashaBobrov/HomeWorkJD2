package chat.Storage;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DataSourceCreator {
    private  static DataSourceCreator instance;
    private ComboPooledDataSource cpds;

    private DataSourceCreator()  {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost:5432/edu");
            cpds.setUser("postgres");
            cpds.setPassword("1234");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getInstance() {
        if(instance == null) {
            synchronized (DataSourceCreator.class) {
                if(instance == null) {
                    instance = new DataSourceCreator();
                }
            }
        }
        return instance.cpds;
    }
}

