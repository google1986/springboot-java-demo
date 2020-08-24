package htzw.algo.array;

import org.junit.Test;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/7 14:57
 */
public class ArrayDemo {
    // 定义整数类型data保存数据
    private int[] data;
    // 定义数组长度
    private int n;
    // 定义实际个数
    private int count;

    /**
     * 定义构造方法
     */
    public ArrayDemo(){
        this.data = new int[10];
        this.n = 10;
        this.count = 0;
    }
    public ArrayDemo(int capatity){
        this.data = new int[capatity];
        this.n = capatity;
        this.count = 0;
    }

    /**
     * 根据索引，找到数据中的元素并返回
     * @param index
     * @return
     */
    public int find(int index){
        if (index < 0 || index >= count){
            return -1;
        }
        return data[index];
    }


    /**
     * 插入元素：头部插入、尾部插入
     * @param index
     * @param value
     * @return
     */
    public boolean insert(int index, int value){
        if (index == count && count==0){
            data[index] = value;
            ++count;
            return true;
        }
        if (count == n) {
            System.out.println("空间不足");
            return false;
        }
        if (index < 0 || index > count){
            System.out.println("位置不合法");
            return false;
        }
        //元素后移
        for (int i = count; i > index; --i) {
            data[i] = data[i-1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    /**
     * 根据索引，删除数组中的元素
     * @param index
     * @return
     */
    public boolean delete(int index){
        if (index < 0 || index >= count){
            return false;
        }
        for (int i = index+1; i <count ; i++) {
            data[i-1] = data[i];
        }
        --count;
        return true;
    }

    public void  printAll(){
        for (int i = 0; i < count; i++) {
            System.out.print(data[i]+" ");
        }
        System.out.println();
    }

    @Test
    public  void main(String[] args) {
        ArrayDemo arrayDemo = new ArrayDemo();
        arrayDemo.printAll();
        System.out.println("=================");
        arrayDemo.insert(0,3);
        arrayDemo.insert(0,4);
        arrayDemo.insert(1,5);
        arrayDemo.insert(3,9);
        arrayDemo.insert(3,30);

        arrayDemo.printAll();//4 5 3 30 9
    }
}
