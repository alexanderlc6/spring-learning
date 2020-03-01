package com.sp.test.ioc.datastructure;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AlexLc on 2020/1/31.
 */
public class SortDemo{
    public static void main(String[] args) {
        //增量排序
        List<Integer> a = Arrays.asList(34,8,64,51,32,21);
        insertionSort(a);

        //希尔排序(ShellSort)
        List<Integer> b = Arrays.asList(81,94,11,96,12,35,17,95,28,58,41,75,15);
        shellSort(b);
    }

    /**
     * 增量排序
     * @param a
     * @param <Integer>
     */
    public static <Integer extends Comparable<? super Integer>> void insertionSort(List<Integer> a){
        int j;

        for (int p = 1; p < a.size(); p++){
            Integer tmp = a.get(p);
            for (j = p; j > 0 && tmp.compareTo(a.get(j - 1)) < 0; j--){
                a.set(j, a.get(j - 1));
            }

            a.set(j, tmp);
        }

        //Result:[[8, 21, 32, 34, 51, 64]]
        System.out.println(Arrays.asList(a));
    }

    /**
     * 希尔排序(ShellSort)
     * @param b
     * @param <Integer>
     */
    private static <Integer extends Comparable<? super Integer>> void shellSort(List<Integer> b) {
        int j;
        //设定增量并执行交换
        for (int gap = b.size() / 2; gap > 0; gap /= 2){
            for (int i = gap; i < b.size(); i++){
                Integer tmp = b.get(i);
                for (j = i; j >= gap && tmp.compareTo(b.get(j - gap)) < 0; j -= gap){
                    b.set(j, b.get(j - gap));
                }

                b.set(j, tmp);
            }
        }

        //Result:[[11, 12, 15, 17, 28, 35, 41, 58, 75, 81, 94, 95, 96]]
        System.out.println(Arrays.asList(b));
    }
}
