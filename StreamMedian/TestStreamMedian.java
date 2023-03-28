import java.util.Random;

/**
 * Test program for the StreamMedian class Output from this program should be:
 * 60.0 54.0 48.0 47.5 47.0 47.5 48.0 50.5 48.0 50.5 53.0 53.5 54.0 57.0 60.0
 * 57.0 60.0 60.5 60.0 57.0  *
 * Note that your instructor will supply a different testing program for grading.
 *
 * @author Stuart Hansen
 *
 */
public class TestStreamMedian {

    public static void main(String[] args) {
        StreamMedian sm = new StreamMedian();
        Random random = new Random(0);
        for (int i = 0; i < 20; i++) {
            int r = random.nextInt(100);
            sm.insert(r);
            double x = sm.getMedian();
            System.out.println(r + " " + x);
        }

        System.out.println("*******************");

        sm = new StreamMedian();
        random = new Random(10);
        for (int i = 0; i < 20; i++) {
            int r = random.nextInt(1000000);
            sm.insert(r);
            double x = sm.getMedian();
            System.out.println(r + " " + x);
        }

        System.out.println();
    }
}