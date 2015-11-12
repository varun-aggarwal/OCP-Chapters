package datasource.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import datasource.DataSource;

public class DataSourceImpl implements DataSource {

    private static final DataSourceImpl INSTANCE = new DataSourceImpl();

    private final String url = "jdbc:postgresql://localhost:5432/employee_demo";

    private final String user = "postgres";

    private final String password = "qafe";

    private Connection conn;

    // private constructor
    private DataSourceImpl() {
        init();
        addShutdownHook();
    }

    // public method to get the singleton
    // Runtime initialization
    // By default ThreadSafe
    public static DataSourceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return conn;
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (final ClassNotFoundException | SQLException e) {
            final String msg = "Failed to connect to the database. Is the database down?";
        }
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
