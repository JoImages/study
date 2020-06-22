package com.jason.search;

/**
 * 二分查找Demo
 * @author: 祁琦
 * @date: 2020/6/22 14:22
 * version: 1.0
 */
public class SearchDemo {

    public static void main(String[] args) {
        int[] items = new int[]{0,1,2,3,4,5,6};
        System.out.println(SearchDemo.binarySearchInternally(items,0,6,6));
        System.out.println(SearchDemo.binarySearchInternally(items,0,6,0));
        System.out.println(SearchDemo.binarySearchInternally(items,0,6,4));
        System.out.println(SearchDemo.binarySearchInternally(items,0,6,7));
    }

    /**
     * 递归进行二分查找
     * @param items 数组
     * @param low 数组最小下标
     * @param high 数组最大下标
     * @param value 查找的元素
     * @return 查找元素在数组中的下标位置,如果查找不到的话返回-1
     * 需要注意点：
     * 1. 循环退出条件注意是 low<=high，而不是 low
     * 2.mid 的取值实际上，mid=(low+high)/2 这种写法是有问题的。因为如果 low 和 high 比较大的话，
     * 两者之和就有可能会溢出。改进的方法是将 mid 的计算方式写成 low+(high-low)/2。
     * 3.low 和 high 的更新low=mid+1，high=mid-1。注意这里的 +1 和 -1，如果直接写成 low=mid
     * 或者 high=mid，就可能会发生死循环。比如，当 high=3，low=3 时，如果 a[3]不等于 value，
     * 就会导致一直循环不退出。
     */
    private static int binarySearchInternally(int[] items, int low, int high, int value) {
        // 这说明数组中不存在value直接返回-1
        if (low > high) {
            return -1;
        }
        // 中间节点
        int mid = low + ((high - low) >> 1);
        if (items[mid] == value) {
            return mid;
        } else if (items[mid] > value) {
            return binarySearchInternally(items, low, mid - 1, value);
        } else {
            return binarySearchInternally(items, mid + 1, high, value);
        }
    }

    /**
     * 二分查找
     * @param items 数组
     * @param length 数组长度
     * @param value 需要查找的元素的值
     * @return 需要查找的元素的值在数组中的下标位置，如果查询不到返回-1
     */
    private static int binarySearch(int[] items, int length, int value) {
        int low = 0, high = length -1;
        while (low <= high) {
            // 注意这个右移符号的优先级是低于%的，所以要加括号
            int mid = low + ((high - low) >> 1);
            if (items[mid] == value) {
                return mid;
            } else if (items[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}
