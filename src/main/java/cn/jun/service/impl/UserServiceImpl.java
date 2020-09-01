package cn.jun.service.impl;

import cn.jun.entity.User;
import cn.jun.entity.request.ReqUser;
import cn.jun.mapper.UserMapper;
import cn.jun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: jun
 * @Date: 2020/9/1 10:53
 * @Version: 1.0
 * @Description:
 */

@Service("userService")
public class UserServiceImpl implements UserService {

//    @Autowired
    @Resource
    private UserMapper userMapper;

    public List<User> selectByUserName(ReqUser reqUser) {

        User user = new User();
        user.setUserId(reqUser.getUserId());
        user.setUserName(reqUser.getUserName());
        user.setPassword(reqUser.getPassword());
        user.setPhone(reqUser.getPhone());
        return userMapper.selectByUserName(user);
    }

    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
