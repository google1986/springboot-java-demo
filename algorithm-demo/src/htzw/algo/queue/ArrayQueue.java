package htzw.algo.queue;

/**
 * 使用数组实现队列
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/8 17:00
 */
public class ArrayQueue {
    // 数组，items，数组大小n
    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item){
        //如果tail==n表示队列已经满了
        if (tail==n) {
            return false;
        }
        items[tail++] = item;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        //如果head==tail表示队列为空
        if (head== tail) {
            return null;
        }
        return items[head++];
    }
    public void printAll(){
        for (int i = head; i < tail; i++) {
            System.out.print(items[i]+"\t");
        }
        System.out.println();
    }
}
