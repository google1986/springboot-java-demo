package htzw.algo.queue;

/**
 * 基于链表实现队列
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/9 14:02
 */
public class QueueBasedOnLinkedList {

    // 队列的队头
    private Node head = null;
    private Node tail = null;

    /**
     * 入队
     * @param value
     */
    public void enqueue(String value){
        if (tail == null) {
            Node node = new Node(value,null);
            head = node;
            tail = node;
        }else {
            tail.next = new Node(value,null);
            tail = tail.next;
        }
    }

    /**
     *出队
     * @return
     */
    public String dequeue(){
        if (head == null) {
            return null;
        }
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public void printAll(){
        Node p = head;
        while (p!= null){
            System.out.print(p.data+" ");
            p = p.next;
        }
        System.out.println();
    }

    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
        public String getData(){
            return data;
        }
    }
}
