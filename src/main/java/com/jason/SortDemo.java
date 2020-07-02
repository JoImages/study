package com.jason;

import java.util.Arrays;

/**
 * 排序算法
 *
 * @author: 祁琦
 * @date: 2020/6/20 9:55
 * version: 1.0
 */
public class SortDemo {


    public static void main(String[] args) {
        int[] items = new int[]{6, 11, 12, 3, 9, 8, 8};
        SortDemo.quickSort(items, 0, items.length - 1);

        System.out.println(Arrays.toString(items));

    }

    /**
     * 快速排序
     *
     * @param items
     * @param start
     * @param end
     * @return
     */
    private static void quickSort(int[] items, int start, int end) {
        if (start >= end) {
            return;
        }
        // 找分区点
        int pivot = getPivot(items, start, end);
        quickSort(items, start, pivot - 1);
        quickSort(items, pivot + 1, end);
    }

    /**
     * 获取分区点
     *
     * @param items
     * @param start
     * @param end
     * @return
     */
    private static int getPivot(int[] items, int start, int end) {
        int pivot = items[end];
        int i = start;
        for (int j = start; j <= end - 1; j++) {
            if (items[j] < pivot) {
                int temp = items[j];
                items[j] = items[i];
                items[i] = temp;
                ++i;
            }
        }
        int temp = items[i];
        items[i] = items[end];
        items[end] = temp;
        return i;
    }

    /**
     * 归并排序算法
     * 实现思路：
     * 1.将当前数组根据中间节点分成两部分递归进行拆分
     * 2.直到每个数组的长度为1
     * 3.对相邻的两个数组进行有序合并
     * 4.将所有的有序数组进行合并
     *
     * @param items 原始数组
     * @param start 数组开始下标
     * @param end   数组结束下标
     * @return 排序完成的数组
     */
    private static int[] mergeSort(int[] items, int start, int end) {
        if (start >= end) {
            return new int[]{items[start]};
        }
        int center = (start + end) / 2;
        int[] headArray = mergeSort(items, start, center);
        int[] endArray = mergeSort(items, center + 1, end);
        // 合并两个有序数组
        return mergeTwoArray(headArray, endArray);
    }

    /**
     * 插入排序
     * 实现思路：
     * 1. 将items分成两部分：已排序部分和未排序部分
     * 2.从未排序部分获取第一个元素,和已排序部分进行比较
     * 3.找到合适的位置,然后对已排序部分的数据进行移动
     * 4.插入到合适的位置
     *
     * @param items  数组
     * @param length 数组长度
     */
    private static void insertionSort(int[] items, int length) {
        // 如果数组长度小于等于1的话,就没有必要进行排序了
        if (length <= 1) {
            return;
        }
        for (int i = 1; i < length; i++) {
            // 这个是希望插入的数据
            int value = items[i];
            // 获取的是已排序部分的最后的下标位置
            int j = i - 1;
            // 循环遍历已排序部分
            for (; j >= 0; j--) {
                // 如果当前元素比希望插入的元素大,则触发数据迁移
                if (items[j] > value) {
                    items[j + 1] = items[j];
                } else {
                    // 说明在已排序剩余的部分数据都比items小,没必要进行数据迁移了。
                    break;
                }
            }
            // 数据迁移完成,将比较的数据插入希望插入的位置
            items[j + 1] = value;
        }
    }

    /**
     * 冒泡排序算法
     *
     * @param items  需要排序的数组
     * @param length 数组长度
     */
    private static void bubbleSort(int[] items, int length) {
        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (items[j] > items[j + 1]) {
                    int temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序算法
     *
     * @param items 需要排序的数组
     */
    private static void bubbleSort(int[] items) {
        int length = items.length;
        if (length >= 1) {
            return;
        }
        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (items[j] > items[j + 1]) {
                    int temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) {
                return;
            }
        }
    }

    /**
     * 合并两个有序数组
     *
     * @param a 数组1
     * @param b 数组2
     * @return 合并后的数组
     */
    private static int[] mergeTwoArray(int[] a, int[] b) {
        int length = a.length + b.length;
        int[] temp = new int[length];
        if (length == 0) {
            return temp;
        }
        int index = 0;
        int i = 0;
        int j = 0;
        while (i != a.length && j != b.length) {
            if (a[i] < b[j]) {
                temp[index] = a[i];
                i++;
            } else {
                temp[index] = b[j];
                j++;
            }
            index++;
        }

        if (i != a.length) {
            for (; i < a.length; i++) {
                temp[index] = a[i];
                index++;
            }
        }
        if (j != b.length) {
            for (; j < b.length; j++) {
                temp[index] = b[j];
                index++;
            }
        }
        return temp;
    }

}
