/**
 * Created by micha on 12/10/2016.
 */
import org.junit.Test;
import junit.framework.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AuthentTest extends TestCase {

    public void test(){
        Authent authent = new Authent();
        Boolean b = authent.checkDB("michhael@test.net", "pswd");
        Authent.checkDB("michael@test.net", "pswd");
    }

}
