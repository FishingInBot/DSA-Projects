import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * This class takes in an int array and finds the median value.
 * Median is defined as the middle value (or the average of the two middle values on an even array).
 * @author Brandon Murry
 */
public class FindMedian {
    /**
     * findMedian takes an input array of ints and used partition to find its median.
     * You can use this to find any indexed value by changing k to be the index you want.
     * @param array array of ints to find median of.
     * @return the value of median for given array.
     */
    public static double findMedian(int[] array) {
        int length = array.length;
        int k = (length - 1) / 2; // median index

        int left = 0;
        int right = length - 1;
        Random randy = new Random(); // for random pivot selection

        while (left <= right) {
            int pivotIndex = randy.nextInt(right - left + 1) + left; // choose random pivot
            int newPivotIndex = partition(array, left, right, pivotIndex);

            if (newPivotIndex == k) {
                if (length % 2 == 0) {  // if array has an even number of integers
                    int middle_left = newPivotIndex - 1;
                    return (array[middle_left] + array[newPivotIndex]) / 2.0; // return the average of the middle two digits
                } else {
                    return array[newPivotIndex]; // return median
                }
            } else if (newPivotIndex > k) {
                right = newPivotIndex - 1; // search left sub-array
            } else {
                left = newPivotIndex + 1; // search right sub-array
            }
        }

        return -1; // error: median not found, time to panic
    }

    /**
     * This is the main implementation for quick select. This will take an array of ints, a right and left bound, and a pivot index.
     * This is an adapted version of quick select using the code on its wiki as a starting point.
     * @param nums array we are operating on.
     * @param left index of left bound for the partition.
     * @param right index of right bound of the partition.
     * @param pivotIndex index of the chosen pivot value.
     * @return The index in which all values before are less than the pivot and after are all values greater than. This value will contain the pivot value.
     */
    private static int partition(int @NotNull [] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right); // move pivot to end
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }

        swap(nums, storeIndex, right); // move pivot to its final place
        return storeIndex;
    }

    /**
     * Helper for partition.
     * swapArray swaps two ints in an array.
     * @param swapArray array of which you want ints to be swapped in.
     * @param i The index of the first array to be swapped.
     * @param j The index of the second array to be swapped.
     */
    private static void swap(int[] swapArray, int i, int j) {
        int temp = swapArray[i];
        swapArray[i] = swapArray[j];
        swapArray[j] = temp;
    }
}