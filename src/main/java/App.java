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

// Extend HttpServlet class
public class App extends HttpServlet {
	static final long serialVersionUID = 42L;
	public String Name;
	public String Password;
	public String Commissioner;
	public String commEmail;
	public int num_Teams;
	public int ID;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		File file = new File("C:/xampp - DEV/tomcat/webapps/log/Log.txt");
		PrintWriter printWriter = new PrintWriter(file);
		Name = request.getParameter("league_name");
		Commissioner = request.getParameter("commName"); 
		commEmail = request.getParameter("commEmail"); 
		num_Teams = Integer.parseInt(request.getParameter("num_teams"));
		ID = getID();
		League newLeague = new League(Name, null, num_Teams, ID);

		printWriter.println(newLeague.Name+", "+newLeague.num_Teams+", "+commEmail+", "+Commissioner);

		try
		{
            boolean b = insertLeague();
            printWriter.println("insert league: "+b);
//			Connection conn = getConn();
//			printWriter.println("conn: "+conn);
//			Statement stmt = conn.createStatement();
//			String params = "";
//			String sql = "insert into drafthub.league Values('"+(ID)+"','"+Name+"', '"+ID+"', '"+num_Teams+"', '"+Commissioner+"', '"+commEmail+"')";
//			int rs = stmt.executeUpdate(sql);
//			printWriter.println(rs);
//			conn.close();
		}
		catch(Exception e)
		{
			printWriter.println("Error:"+e);
			printWriter.close();
		}
		printWriter.close();
	}
	public boolean insertLeague() {
		try {
			File file = new File("C:/xampp - DEV/tomcat/webapps/log/Log.txt");
			PrintWriter printWriter = new PrintWriter(file);
			try {
				Connection conn = getConn();
				Statement stmt = conn.createStatement();
				String params = "";
				String sql = "insert into drafthub.league Values('" + (ID) + "','" + Name + "', '" + ID + "', '" + num_Teams + "', '" + Commissioner + "', '" + commEmail + "')";
				int rs = stmt.executeUpdate(sql);

				conn.close();
                return true;
			} catch (Exception e) {
				printWriter.println("Error:" + e);
                return false;
			}
		}catch(Exception e){
			System.out.println(e);
            return false;
		}
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		File file = new File("C:/xampp - DEV/tomcat/webapps/log/Log.txt");
		PrintWriter printWriter = new PrintWriter(file);
		Name = request.getParameter("league_name"); 
		Commissioner = request.getParameter("commName"); 
		Password = request.getParameter("leaguePW");
		printWriter.println(Name+", "+Password);

		printWriter.close();
	}
	
	
	public Connection getConn(){
		try{
			File file = new File("C:/xampp - DEV/tomcat/webapps/log/Connection_Log.txt");
			PrintWriter printWriter = new PrintWriter(file);
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1","me13101", "Elemnopee3");
                printWriter.println(conn);
                printWriter.println("Connected");
				printWriter.close();
				return conn;
			}catch(Exception e){
				printWriter.println(e);
			}
			printWriter.println("DB Not Connected");
			printWriter.close();
		}catch(Exception e){
		}
		return null;
	}
	
	public int getID(){
		try{
			File file = new File("C:/xampp - DEV/tomcat/webapps/log/Connection_Log.txt");
			PrintWriter printWriter = new PrintWriter(file);
			
			Connection conn = getConn();
			//Statement stmt = conn.createStatement();
			ResultSet res;
			String sql = "select count(*) from drafthub.league";
			printWriter.println(sql);
			//res = stmt.executeQuery(sql);
//			while(/*res.next()*/){
//				while(){
//				ID = Integer.parseInt(res.getString(1));
//				printWriter.println(res.getString(1));
//			}
			return ID;
		}catch(Exception e){
			
		}
		return -1;
	}
	
	public static void main (String[] args){
		
	}
}