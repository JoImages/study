package com.jason.search;

/**
 * 二分查找Demo
 *
 * @author: 祁琦
 * @date: 2020/6/22 14:22
 * version: 1.0
 */
public class SearchDemo {

    public static void main(String[] args) {
        int[] items = new int[]{0, 1, 1, 1, 3, 3};
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, 2));
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, 0));
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, 1));
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, 3));
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, -3));
        System.out.println(SearchDemo.binarySearchLastByLessOrEqualsToValue(items, -3));
    }

    /**
     * 二分查找变体,查找最后一个小于等于给定值的元素
     * @param items 数组
     * @param value 查找的元素
     * @return 最后一个小于等于给定元素的值的下标, 如果查询不到返回-1
     */
    private static  int binarySearchLastByLessOrEqualsToValue(int[] items, int value) {
        int length = items.length;
        if (length == 0) {
            return -1;
        }
        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (items[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == length - 1 || items[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找变体,查找第一个大于等于给定值的元素
     * @param a 数组
     * @param value 给定元素
     * @param n  数组长度
     * @return 第一个大于等于给定元素的值得下标, 如果查询不到返回-1
     */
    private static int binarySearchFirstByGreaterOrEqualsToValue(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找变体,查找第一个大于等于给定值的元素
     *
     * @param items 数组
     * @param value 给定元素
     * @return 第一个大于等于给定元素的值得下标, 如果查询不到返回-1
     */
    private static int binarySearchFirstByGreaterOrEqualsToValue(int[] items, int value) {
        int length = items.length;
        if (length == 0) {
            return -1;
        }
        int low = 0, high = length - 1;
        while (low <= high) {
            if (high != length - 1 && items[high] < value) {
                return high + 1;
            }
            int mid = low + ((high - low) >> 1);
            if (items[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < length && items[low] >= value) {
            return low;
        }
        return -1;
    }

    /**
     * 二叉树变种,查找最后一个值为给定值得元素下标
     *
     * @param items 数组
     * @param value 给定值的元素
     * @return 下标位置
     */
    private static int binarySearchLast(int[] items, int value) {
        int length = items.length;
        if (length == 0) {
            return -1;
        }
        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (items[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (high < length && items[high] == value) {
            return high;
        }
        return -1;
    }

    /**
     * 二叉树变种,查找第一个值为给定值得元素下标
     *
     * @param items 数组
     * @param value 给定值的元素
     * @return 下标位置
     */
    private static int binarySearchByFirstMethod2(int[] items, int value) {
        int length = items.length;
        if (length == 0) {
            return -1;
        }
        int low = 0, high = length - 1;
        while (low <= high) {
            // 获取区间中间值
            int mid = low + ((high - low) >> 1);
            if (items[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < length && items[low] == value) {
            return low;
        }
        return -1;
    }

    /**
     * 二叉树变种,查找第一个值为给定值得元素下标
     *
     * @param items 数组
     * @param value 给定值的元素
     * @return 下标位置
     */
    private static int binarySearchByFirst(int[] items, int value) {
        int length = items.length;
        if (length == 0) {
            return -1;
        }
        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int midValue = items[mid];
            /*
             * 和简单的二分查找相比唯一的区别就在这里
             * 如果查找到一个符合的元素的时候,需要判断其前一个是不是也是一样的值
             * 如果是一样的值,那么继续判断前一个直到找到一个值不是的为止,然后返回
             * 值为错误的下标+1的坐标返回
             */
            if (midValue == value) {
                int i = mid - 1;
                while (i >= 0 && items[i] == value) {
                    i--;
                }
                return i + 1;
            } else if (midValue > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归进行二分查找
     *
     * @param items 数组
     * @param low   数组最小下标
     * @param high  数组最大下标
     * @param value 查找的元素
     * @return 查找元素在数组中的下标位置, 如果查找不到的话返回-1
     * 需要注意点：
     * 1. 循环退出条件注意是 low<=high，而不是 low
     * 2.mid 的取值实际上，mid=(low+high)/2 这种写法是有问题的。因为如果 low 和 high 比较大的话，
     * 两者之和就有可能会溢出。改进的方法是将 mid 的计算方式写成 low+(high-low)/2。
     * 3.low 和 high 的更新low=mid+1，high=mid-1。注意这里的 +1 和 -1，如果直接写成 low=mid
     * 或者 high=mid，就可能会发生死循环。比如，当 high=3，low=3 时，如果 a[3]不等于 value，
     * 就会导致一直循环不退出。
     */
    private static int simpleBinarySearchInternally(int[] items, int low, int high, int value) {
        // 这说明数组中不存在value直接返回-1
        if (low > high) {
            return -1;
        }
        // 中间节点
        int mid = low + ((high - low) >> 1);
        if (items[mid] == value) {
            return mid;
        } else if (items[mid] > value) {
            return simpleBinarySearchInternally(items, low, mid - 1, value);
        } else {
            return simpleBinarySearchInternally(items, mid + 1, high, value);
        }
    }

    /**
     * 二分查找
     *
     * @param items  数组
     * @param length 数组长度
     * @param value  需要查找的元素的值
     * @return 需要查找的元素的值在数组中的下标位置，如果查询不到返回-1
     */
    private static int simpleBinarySearch(int[] items, int length, int value) {
        int low = 0, high = length - 1;
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
