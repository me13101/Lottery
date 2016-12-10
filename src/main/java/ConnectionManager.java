import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by micha on 12/10/2016.
 */
public class ConnectionManager {
    static Logger logger = Logger.getLogger(ConnectionManager.class);
    public ConnectionManager(){
    }

    public Connection getConn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1","me13101", "Elemnopee3");
            logger.info(conn);
            logger.info("Connected");
            return conn;
        }catch(Exception e){
            logger.info("error2: "+e);
        }
        logger.info("DB Not Connected");
        return null;
    }
}
