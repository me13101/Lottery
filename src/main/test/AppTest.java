import org.junit.Test;
import junit.framework.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by micha on 12/10/2016.
 */
public class AppTest extends TestCase {

    public void test(){
        App app = new App();
        app.insertLeague();
    }

}
