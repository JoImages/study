package com.jason.string;

/**
 * BF算法，暴力匹配字符串
 * @author: 祁琦
 * @date: 2020/7/1 11:56
 * version: 1.0
 */
public class BruteForce {

    public static void main(String[] args) {
        String a = "abcaba";
        String b = "ca";
        int index = BruteForce.bf(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(index);
    }

    /**
     * bf 算法
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return 如果不存在返回-1，否则返回匹配的主串下标位置
     */
    public static int bf(char[] a, int n, char[] b, int m) {
        int result = -1;
        for (int i = 0; i <= n - m + 1; i++) {
            int c = i;
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (a[c] != b[j]) {
                    flag = false;
                    break;
                } else {
                    c++;
                }
            }
            if (flag){
                return i;
            }
        }
        return result;
    }

}
