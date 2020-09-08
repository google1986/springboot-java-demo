package htzw.algo.bit;

import org.junit.Assert;
import org.junit.Test;

/**
 * 几种常见的位运算
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/24 10:47
 */
public class BitOperationDemo {
    /**
     * 1. 利用【或操作 `|` 和空格】将英文字符转换为小写
     */
    @Test
    public void test01() {
        System.out.println(('a' | ' ') == 'a');//true
        System.out.println(('A' | ' ') == 'a');//true
    }

    /**
     * 2. 利用【与操作 `&` 和下划线】将英文字符转换为大写
     */
    @Test
    public void test02() {
        System.out.println(('b' & '_') == 'B');//true
        System.out.println(('B' & '_') == 'B');//true
    }

    /**
     * 3. 利用【异或操作 `^` 和空格】进行英文字符大小写互换
     */
    @Test
    public void test03() {
        System.out.println(('d' ^ ' ') == 'D');//true
        System.out.println(('D' ^ ' ') == 'd');//true
    }

    /**
     * 4.判断两个数是否异号
     * 如果不用位运算来判断是否异号，需要使用 if else 分支，还挺麻烦的
     */
    @Test
    public void test04() {
        int x = -1, y = 2;
        System.out.println(((x ^ y) < 0));//true

        int xx = 3, yy = 2;
        System.out.println(((xx ^ yy) < 0));//false
        Assert.assertTrue((x^y) < 0);
        Assert.assertFalse((xx^yy)<0);
    }

    /**
     * 5.不用临时变量交换两个数
     */
    @Test
    public void test05() {
        int a = 1, b = 2;
        a ^= b;
        b ^= a;
        a ^= b;
        //a = 2, b = 1
        Assert.assertEquals(2, a);
        Assert.assertEquals(1, b);
    }

    /**
     * 6. 判断一个数是不是 2 的指数
     * 一个数如果是 2 的指数，那么它的二进制表示一定只含有一个 1：
     * <p>
     * 2^0 = 1 = 000001
     * 2^1 = 2 = 000010
     * 2^2 = 4 = 000100
     *
     * @param n
     * @return
     */
    private boolean isPowerOfTwo(int n) {
        if (n < 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    /**
     * 7.查找只出现一次的元素
     *
     * 这里就可以运用异或运算的性质：
     *      一个数和它本身做异或运算结果为 0，即 `a ^ a = 0`；
     *      一个数和0做异或运算的结果为它本身，即 `a ^ 0 = a`
     * 对此，只要把所有数字进行异或，成对儿的数字就会变成 0，落单的数字和 0 做异或还是它本身，所以最后异或的结果就是只出现一次的元素
     */
    @Test
    public void getSingleData() {

        int result = 0;
        int[] arr = new int[]{1, 2, 3, 4, 3, 2, 1};
        for (int value : arr) {
            result ^= value;
        }
        Assert.assertEquals(4, result);
    }
    /**
     * 8.乘以2运算
     * 计算n*2
     * @param n
     */
    @Test
    public void mulTwo(int n){
        int res = n << 1;
        System.out.println(res);
    }

    /**
     * 9.除以2运算
     *
     * 负奇数的运算不可用
     *
     * @param n
     */
    @Test
    public void divTwo(int n){
        int res = n >> 1;//除以2
        System.out.println(res);
    }

    /**
     * 10.乘以2的m次方
     *计算n*(2^m)
     * @param n
     * @param m
     */
    @Test
    public void mulTwoPower(int n, int m){
        int res = n << m;
        System.out.println(res);
    }

    /**
     * 11.除以2的m次方
     * 计算n/(2^m)
     * @param n
     * @param m
     */
    @Test
    public void divTwoPower(int n, int m){
        int res = n >> m;
        System.out.println(res);
    }

    /**
     * 12.判断一个数的奇偶性
     *
     * @param n
     * 奇数的话，返回true，偶数的话，返回false
     */
    @Test
    public void  isOldNumber(int n){
        boolean res = (n&1) == 1;
        System.out.println(res);
    }
}
