package htzw.algo.stack;

/**
 * 基于链表实现的栈
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/8 16:37
 */
public class StackBasedOnLinkedList {
    private Node top = null;

    /**
     * 压入数据
     * @param value
     */
    public void push(int value) {
        Node node = new Node(value, null);
        if (top == null) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
    }

    /**
     * 用-1表示栈中没有数据
     * @return
     */
    public int pop(){
        if (top == null) {
            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

    public void printAll(){
        Node p = top;
        while (p != null) {
            System.out.println(p.data+" ");
            p = p.next;
        }
    }


    private static class Node{
        private int data;
        private Node next;
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
        public int getData(){
            return data;
        }
    }

}

