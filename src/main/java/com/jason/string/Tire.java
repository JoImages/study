package com.jason.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Tire树
 * @author: 祁琦
 * @date: 2020/6/30 9:01
 * version: 1.0
 */
public class Tire {

    private TrieNode root = new TrieNode('/');

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            char c = text[i];
            if (!p.characterTrieNodeMap.containsKey(c)) {
                p.characterTrieNodeMap.put(c, new TrieNode(c));
            }
            p = p.characterTrieNodeMap.get(c);
        }
        // 标志这个字符已经插入完成了
        p.endingFlag = true;
        System.out.println(root);
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (char c : pattern) {
            if (!p.characterTrieNodeMap.containsKey(c)) {
                return false;
            }
            p = p.characterTrieNodeMap.get(c);
        }
        return p.endingFlag;
    }


}

/**
 * 数据结构
 */
class TrieNode {
    char data;
    Map<Character, TrieNode> characterTrieNodeMap = new HashMap<>();
    boolean endingFlag;

    @Override
    public String toString() {
        return "TrieNode{" +
                "data=" + data +
                ", characterTrieNodeMap=" + characterTrieNodeMap +
                ", endingFlag=" + endingFlag +
                '}';
    }

    public TrieNode(char data) {
        this.data = data;
    }
}

