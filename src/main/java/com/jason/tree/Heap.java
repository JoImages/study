package com.jason.tree;

import java.util.Arrays;

/**
 * 堆
 * @author: 祁琦
 * @date: 2020/6/24 11:51
 * version: 1.0
 */
public class Heap {

    /**
     * 其实堆就是一种完全二叉树,而由于完全二叉树的特点特别适合使用数组
     * 来存储,所以我们使用数组作为堆的容器
     */
    private int[] capacity;
    /**
     * 堆可以容纳大最大元素个数
     */
    private int length;
    /**
     * 堆中当前存储的元素个数
     */
    private int count;

    public Heap(int length) {
        this.capacity = new int[length + 1];
        this.length = length;
        this.count = 0;
    }

    /**
     * 向堆中插入数据
     * @param value
     */
    public void insert(int value) {
        // 堆满了,不再接受数据
        if (count >= length) {
            System.out.println("堆满了,不再接收数据");
            return;
        }
        // 先将对象插入堆中
        count++;
        capacity[count] = value;
        int num = count;
        while (num / 2 > 0 && capacity[num] > capacity[num / 2]) {
            int temp = capacity[num];
            capacity[num] = capacity[num / 2];
            capacity[num / 2] = temp;
            num = num / 2;
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(7);
        heap.insert(33);
        heap.insert(27);
        heap.insert(21);
        heap.insert(16);
        heap.insert(13);
        heap.insert(15);
        heap.insert(9);
        heap.insert(5);
        System.out.println(Arrays.toString(heap.capacity));
    }
}
