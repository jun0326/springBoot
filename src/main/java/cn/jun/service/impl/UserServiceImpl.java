package cn.jun.service.impl;

import cn.jun.entity.User;
import cn.jun.entity.request.ReqUser;
import cn.jun.mapper.UserMapper;
import cn.jun.service.UserService;
import org.springframework.stereotype.Service;

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
        if (reqUser.getReq() != null){
            user.setUserId(reqUser.getReq().getUserId());
            user.setUserName(reqUser.getReq().getUserName());
            user.setPassword(reqUser.getReq().getPassword());
            user.setPhone(reqUser.getReq().getPhone());
        }
        return userMapper.selectByUserName(user);
    }

    public User selectByPrimaryKey(ReqUser reqUser) {
        return userMapper.selectByPrimaryKey(reqUser.getReq().getUserId());
    }
}
