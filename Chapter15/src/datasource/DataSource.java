package datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A datasource object which is a wrapper for the Hikari Connection Pooling library
 */
public interface DataSource {

    /**
     * Gets a connection object from the datasource
     *
     * @return a Connection object
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;
}
