import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class implements a 2D tree. A 2D tree is a special kind of binary search tree that is used to store a set of points in the plane.
 * The tree is organized such that points that are close to each other are stored in the same area of the tree.
 * This allows for efficient range searching (e.g. finding all the points contained in a given rectangle) and nearest neighbor searching.
 * @author Brandon Murry
 */
public class TwoDTree {
    TwoDTreeNode root; //Start of the tree.

    //Comparator for comparing points by their x coordinate.
    private final Comparator<Point> X_COMPARATOR = Comparator.comparingInt(o -> o.x);

    //Comparator for comparing points by their y coordinate.
    private final Comparator<Point> Y_COMPARATOR = Comparator.comparingInt(o -> o.y);

    /**
     * This class represents a node in the 2D tree.
     * Each node contains a point, a left child, and a right child.
     * The left child contains all the points that are less than the current node's point.
     * The right child contains all the points that are greater than the current node's point.
     * The comparator is used to determine whether to compare the x or y coordinate of the points.
     * The comparator is switched each time a new node is created.
     * @Author Brandon Murry
     */
    private class TwoDTreeNode {
        final private Point point;
        private TwoDTreeNode left;
        private TwoDTreeNode right;
        final private Comparator<Point> comparator;

        /**
         * Creates a new node with the given point and comparator.
         * @param point The point to store in the node.
         * @param comparator The comparator to use to determine whether to compare the x or y coordinate of the points.
         */
        public TwoDTreeNode(Point point, Comparator<Point> comparator) {
            this.point = point;
            this.comparator = comparator;
        }

        /**
         * Inserts a new point into the tree. The point is inserted into the left or right subtree depending on the value returned by the comparator.
         * If the point is equal to the point stored in the current node, it is inserted on the left.
         * @param point The point to insert.
         */
        public void insert(Point point) {
            if (comparator.compare(point, this.point) <= 0) {
                if (left == null) {
                    left = new TwoDTreeNode(point, comparator == X_COMPARATOR ? Y_COMPARATOR : X_COMPARATOR);
                } else {
                    left.insert(point);
                }
            } else {
                if (right == null) {
                    right = new TwoDTreeNode(point, comparator == X_COMPARATOR ? Y_COMPARATOR : X_COMPARATOR);
                } else {
                    right.insert(point);
                }
            }
        }

        /**
         * Searches the tree for the given point. Returns true if the point is found, false otherwise.
         * The search is performed by comparing the point to the point stored in the current node.
         * If the point is less than the point stored in the current node, the search is performed on the left subtree.
         * If the point is greater than the point stored in the current node, the search is performed on the right subtree.
         * @param point The point to search for.
         * @return True if the point is found, false otherwise.
         */
        public boolean search(Point point) {
            if (this.point.equals(point)) {
                return true;
            } else if (comparator.compare(point, this.point) < 0) {
                if (left == null) {
                    return false;
                } else {
                    return left.search(point);
                }
            } else {
                if (right == null) {
                    return false;
                } else {
                    return right.search(point);
                }
            }
        }

        /**
         * Searches the tree for all the points that are contained in the rectangle defined by p1 and p2. The points are added to the given list.
         * The search is performed by comparing the rectangle to the rectangle defined by the point stored in the current node.
         * searchRange is called recursively on the left and right subtrees.
         * @param p1 The first corner of the rectangle.
         * @param p2 The second corner of the rectangle.
         * @param points The list of points to add the points that are contained in the rectangle to.
         */
        public void searchRange(Point p1, Point p2, ArrayList<Point> points) {
            Point topRight = new Point(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y));
            Point bottomLeft = new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));

            if(topRight.x >= point.x && bottomLeft.x <= point.x && topRight.y >= point.y && bottomLeft.y <= point.y) {
                points.add(point);
            }
            if(left != null && comparator.compare(point, bottomLeft) >= 0) {
                left.searchRange(p1, p2, points);
            }
            if(right != null && comparator.compare(point, topRight) <= 0) {
                right.searchRange(p1, p2, points);
            }
        }
    }

    /**
     * Creates a new empty 2D tree. The root is set to null.
     */
    public TwoDTree(){
         root = null;
    }

    /**
     * Creates a new 2D tree and inserts all the points in the given list.
     * The points are inserted in the order they appear in the list.
     * @param points The list of points to insert.
     */
    public TwoDTree(ArrayList<Point> points){
       root = null;
         for (Point p : points) {
             insert(p);
         }
    }

    /**
     * Inserts a new point into the tree. The point is inserted into the left or right subtree depending on the value returned by the comparator.
     * If the point is equal to the point stored in the current node, it is inserted on the left. The comparator is switched each time a new node is created.
     * @param p The point to insert.
     */
    public void insert(Point p){
       TwoDTreeNode node = new TwoDTreeNode(p, Y_COMPARATOR);
         if (root == null) {
             root = node;
         } else {
             root.insert(p);
         }
    }

    /**
     * Searches the tree for the given point. Returns true if the point is found, false otherwise.
     * The search is performed by comparing the point to the point stored in the current node.
     * If the point is less than the point stored in the current node, the search is performed on the left subtree.
     * If the point is greater than the point stored in the current node, the search is performed on the right subtree.
     * @param p The point to search for.
     * @return True if the point is found, false otherwise.
     */
    public boolean search(Point p){
         return root.search(p);
    }

    /**
     * Searches the tree for all the points that are contained in the rectangle defined by p1 and p2. The points are added to the given list.
     * The search is performed by comparing the rectangle to the rectangle defined by the point stored in the current node.
     * @param p1 The first point of the rectangle.
     * @param p2 The second point of the rectangle.
     * @return The list of points that are contained in the rectangle defined by p1 and p2.
     */
    public ArrayList<Point> searchRange(Point p1, Point p2){
            ArrayList<Point> points = new ArrayList<>();
            root.searchRange(p1, p2, points);
            return points;
    }
}