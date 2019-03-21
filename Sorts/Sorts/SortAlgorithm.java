package Sorts;

import java.util.Arrays;
import java.util.List;

/**
 *  The common interface of most sorting algorithms
 *
 * @author Huyuxi
 * @date 2019-3-21
 *
 **/
public interface SortAlgorithm {

    /**
     * Main method arrays sorting algorithms
     * @param unsorted - an array should be sorted
     * @return a sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

}
