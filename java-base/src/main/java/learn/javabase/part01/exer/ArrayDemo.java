package learn.javabase.part01.exer;

/**
 * @author Administrator
 */
public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 2, 1, 0, 3};
        int[] index = new int[]{2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};
        String tel = "";
        for (int i = 0; i < index.length; i++) {
            tel += arr[index[i]];
        }
        System.out.println("联系方式：" + getTel());
    }

    public static String getTel(){
        int [] arr = {8,2,1,0,3};
        int [] index = {2,0,3,2,4,0,1,3,2,3,3};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < index.length; i++) {
            stringBuilder.append(arr[index[i]]);
        }
        return stringBuilder.toString();
    }
}
