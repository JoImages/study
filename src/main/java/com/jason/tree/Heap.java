package com.jason.tree;

import java.util.Arrays;

/**
 * 堆
 *
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
    /**
     * 由于堆是一个完全二叉树,那么在使用数组进行存储的时候,左子节点和
     * 右子节点的下标分别是当前节点的2倍和2倍+1
     * <p>
     * 例如：
     * 根节点的数组下标为1
     * 那么根节点的左子节点下标为2*1 = 2
     * 根节点的右子节点下标为2*1 + 1 = 3
     */
    private static final Integer num = 2;

    public Heap() {
    }

    public Heap(int length) {
        this.capacity = new int[length + 1];
        this.length = length;
        this.count = 0;
    }

    /**
     * 取数组top10的元素
     * @param items
     */
    public void top10(int[] items) {
        if (items.length < 10) {
            return;
        }
        // 先构建一个堆，堆的大小为10
        Heap heap = new Heap(10);
        // 循环遍历数组items，将数据写入堆
        for (int i = 0; i < items.length; i++) {
            // 因为堆可以存储十个元素，前10个直接存入
            if (i < 10) {
                heap.insertSmall(items[i]);
            } else {
                // 需要和堆顶的元素进行比较，如果小于堆顶的元素的话，就不存储
                int heapTop = heap.capacity[1];
                if (items[i] > heapTop) {
                    heap.capacity[1] = items[i];
                    heap.smallHeapify(10, 1);
                }
            }
        }
        System.out.println(Arrays.toString(heap.capacity));
    }

    public void sort(int[] items, int lastIndex) {
        this.buildHeap(items, lastIndex);
        int k = lastIndex;
        while (k > 1) {
            swap(items, 1, k);
            k--;
            heapify(items, k, 1);
        }
    }

    /**
     * 根据跟定的数组和数组中最后使用的下标位置创建一个堆
     *
     * @param items     数组
     * @param lastIndex 数组中使用的最后一个下标位置
     */
    public void buildHeap(int[] items, int lastIndex) {
        for (int i = lastIndex / num; i >= 1; i--) {
            heapity(items, lastIndex, i);
        }
    }

    /**
     * 对给定的数组进行堆化，自上而下的堆化
     *
     * @param items     数组
     * @param lastIndex 数组中最后一个元素的下标位置
     * @param index     需要开始分析的数组位置
     */
    private void heapity(int[] items, int lastIndex, int index) {
        while (true) {
            int maxPos = index;
            if (index * num <= lastIndex && items[index] < items[index * num]) {
                maxPos = index * 2;
            }
            if (index * num + 1 <= lastIndex && items[maxPos] < items[index * num + 1]) {
                maxPos = index * num + 1;
            }
            if (maxPos == index) {
                break;
            }
            swap(items, index, maxPos);
            index = maxPos;
        }
    }

    /**
     * 向堆中插入数据
     *
     * @param value
     */
    public void insert(int value) {
        if (count >= length) {
            System.out.println("Heap's full");
            return;
        }
        // 因为是从1开始
        count++;
        // 无脑将数据插入最后一个位置中去。然后再进行判断
        capacity[count] = value;
        int i = count;
        while (i >> 1 > 0 && capacity[i] > capacity[i >> 1]) {
            this.swap(capacity, i, i >> 1);
            i = i >> 1;
        }
    }

    /**
     * 构建一个小顶堆
     *
     * @param val 插入的数据元素
     */
    public boolean insertSmall(int val) {
        if (count >= length) {
            return false;
        }
        count++;
        capacity[count] = val;
        int i = count;
        while (i / 2 > 0 && capacity[i] < capacity[i / 2]) {
            swap(capacity, i, i / 2);
            i = i / 2;
        }
        return true;
    }

    /**
     * 删除堆顶元素
     * 步骤分为两步：
     * 1、使用完全二叉树最后一个节点替换掉头结点
     * 2、然后进行根据规则进行排序(堆化)
     */
    public void removeHeapTop() {
        // 没有数据
        if (count == 0) {
            return;
        }
        capacity[1] = capacity[count];
        count--;
        // 将数组进行堆化
        this.heapify(capacity, count, 1);
    }

    /**
     * 将给定的数组进行小顶堆的堆化
     *
     * @param n     最后一个元素的位置
     * @param i     需要开始堆化的位置
     */
    private void smallHeapify(int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && capacity[i] > capacity[i * 2]) {
                maxPos = i * 2;
            }
            if ((i * 2) + 1 <= n && capacity[maxPos] > capacity[(i * 2) + 1]) {
                maxPos = (i * 2) + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(capacity, maxPos, i);
            i = maxPos;
        }
    }

    /**
     * 将给定的数组根据堆的标准进行格式化
     *
     * @param items 数组
     * @param n     堆最后一个元素的下下标位置
     * @param i     堆定的下标位置
     */
    private void heapify(int[] items, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * num < n && items[i] < items[i * num]) {
                maxPos = i * num;
            }
            if (i * num + 1 < n && items[maxPos] < items[i * num + 1]) {
                maxPos = i * num + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(items, i, maxPos);
            i = maxPos;
        }
    }


    @Override
    public String toString() {
        return "Heap{" +
                "capacity=" + Arrays.toString(capacity) +
                '}';
    }


    /**
     * 交换数组中的元素
     *
     * @param items  原始数组
     * @param index1 需要交换的元素下标1
     * @param index2 需要交换的元素下标2
     */
    private void swap(int[] items, int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    public static void main(String[] args) {
        int[] items = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Heap heap = new Heap();
        heap.top10(items);
        System.out.println(Arrays.toString(heap.capacity));
    }
}
