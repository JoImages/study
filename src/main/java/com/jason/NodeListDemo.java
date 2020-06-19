package com.jason;

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
        n1.next.next = new Node(5);

        Node n2 = new Node(2);
        n2.next = new Node(3);
        n2.next.next = new Node(4);
        n2.next.next.next = new Node(5);
        n2.next.next.next.next = new Node(6);

        System.out.println(mergeTwoNode(n1, n2));


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
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
