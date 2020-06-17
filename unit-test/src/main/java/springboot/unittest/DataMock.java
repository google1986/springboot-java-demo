package springboot.unittest;


import springboot.unittest.entity.User;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 模拟数据
 */
public class DataMock {

    public static List<User> getUserAll() {
        return Arrays.asList(new User(1L, "张三", "123456", "男"),
                new User(2L, "李四", "123456", "男"),
                new User(3L, "王五", "123456", "女"));
    }

}
