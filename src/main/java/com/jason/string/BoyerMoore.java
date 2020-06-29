package com.jason.string;

/**
 * BM 算法
 *
 * @author: 祁琦
 * @date: 2020/6/29 11:15
 * version: 1.0
 */
public class BoyerMoore {

    private static final int SIZE = 256;


    // a,b表示主串和模式串；n，m表示主串和模式串的长度。
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b,  bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i + j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int) a[i + j]];
            int y = 0;
            if (j < m - 1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] + 1;
        for (int r = j + 2; r <= m - 1; ++r) {
            if (prefix[m - r] == true) {
                return r;
            }
        }
        return m;
    }

    /**
     * 通过散列表bc将模式串modeString存储进去
     * 其中散列表的key为char的ascii码
     * value是char在模式串中的位置
     *
     * @param modeString
     * @param bc
     */
    private void generateBC(char[] modeString, int[] bc) {
        if (modeString.length > SIZE) {
            return;
        }
        for (int i = 0; i < 256; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < modeString.length; i++) {
            int ascii = modeString[i];
            bc[ascii] = i;
        }
    }

    /**
     * 计算模式串中跟好后缀匹配的另一个子串以及查找最长的能跟模式串前缀子串匹配的后缀子串
     *
     * @param b      模式串
     * @param m      模式串长度
     * @param suffix suffix 数组的下标 k，表示后缀子串的长度，下标对应的数组值存储的是，在模式串中跟好后缀{u}相匹配的子串{u*}的起始下标值
     * @param prefix 来记录模式串的后缀子串是否能匹配模式串的前缀子串,下标k表示公共后缀子串的长度,value为true,否则为false
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化数据
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        // 开始遍历
        for (int i = 0; i < m - 1; i++) {
            int j = i, k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                k++;
                suffix[k] = j;
                j--;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

}
