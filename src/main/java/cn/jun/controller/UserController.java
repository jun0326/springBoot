package cn.jun.controller;

import cn.jun.common.utils.AuthCheckUtils;
import cn.jun.entity.User;
import cn.jun.entity.request.ReqUser;
import cn.jun.entity.response.RespCode;
import cn.jun.entity.response.RespEntity;
import cn.jun.service.SignService;
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

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/selectByName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public RespEntity electByUserName(@RequestBody ReqUser reqUser) {
        boolean flag = AuthCheckUtils.authCheck(reqUser);
        if(flag){
            List<User> users = userService.selectByUserName(reqUser);
            return new RespEntity(RespCode.SUCCESS,users);
        }else{
            return new RespEntity(RespCode.WARN,null);
        }
    }

    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public RespEntity selectByPrimaryKey(@RequestBody ReqUser reqUser) {
        boolean flag = AuthCheckUtils.authCheck(reqUser);
        if(flag){
            User user = userService.selectByPrimaryKey(reqUser);
            return new RespEntity(RespCode.SUCCESS,user);
        }else{
            return new RespEntity(RespCode.WARN,null);
        }
    }

    @RequestMapping(value = "/selectByUserName/{userName}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public User selectByUserName(@PathVariable("userName") String userName) {
        return new User();
    }
}
