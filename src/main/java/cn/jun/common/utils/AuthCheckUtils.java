package cn.jun.common.utils;

import cn.jun.entity.request.ReqUser;
import cn.jun.entity.request.Sign;
import cn.jun.service.SignService;
import cn.jun.service.impl.SignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 权限验证公共类
 *
 * @Author: jun
 * @Date: 2020/9/8 13:57
 * @Version: 1.0
 * @Description:
 */
@Component
public class AuthCheckUtils {

    @Autowired
    private SignService signService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public static AuthCheckUtils authCheckUtils;

    @PostConstruct
    public void init() {
        authCheckUtils = this;
    }


    public static boolean authCheck(ReqUser reqUser) {
        boolean flag = false;
        String appid = reqUser.getSign().getAppid();
        String signature = reqUser.getSign().getSignature();

        if ((appid != null && appid != "")
                && (signature != null && signature != "")) {
            if (!querySignCache(appid,signature)){
                Sign sign = authCheckUtils.signService.selectByAppid(reqUser.getSign());
                authCheckUtils.redisTemplate.opsForValue().set(sign.getAppid(),sign.getSignature());
                if (sign != null) {
                    flag = true;
                }
            }else {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean querySignCache(String appid,String signature) {
        String signature_redis = authCheckUtils.redisTemplate.opsForValue().get(appid);
        return signature.equals(signature_redis) ? true:false;
    }

}
