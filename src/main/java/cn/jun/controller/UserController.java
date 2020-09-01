package cn.jun.controller;

import cn.jun.entity.User;
import cn.jun.entity.request.ReqUser;
import cn.jun.entity.response.RespCode;
import cn.jun.entity.response.RespEntity;
import cn.jun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: jun
 * @Date: 2020/9/1 10:45
 * @Version: 1.0
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public RespEntity selectByUserName(@RequestBody ReqUser reqUser) {
        System.out.println(reqUser);
        List<User> users = userService.selectByUserName(reqUser);
        return new RespEntity(RespCode.SUCCESS,users);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public User selectByPrimaryKey(@PathVariable("id") Integer id) {

        return userService.selectByPrimaryKey(id);
    }
}
