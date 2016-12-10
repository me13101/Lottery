// Import required java libraries

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
        logger.info(newLeague.Name+", "+newLeague.num_Teams+", "+commEmail+", "+Commissioner);
        try
        {
            boolean b = insertLeague();
            logger.info(b);
        }
        catch(Exception e)
        {
            logger.info("error1: "+e);
        }
    }
    public boolean insertLeague() {
        try {
            Connection conn = getConn();
            logger.info("connection: "+conn);
            Statement stmt = conn.createStatement();
            String params = "";
            String sql = "insert into drafthub.league Values('" + (ID) + "','" + Name + "', '" + ID + "', '" + num_Teams + "', '" + Commissioner + "', '" + commEmail + "')";
            int rs = stmt.executeUpdate(sql);
            conn.close();
            return true;
        } catch (Exception e) {
            logger.info("Error:" + e);
            return false;
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        Name = request.getParameter("league_name");
        Commissioner = request.getParameter("commName");
        Password = request.getParameter("leaguePW");
        //logger.info(Name+", "+Password);
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

    public int getID(){
        Connection conn = getConn();
        ResultSet res;
        String sql = "select count(*) from drafthub.league";
        //logger.info(sql);
        return ID;
    }

    public static void main (String[] args){

    }
}