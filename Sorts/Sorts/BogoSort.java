package Sorts;

import java.util.Random;
import static Sorts.SortUtils.*;
/**
 * 什么是BogoSort啊?
 * 别看了,猴子排序而已...
 * 最坏时间复杂度为∞
 * 平均时间复杂度O(n*n!)
 * 
 * @author Huyuxi
 * @date 2019-3-21
 * @see SortAlgorithm
 *
 */
public class BogoSort implements SortAlgorithm {

    private static final Random random = new Random();

    /**
     * 
     * @param array array to be checked
     * @return if the array is sorted
     */
    private static <T extends Comparable<T>> boolean isSorted(T array[]){
        for(int i = 0; i<array.length - 1; i++){
            if(less(array[i + 1], array[i])) return false;
        }
        return true;
    }

    /**
     * Randomly shuffles the array
     * @param array The array to be sorted
     */
    private static <T> void nextPermutation(T array[]){
        int length = array.length;

        for (int i = 0; i < array.length; i++) {
            int randomIndex = i + random.nextInt(length - i);
            swap(array, randomIndex, i);
        }
    }

    /**
     * 
     */
    public <T extends Comparable<T>> T[] sort(T array[]) {
        while(!isSorted(array)){
            nextPermutation(array);
        }
        return array;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        // Integer Input
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        BogoSort bogoSort = new BogoSort();

        // print a sorted array
        SortUtils.print(bogoSort.sort(integers));

        // String Input
        String[] strings = {"c", "a", "e", "b","d"};

        SortUtils.print(bogoSort.sort(strings));
    }
}
