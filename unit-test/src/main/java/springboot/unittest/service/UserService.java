package springboot.unittest.service;


import springboot.unittest.entity.User;
import springboot.unittest.result.FwResult;

/**
 */
public interface UserService {
    /**
     * 根据id查询用户信息
     * @Param id
     * @Return
     */
    FwResult getUserById(Long id);
    /**
     *
     * @Param [id]
     * @Return
     */
    FwResult deleteUserById(Long id);
    /**
     * 创建用户
     * @Param [user]
     */
    FwResult createUser(User user);
    /**
     * 跟新用户
     * @Param [user]
     */
    FwResult updateUser(User user);
}
