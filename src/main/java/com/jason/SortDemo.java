package com.jason;

import java.util.Arrays;

/**
 * 排序算法
 * @author: 祁琦
 * @date: 2020/6/20 9:55
 * version: 1.0
 */
public class SortDemo {


    public static void main(String[] args) {
        int[] items = new int[]{3,2,5,1,56,8,123,789,0};
        SortDemo.insertionSort(items, items.length);
        System.out.println(Arrays.toString(items));
    }

    /**
     * 插入排序
     * 实现思路：
     *  1. 将items分成两部分：已排序部分和未排序部分
     *  2.从未排序部分获取第一个元素,和已排序部分进行比较
     *  3.找到合适的位置,然后对已排序部分的数据进行移动
     *  4.插入到合适的位置
     * @param items 数组
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
            items[j+1] = value;
        }
    }

    /**
     * 冒泡排序算法
     * @param items
     * @param length
     */
    private static void bubbleSort(int[] items, int length) {
        for (int i = 0; i < length; i++) {
            boolean flag = false;
            for (int j = 0; j < length - i -1; j++) {
                if (items[j] > items[j+1]) {
                    int temp = items[j];
                    items[j] = items[j+1];
                    items[j+1] = temp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag){ break;}
        }
    }

}
