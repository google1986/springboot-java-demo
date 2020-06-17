package springboot.unittest.service.impl;

import org.springframework.stereotype.Service;
import springboot.unittest.DataMock;
import springboot.unittest.entity.User;
import springboot.unittest.result.FwResult;
import springboot.unittest.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Description 接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public FwResult getUserById(Long id) {
        List<User> collect = DataMock.getUserAll().stream().filter(user -> user.getId() == id).collect(Collectors.toList());
        return FwResult.ok(collect.get(0));
    }

    @Override
    public FwResult deleteUserById(Long id) {
        boolean b = DataMock.getUserAll().removeIf(user -> user.getId() == id);
        if(!b)
            return FwResult.failed("可能没有对应的用户信息");
        return FwResult.ok("删除成功");
    }

    @Override
    public FwResult createUser(User user) {
        user.setId(333422L);
        DataMock.getUserAll().add(user);
        return FwResult.ok(user);
    }

    @Override
    public FwResult updateUser(User user) {
        List<User> userAll = DataMock.getUserAll();
        for (User use:userAll) {
            if(use.getId()==user.getId())
            {
                use.setUserName(user.getUserName());
                use.setSex(user.getSex());
                use.setPassword(user.getPassword());
                return FwResult.ok("更新成功");
            }
        }
        return FwResult.failed("更新失败");
    }
}
