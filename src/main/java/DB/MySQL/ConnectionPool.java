package DB.MySQL;


import exception.MainException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Realisation of connection pool.
 *
 * @author D. Kuliha
 */
public class ConnectionPool {

private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    private static final String DATASOURCE_NAME = "java:/comp/env/jdbc/autobaseDB";
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(DATASOURCE_NAME);
        } catch (NamingException ex) {
            LOG.error("Cannot obtain data source", ex);
            throw new MainException("Cannot obtain data source", ex);
        }
    }

    ///////////////////////////////////
    //Singleton
    /////////////////////////////////////
    private ConnectionPool() {
    }

    public static synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error("Cannot obtain connection from data source",e);
            throw new MainException("Cannot obtain connection from data source",e);
        }
        return connection;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            LOG.error("Cannot close connection",e);
            throw new MainException("Cannot close connection",e);
        }
    }
    public static void rollback(Connection con){
        try {
            con.rollback();
        } catch (SQLException e) {
            LOG.error("Cannot rollback transaction", e);
            throw new MainException("Cannot rollback transaction", e);
        }
    }
}
