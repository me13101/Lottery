import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

// Extend HttpServlet class
public class Authent extends HttpServlet {
	static final long serialVersionUID = 42L;
	public static String email, pswd, userID, userName, leagueID, leagueName;
	static Logger logger = Logger.getLogger(Authent.class);

    //public Authent(String email,String pswd,String userID,String userName,String leagueID,String leagueName){
    public Authent(){
//		this.email = email;
//		this.pswd = pswd;
//		this.userID = userID;
//		this.userName = userName;
//		this.leagueID = leagueID;
//		this.leagueName = leagueName;
	}
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{

		try{
		email = request.getParameter("email");
		pswd = request.getParameter("password");
		
		if (checkDB(email, pswd) == true){
            //response.getWriter();
			//response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("true");
			out.close();
		}
		else{
			//response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("false");
			out.close();
		}
			//logger.info("Authent: "+email+" : "+pswd);
		}catch(Exception e){
			//logger.info("Authent: "+e);
		}
	}
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		try{
		userName = request.getParameter("userName");
		leagueName = request.getParameter("leagueName");
		leagueID = request.getParameter("leagueID");
		email = request.getParameter("email");
		pswd = request.getParameter("password");
		
		if (checkDB(email, pswd) == true){
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("fount");
			out.close();
		}
		else{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (newUser(email, pswd, userID, userName, leagueID, leagueName)== true){
				out.print("successful");
			}
			else{
				out.print("failed");
			}
			out.close();
		}

			//logger.info("Authent: "+email+" : "+pswd);
		}catch(Exception e){
			//logger.info("Authent: "+e);
		}
	}
	public static boolean checkDB(String email, String pswd){
			try{
				ConnectionManager conn = new ConnectionManager();
				Connection c = conn.getConn();
				//System.out.println(c);
				PreparedStatement ps = c.prepareStatement("Select * from draftHub.player where email ='"+email+"' and pswd ='"+pswd+"'");
                //PreparedStatement ps = c.prepareStatement("Select * from drafthub.player");
                ResultSet rs;
				rs = ps.executeQuery();
				//System.out.println(rs.getFetchSize());
//				if (rs.getFetchSize() > 0){
                if (rs.next()){
					return true;
				}
				return false;
			}catch(Exception e){
				//System.out.println(e);
				//logger.info("Authent: "+e);
			}
		return false;
	}
	public static boolean newUser(String userID, String userName, String leagueID, String leagueName, String email, String pswd){
			try{
				Conn conn = new Conn();
				Connection c = conn.getConn();
				//userID = conn.getID("drafthub.player");
				int i = Integer.parseInt(userID);
				int j = Integer.parseInt(leagueID);
				PreparedStatement ps = c.prepareStatement("Insert into draftHub.player values('"+i+"', '"+userName+"', '"+j+"', '"+leagueName+"', '"+email+"', SYSDATE(), '"+pswd+"')");
				return true;
			}catch(Exception e){
				//logger.info("Authent: "+e);
			}
		return false;
	}
}
