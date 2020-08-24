package io;

import org.junit.Test;

import java.io.*;

/**
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/14 16:15
 */
public class ObjectInputOutputStreamTest {

    /**
     * 序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
     *     使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.data"))){
            oos.writeObject(new String("dfdfdf撒大声地所"));
            oos.flush();
            oos.writeObject(new Person("张三", 33));
            oos.flush();
            oos.writeObject(new Person("ddd",22,3000, new Account(57777)));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化：将磁盘文件中的对象还原为内存中的一个java对象
     *     使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.data"))){
            Object object = ois.readObject();
            String str = (String)object;
            Person person1 = (Person) ois.readObject();
            Person person2 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(person1);
            System.out.println(person2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
