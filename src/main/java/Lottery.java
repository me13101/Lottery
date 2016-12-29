// Import required java libraries

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Extend HttpServlet class
public class Lottery extends HttpServlet {
    static final long serialVersionUID = 42L;
    public String Name;
    public String Commissioner;
    public static int num_Teams;
    private String[] ownerList;
    public static Logger logger = Logger.getLogger(Lottery.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            Name = request.getParameter("league_name");
            Commissioner = request.getParameter("commName");
            num_Teams = Integer.parseInt(request.getParameter("num_teams"));
            ownerList = request.getParameterValues("ownerList");
            //logger.info(Arrays.toString(ownerList));
            Object[] order = runLottery(ownerList, num_Teams);
            logger.info("order: " + order);
            pw.print(Arrays.toString(order));
            pw.close();
        } catch (Exception e) {
            logger.info("Exception : " + e);
        }

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

    }

    private static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static Object[] runLottery(String[] owners, int number_of_teams) {
        List<String> order = new ArrayList<String>();
        List<String> ownersList = new ArrayList<String>(Arrays.asList(owners));

        logger.info("owners[0]: " + owners[0]);
        logger.info("ownerList: " + ownersList);

        int i = 1;
        int count = 1;
        int loopIncrementor = number_of_teams;

        if (!ownersList.isEmpty()) {
            try {
                for (int j = 1; j <= loopIncrementor; j++) {
                    //i = (int)Math.floor(Math.random()*number_of_teams);
                    i = randomWithRange(1, number_of_teams);
                    if (ownersList.get(i) != null) {
                        order.add(ownersList.get(i));
                        ownersList.remove(i);
                        number_of_teams--;
                        if (count <= loopIncrementor) {
                            count++;
                        }
                    } else {
                        j--;
                    }
                }
            } catch (Exception e) {
                logger.info("failed order: " + owners + ", Exception: " + e);
                return owners;
            }
            logger.info("order: " + order);
            return (order.toArray());
        }
        logger.info("failed order no exception: " + owners);
        return owners;

    }
}
