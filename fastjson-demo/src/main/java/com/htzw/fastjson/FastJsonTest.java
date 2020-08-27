package com.htzw.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fastjson 是一个 Java 库，可以将 Java 对象转换为 JSON 格式，当然它也可以将 JSON 字符串转换为 Java 对象。
 * Fastjson 可以操作任何 Java 对象，即使是一些预先存在的没有源码的对象。
 *
 * 提供服务器端、安卓客户端两种解析工具，性能表现较好。
 * 提供了 toJSONString() 和 parseObject() 方法来将 Java 对象与 JSON 相互转换。调用toJSONString方 法即可将对象转换成 JSON 字符串，parseObject 方法则反过来将 JSON 字符串转换成对象。
 * 允许转换预先存在的无法修改的对象（只有class、无源代码）。
 * Java泛型的广泛支持。
 * 允许对象的自定义表示、允许自定义序列化类。
 * 支持任意复杂对象（具有深厚的继承层次和广泛使用的泛型类型）。
 *
 *
 * 反序列化能够自动识别如下日期格式：
 *
 * ISO-8601日期格式
 * yyyy-MM-dd
 * yyyy-MM-dd HH:mm:ss
 * yyyy-MM-dd HH:mm:ss.SSS
 * 毫秒数字
 * 毫秒数字字符串
 * .NET JSON日期格式
 * new Date(1982932381111)
 *
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/27 9:35
 */
public class FastJsonTest {

    private List<Person> listOfPersons = new ArrayList<>();

    private List<Person2> person2List = new ArrayList<>();

    @Before
    public void setUp() {
        listOfPersons.add(new Person(15, "John Doe", new Date()));
        listOfPersons.add(new Person(20, "Janette Doe", new Date()));
        listOfPersons.add(new Person(45, "Mark", new Date()));
        listOfPersons.add(new Person(66, "Jack", new Date()));

        person2List.add(new Person2(23,"zhang","san",new Date()));
        person2List.add(new Person2(34,"wang","dd",new Date()));
        person2List.add(new Person2(44,"sun","ff",new Date()));
        person2List.add(new Person2(55,"li","n",new Date()));

    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput= JSON.toJSONString(listOfPersons);
        System.out.println(jsonOutput);
    }

    @Test
    public void testJsonList(){
        String string = JSON.toJSONString(person2List);
        System.out.println(string);
    }

    /**
     * 反序列化能够自动识别如下日期格式：
     *
     * ISO-8601日期格式
     * yyyy-MM-dd
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss.SSS
     * 毫秒数字
     * 毫秒数字字符串
     * .NET JSON日期格式
     * new Date(1982932381111)
     */
    @Test
    public void deserializeJson(){
        Person2 person = new Person2(55, "li", "n", new Date());
        String string = JSON.toJSONString(person);

        Person2 newPerson = JSON.parseObject(string, Person2.class);
        Assert.assertEquals(person.getAge(),newPerson.getAge());
        Assert.assertEquals(person.getDateOfBirth(),newPerson.getDateOfBirth());
    }
}
