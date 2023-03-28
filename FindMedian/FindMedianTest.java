import edu.princeton.cs.algs4.StdRandom;

public class FindMedianTest {
    public static void main (String [] args) throws Throwable    {
        int[] size = {10, 50, 1000000, 5000000};
        // Sort and print a few small arrays to make certain the sort works
        SortandMedian(size[0], size[1], 10);
        // Sort and time larger arrays
        SortandMedian(size[2], size[3], 1000000);
        // Sort and time larger arrays
        SortandMedian(size[2]+1, size[3]+1, 1000000);
    }
    public static void SortandMedian(int start, int end, int inc) throws Throwable
    {
        double result; //Because it is "remade" here.
        for (int size = start; size<=end; size+=inc) {
            int [] array = createRandomArray(size);
            System.gc();
            long startTime = System.nanoTime();
            result = FindMedian.findMedian(array);
            long endTime = System.nanoTime();
            System.out.println ("Array size = " + size +
                    "\tMedian = " + result +
                    "\tSearch time = " +
                    (endTime-startTime)/1000000000.0 + " seconds");
        }
    }
    // This method reads creates an array of random integers
    public static int[] createRandomArray(int size) throws Throwable    {
        StdRandom.setSeed(10);
        int [] array = new int [size];
        for (int i=0; i<size; i++)  {
            array[i] = StdRandom.uniformInt(100000);
        }
        return array;
    }
}