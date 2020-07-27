package htzw.algo.linkedlist;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/7 17:23
 */
public class SinglyLinkedList {
    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 没有头结点
     * 表头部插入
     */
    public void  insertToHead(int value){
        Node newNode = new Node(value,null);
        insertToHead(newNode);
    }

    private void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        }else {
            newNode.next = head.next;
            head.next = newNode;
        }
    }

    public void  insertTail(int value){
        Node newNode = new Node(value,null);
        if (head == null) {
            head = newNode;
        }else {
            Node q = head;
            while (q.next != null) {
                q = q.next;
            }
            newNode.next = q.next;
            q.next = newNode;
        }
    }

    public void insertAfter(Node p, int value){
        Node newNode = new Node(value,null);
        insertAfter(p, newNode);
    }

    public void  insertAfter(Node p, Node newNode){
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void  insertBefore(Node p ,int value){
        Node newNode = new Node(value, null);
        if (p == null) {
            return;
        }
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q= q.next;
        }
        newNode.next = p;
        q.next = newNode;
    }

    public  void deleteByNode(Node p){
        if (p == null || head ==null) {
            return;
        }
        if (p == head){
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q=q.next;
        }
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

}
