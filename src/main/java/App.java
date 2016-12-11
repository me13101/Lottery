// Import required java libraries
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

// Extend HttpServlet class
public class App extends HttpServlet {
    static final long serialVersionUID = 42L;
    public String Name;
    public String Password;
    public String Commissioner;
    public String commEmail;
    public int num_Teams;
    public int ID;
    static Logger logger  = Logger.getLogger(App.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        Name = request.getParameter("league_name");
        Commissioner = request.getParameter("commName");
        commEmail = request.getParameter("commEmail");
        num_Teams = Integer.parseInt(request.getParameter("num_teams"));
        ID = getID();
        League newLeague = new League(Name, null, num_Teams, ID);
        logger.info("App:"+newLeague.Name+", "+newLeague.num_Teams+", "+commEmail+", "+Commissioner);
        try
        {
            insertLeague();
            logger.info("App:"+insertLeague());
        }
        catch(Exception e)
        {
            logger.info("App:"+"error1: "+e);
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        Name = request.getParameter("league_name");
        Commissioner = request.getParameter("commName");
        Password = request.getParameter("leaguePW");
        //logger.info("App:"+Name+", "+Password);
    }

    public boolean insertLeague() {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            Connection conn = connectionManager.getConn();
            //Connection conn = getConn();
            logger.info("App:"+"connection: "+conn);
            Statement stmt = conn.createStatement();
            String params = "";
            String sql = "insert into drafthub.league Values('" + (ID) + "','" + Name + "', '" + ID + "', '" + num_Teams + "', '" + Commissioner + "', '" + commEmail + "')";
            int rs = stmt.executeUpdate(sql);
            conn.close();
            return true;
        } catch (Exception e) {
            logger.info("App:"+"Error:" + e);
            return false;
        }
    }

    public int getID(){
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            Connection conn = connectionManager.getConn();
            ResultSet res;
            String sql = "select count(*) from drafthub.league";
            //logger.info("App:"+sql);
            conn.close();
        }catch (Exception e){
            logger.info("App:"+"error3: "+e);
        }
        return ID;
    }
}