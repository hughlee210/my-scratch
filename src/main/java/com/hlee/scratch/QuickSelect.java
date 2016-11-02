package com.hlee.scratch;

import java.util.Arrays;
import java.util.Random;

/**
 * Quickselect uses the same overall approach as quicksort, choosing one element as a pivot 
 * and partitioning the data in two based on the pivot, accordingly as less than or greater than the pivot. 
 * However, instead of recursing into both sides, as in quicksort, quickselect only recurses into one side 
 * – the side with the element it is searching for. 
 * This reduces the average complexity from O(n log n) to O(n), with a worst case of O(n2).
 */
public class QuickSelect {

    public static void main(String[] args) {
        int[] arr = { 14, 2, 6, 9, 3 };
        int k = 0;
        int result = select(arr, 0, arr.length - 1, k);
        System.out.println(k + "th smallest element in " + Arrays.toString(arr) + " = " + result);

        arr = new int[] { 200, 10, 8, 1, 40 };
        k = 4;
        result = select(arr, 0, arr.length - 1, k);
        System.out.println(k + "th smallest element in " + Arrays.toString(arr) + " = " + result);

        int kthLargestIndex = 0;
        int kthSmallestIndex = arr.length - kthLargestIndex - 1;

        result = select(arr, 0, arr.length - 1, kthSmallestIndex);
        System.out.println(kthLargestIndex + "th largest element in " + Arrays.toString(arr) + " = " + result);

        kthLargestIndex = 4;
        kthSmallestIndex = arr.length - kthLargestIndex - 1;
        result = select(arr, 0, arr.length - 1, kthSmallestIndex);
        System.out.println(kthLargestIndex + "th largest element in " + Arrays.toString(arr) + " = " + result);

    }

    /**
     * Returns the k-th smallest element of the array within left..right inclusive.
     * The search space within the array is changing for each round - but the list 
     * is still the same size. Thus, k does not need to be updated with each round. 
     */
    static int select(int[] arr, int left, int right, int k) {
        if (k < 0 || k > arr.length - 1)
            return Integer.MIN_VALUE;
        if (left == right && k < 2)
            return arr[left];

        Random rand = new Random();
        int pivotIndex = left + (rand.nextInt(right - left + 1));
        pivotIndex = partition(arr, left, right, pivotIndex);

        // the pivot is in its final sorted position
        if (k == pivotIndex)
            return arr[k];
        else if (k < pivotIndex)
            return select(arr, left, pivotIndex - 1, k);
        else
            return select(arr, pivotIndex + 1, right, k);
    }

    /**
     * Partition method, in linear time, group a list (ranging from indices left to right) 
     * into two parts, those less than a pivot element, 
     * and those greater than or equal to the element.
     */
    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right); // move pivot to end
        int storeIndex = left; // position to store element less than pivot value
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex); // move pivot to its final sorted place
        return storeIndex; // return sorted pivot position
    }

    private static void swap(int[] arr, int x, int y) {
        if (arr == null || arr.length == 0)
            return;
        if (x < 0 || x > arr.length - 1)
            throw new IndexOutOfBoundsException("index " + x + " is out of bound of arr " + arr);
        else if (y < 0 || y > arr.length - 1)
            throw new IndexOutOfBoundsException("index " + y + " is out of bound of arr " + arr);

        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    // NOT working
    static int select_ex(int[] arr, int left, int right, int k) {
        if (arr == null || arr.length == 0)
            throw new RuntimeException("array is null or empty");
        Random rand = new Random();
        int pivotIndex = 0;
        while (true) {
            if (left == right)
                return arr[left];

            pivotIndex = left + rand.nextInt(right - left + 1);
            pivotIndex = partition(arr, left, right, k);
            if (k == pivotIndex)
                return arr[k];
            else if (k < arr[pivotIndex])
                right = pivotIndex - 1;
            else
                left = pivotIndex + 1;
        }
    }

    /*
    Note the resemblance to quicksort: just as the minimum-based selection algorithm is a partial selection sort, 
    this is a partial quicksort, generating and partitioning only O(log n) of its O(n) partitions. 
    This simple procedure has expected linear performance, and, like quicksort, has quite good performance in practice. 
    It is also an in-place algorithm, requiring only constant memory overhead, since the tail recursion can be eliminated with a loop like this:
    
    function select(list, left, right, n)
     if left = right
         return list[left]
     loop
         pivotIndex := ...     // select pivotIndex between left and right
         pivotIndex := partition(list, left, right, pivotIndex)
         if n = pivotIndex
             return list[n]
         else if n < pivotIndex
             right := pivotIndex - 1
         else
             left := pivotIndex + 1
     */

}
