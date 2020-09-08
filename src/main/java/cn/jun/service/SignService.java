package cn.jun.service;

import cn.jun.entity.request.Sign;

/**
 * @Author: jun
 * @Date: 2020/9/8 14:08
 * @Version: 1.0
 * @Description:
 */
public interface SignService {

    Sign selectByAppid(Sign sign);
}
