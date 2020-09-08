package cn.jun.service.impl;

import cn.jun.entity.request.Sign;
import cn.jun.mapper.SignMapper;
import cn.jun.mapper.UserMapper;
import cn.jun.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: jun
 * @Date: 2020/9/8 14:11
 * @Version: 1.0
 * @Description:
 */
@Service("signService")
public class SignServiceImpl implements SignService {

//    @Autowired
    @Resource
    private SignMapper signMapper;

    @Autowired
    private UserMapper userMapper;

    public Sign selectByAppid(Sign sign) {
        System.out.println(signMapper + "=============" + userMapper);

        return signMapper.selectByAppid(sign.getAppid(),sign.getSignature());
    }
}
