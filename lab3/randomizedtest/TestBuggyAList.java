package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> W = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            boolean flag = false;
            if (L.size() == 0)
                flag = true;
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                W.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size_L = L.size();
                assertEquals(L.size(), W.size());
                System.out.println("size: " + size_L);
            } else if (operationNumber == 2) {
                //getLast
                if (flag)
                    continue;
                int last = L.getLast();
                assertEquals(L.getLast(),W.getLast());
                System.out.println("getLast" + last);
            } else {
                //removeLast
                if (flag)
                    continue;
                int last_L = L.removeLast();
                int last_W = W.removeLast();
                assertEquals(last_L, last_W);
                System.out.println("removeLast" + last_L);
            }
        }
    }
}
