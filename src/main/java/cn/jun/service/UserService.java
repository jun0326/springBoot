package cn.jun.service;

import cn.jun.entity.User;
import cn.jun.entity.request.ReqUser;

import java.util.List;

/**
 * @Author: jun
 * @Date: 2020/9/1 10:50
 * @Version: 1.0
 * @Description:
 */
public interface UserService {

    List<User> selectByUserName(ReqUser reqUser);


    User selectByPrimaryKey(ReqUser reqUser);
}
