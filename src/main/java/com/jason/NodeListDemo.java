package com.jason;

import java.util.Objects;

/**
 * 链表练习
 * @projectName: study
 * @see: com.jason
 * @author: 祁琦
 * @date: 2020/6/19 13:59
 * version: 1.0
 */
public class NodeListDemo {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(3);
        n1.next.next = new Node(1);

        Node n2 = new Node(4);
        n2.next = new Node(3);
        n2.next.next = new Node(4);
        n2.next.next.next = new Node(5);
        n2.next.next.next.next = new Node(4);
        n2.next.next.next.next.next = new Node(6);
        n2.next.next.next.next.next.next = new Node(4);
        System.out.println(deleteByVal(n2,4));


    }

    private static Node deleteByVal(Node node, int val){
        if (node.val == val) {
            node = node.next;
        }
        Node p = node;
        Node prev = node;
        while(p != null){
            if(p.val == val){
                prev.next = p.next;
            }
            prev = p;
            p = p.next;
        }
        System.out.println(node);
        return node;
    }

    private static void deleteByIndex(Node node, int index){
        int i = 0;
        Node p = node;
        Node prev = null;
        while(p != null){
            if(i == index){
                prev.next = p.next == null ? null : p.next.next;
            }
            i++;
            prev = p;
            p = p.next;
        }
    }

    private static void deleteTailNode(Node node){
        Node p = node;
        Node prev = null;
        while(p.next != null){
            prev = p;
            p = p.next;
        }
        prev.next = null;
    }
    /**
     * 判断给定的单列表是否是回文
     * @param node
     * @return
     */
    private static boolean isPalindrome(Node node) {
        Node p = null;
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            Node next = slow.next;
            slow.next = p;
            p = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (!slow.equals(p)) {
                return false;
            }
            slow = slow.next;
            p = p.next;
        }
        return true;
    }

    /**
     * 单项列表反转
     * @param node
     * @return
     */
    private static Node reverse(Node node) {

        Node head = new Node();
        head.next = node;

        Node prev = node;
        Node next = node.next;

        while (next != null) {
            prev.next = next.next;
            next.next = head.next;
            head.next = next;
            next = prev.next;
        }

        return head.next;
    }

    /**
     * 删除倒数第几个节点
     * @param node
     * @param lastNum
     * @return
     */
    private static Node deleteLastKth(Node node, Integer lastNum) {

        int length = 0;
        Node p = node;
        while (p != null) {
            length++;
            p = p.next;
        }
        p = node;
        int number = length - lastNum;
        if (number < 0) {
            return p;
        }
        if (number == 0) {
            p = p.next;
            return p;
        }
        int counter = 0;
        while (p != null) {
            counter++;
            if (counter == number) {
                p.next = p.next.next;
                return p;
            }
            p = p.next;
        }

        return p;
    }

    /**
     * 判断单向链表中是否包含环
     * @param node
     * @return
     */
    private static boolean existsCircle(Node node) {
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 求链表中间的节点
     * @param node
     * @return
     */
    private static Node findMiddleNode(Node node) {
        Node fast = node;
        Node slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 两个有序列表合并
     *
     * @param n1
     * @param n2
     * @return
     */
    private static Node mergeTwoNode(Node n1, Node n2) {
        Node result = new Node(0);
        Node p = result;

        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                p.next = n1;
                n1 = n1.next;
            } else {
                p.next = n2;
                n2 = n2.next;
            }
            p = p.next;
        }

        if (n1 != null) {
            p.next = n1;
        }
        if (n2 != null) {
            p.next = n2;
        }

        return result.next;
    }

    private static class Node{
        int val;
        Node next;
        public Node(){}

        public Node(int val) {
            this.val = val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o){ return true;}

            if (o == null || getClass() != o.getClass()){ return false;}

            Node node = (Node) o;
            return val == node.val &&
                    Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
