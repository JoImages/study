package com.jason.tree;

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
        delete(node, 13);
        System.out.println(node);
//        System.out.println(find(node, 33));
    }

    /**
     * 二叉查找树的删除操作
     *
     * @param tree  二叉查找树
     * @param data 需要删除的节点的值
     */

    private static void delete(Node tree, int data) {
        // p指向要删除的节点，初始化指向根节点
        Node p = tree;
        // pp记录的是p的父节点
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        // 没有找到
        if (p == null){ return;}

        // 要删除的节点有两个子节点
        // 查找右子树中最小节点
        if (p.left != null && p.right != null) {
            Node minP = p.right;
            // minPP表示minP的父节点
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            // 将minP的数据替换到p中
            p.data = minP.data;
            // 下面就变成了删除minP了
            p = minP;
            pp = minPP;
        }
        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null){ child = p.left;}
        else if (p.right != null){ child = p.right;}
        else{ child = null;}
        // 删除的是根节点
        if (pp == null){ tree = child;}
        else if (pp.left == p){ pp.left = child;}
        else{ pp.right = child;}
    }

    /**
     * 二叉查找树的插入操作
     *
     * @param node 希望插入的二叉查找树
     * @param data 希望插入的节点的值
     */
    private static void insert(Node node, int data) {
        Node p = node;
        Node newNode = new Node(data);
        while (p != null) {
            if (data < p.data) {
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
        if (node.data == data) {
            return node;
        }
        if (data < node.data) {
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
        System.out.println(node.data);
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
        System.out.println(node.data);
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
        System.out.println(node.data);
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
