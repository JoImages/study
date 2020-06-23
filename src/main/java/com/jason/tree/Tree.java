package com.jason.tree;

import javax.xml.soap.Node;

/**
 * 二叉树学习
 *
 * @author: 祁琦
 * @date: 2020/6/23 16:42
 * version: 1.0
 */
public class Tree {

    public static void main(String[] args) {
        Node node = new Node(33);
        node.left = new Node(17);
        node.right = new Node(50);
        node.left.left = new Node(13);
        node.left.right = new Node(18);
        node.right.left = new Node(34);
        node.right.right = new Node(58);
        insert(node, 25);
        System.out.println(node);
//        System.out.println(find(node, 33));
    }

    /**
     * 二叉查找树的插入操作
     * @param node 希望插入的二叉查找树
     * @param data 希望插入的节点的值
     */
    private static void insert(Node node, int data) {
        Node p = node;
        Node newNode = new Node(data);
        while (p != null) {
            if (data < p.val) {
                if (p.left == null) {
                    p.left = newNode;
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = newNode;
                    return;
                }
                p = p.right;
            }
        }

    }

    /**
     * 二叉查找树的查找操作
     *
     * @param node 节点node
     * @param data 需要比对的数据
     * @return
     */
    private static Node find(Node node, int data) {
        if (node == null) {
            return null;
        }
        if (node.val == data) {
            return node;
        }
        if (data < node.val) {
            node = node.left;
        } else {
            node = node.right;
        }
        return find(node, data);
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    private static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    private static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    private static class Node {
        int val;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
