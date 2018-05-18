package com.algorithm.study;

public class BinaryTest
{
    public static int binary(int[] array, int value)
    {
        int low = 0;
        int high = array.length - 1;
        while(low <= high)
        {
            int middle = (low + high) / 2;
            if(value == array[middle])
            {
                return middle;
            }
            if(value > array[middle])
            {
                low = middle + 1;
            }
            if(value < array[middle])
            {
                high = middle - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9,11,15,28,39,44,67,77,78,89,101};
        int value = binary(a, 14);
        System.out.println(value);
    }
}