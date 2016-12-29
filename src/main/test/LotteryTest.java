import junit.framework.TestCase;

import java.util.List;

/**
 * Created by micha on 12/10/2016.
 */
public class LotteryTest extends TestCase {

    public void test(){
        String[] owners = new String[11];
        for (int i = 1; i <= 10; i ++){
            owners[i] = "Player"+i;
        }
        Lottery lottery = new Lottery();
        Object[] order = lottery.runLottery(owners, 10);
        System.out.println("===========================");
        System.out.println("            order          ");
        System.out.println("===========================");
        for (int j = 0; j < 10; j++){
            System.out.println(order[j]);
        }
    }

}
