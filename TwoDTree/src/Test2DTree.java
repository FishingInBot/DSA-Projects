import java.awt.*;
import java.util.ArrayList;
/**
 * This class exercises the methods in the TwoDTree Class.
 *
 * Output from these tests should be:
 * Should print all True:
 * true
 * true
 * true
 * true
 * true
 *
 * Should print all False:
 * false
 * false
 * false
 * false
 * false
 *
 * All points in range: [java.awt.Point[x=10,y=10], java.awt.Point[x=100,y=5],
 java.awt.Point[x=50,y=3], java.awt.Point[x=110,y=7], java.awt.Point[x=100,y=20]]
 */
public class Test2DTree {
    public static void main(String[] args) {
        TwoDTree tree1 = new TwoDTree();
        Point pt1 = new Point(10, 10);
        Point pt2 = new Point(100, 5);
        Point pt3 = new Point(100, 20);
        Point pt4 = new Point(110, 7);
        Point pt5 = new Point(50, 3);
        Point noPt1 = new Point(10, 5);
        Point noPt2 = new Point(10, 1);
        Point noPt3 = new Point(100, 3);
        Point noPt4 = new Point(110, 200);
        Point noPt5 = new Point(50, 7);
        tree1.insert(pt1);
        tree1.insert(pt2);
        tree1.insert(pt3);
        tree1.insert(pt4);
        tree1.insert(pt5);
        System.out.println("Should print all True:");
        System.out.println(tree1.search(pt1));
        System.out.println(tree1.search(pt2));
        System.out.println(tree1.search(pt3));
        System.out.println(tree1.search(pt4));
        System.out.println(tree1.search(pt5));
        System.out.println();
        System.out.println("Should print all False:");
        System.out.println(tree1.search(noPt1));
        System.out.println(tree1.search(noPt2));
        System.out.println(tree1.search(noPt3));
        System.out.println(tree1.search(noPt4));
        System.out.println(tree1.search(noPt5));
        System.out.println();
        ArrayList<Point> list = tree1.searchRange(noPt2, noPt4);
        System.out.println("All points in range: " + list.toString());
    }
}