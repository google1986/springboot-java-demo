package htzw.algo.sorts;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/7/9 14:29
 */
public class BubbleDemo {
    /**
     *冒泡排序
     *
     * @param a 数组
     * @param n 数组大小
     */
    public static void bubbleSort(int[] a, int n){
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] >a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {//没有数据交换，提前退出
                break;
            }
        }
    }


    /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.
     * @param a
     * @param n
     */
    public static void bubbleSort2(int[] a, int n){
        if (n<=1) {
            return;
        }
        //最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界，每次只需比较到这里就即可退出
        int sortBorder = n-1;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] >a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    //此次冒泡有数据交换
                    flag = true;
                    //更新最后一次交换的位置
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag){
                break;
            }
        }
    }

    public static void insertionSort(int[] a, int n){
        if (n <=1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            for (int j = i; j>0;j--){
                if (a[j] < a[j-1]){
                    int temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序，a表示数组，n表示数组大小
     * @param a
     * @param n
     */
    public static void selectionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 0; i < n - 1; ++i) {
            // 查找最小值
            int minIndex = i;
            boolean flag = false;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                    flag = true;
                }
            }

            // 交换
            if (flag) {
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        insertionSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

}
