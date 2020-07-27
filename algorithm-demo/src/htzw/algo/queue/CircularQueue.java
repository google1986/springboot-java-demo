package htzw.algo.queue;

/**
 * 循环队列
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/9 13:53
 */
public class CircularQueue {
    // 数组items，数组大小为n
    private String[] items;
    private int n = 0;
    // head表示队头下标；tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    //申请一个大小为capacity的数组
    public CircularQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队操作
     * @param item
     * @return
     */
    public boolean enqueue(String item){
        if ((tail+1)%n == head){
            return false;
        }
        items[tail] = item;
        tail = (tail+1) % n;
        return true;
    }

    /**
     * 出队操作
     * @return
     */
    public String dequeue(){
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head+1)%n;
        return ret;
    }

    public void printAll(){
        if (n==0){
            return;
        }
        for (int i = head; i %n != tail;i = (i+1)%n) {
            System.out.print(items[i]+" ");
        }
        System.out.println();
    }
}
