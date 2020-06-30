package com.jason;

import java.util.Arrays;

/**
 * @author: 祁琦
 * @date: 2020/6/30 15:24
 * version: 1.0
 */
public class ArrayDemo {

    public void remove(int[] items, int index) {
        if (items.length - 1 < index) {
            return;
        }
        int i = index;
        for (; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
    }

    public static void insert(int[] items, int index, int val) {
        for (int i = items.length - 1; i > index; i--) {
            int temp = items[i];
            items[i] = items[i - 1];
            items[i - 1] = temp;
        }
        items[index] = val;
    }

    /**
     * 在数组下标为index的位置插入元素val
     * @param items 数组
     * @param size 数组中元素的个数
     * @param index 希望插入的位置
     * @param val 希望插入的值
     */
    public static void insert(int[] items, int size, int index,int val) {
        if (size + 1 == items.length) {
            return;
        }
        int temp = items[index];
        items[index] = val;
        items[size + 1] = temp;
    }

    public static void main(String[] args) {
        int[] items = new int[10];
        for (int i = 0; i < 6; i++) {
            items[i] = i;
        }
        ArrayDemo.insert(items,5,4,7);
        System.out.println(Arrays.toString(items));
    }

}
