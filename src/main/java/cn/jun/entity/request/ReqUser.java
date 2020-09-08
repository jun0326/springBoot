package cn.jun.entity.request;

import cn.jun.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: jun
 * @Date: 2020/9/1 14:04
 * @Version: 1.0
 * @Description:
 */

@Repository
public class ReqUser {

    private Sign sign;

    private User req;

    public ReqUser(){

    }

    public ReqUser(Sign sign, User user) {
        this.sign = sign;
        this.req = user;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public User getReq() {
        return req;
    }

    public void setReq(User req) {
        this.req = req;
    }

    @Override
    public String toString() {
        return "ReqUser{" +
                "sign=" + sign +
                ", req=" + req +
                '}';
    }
}
