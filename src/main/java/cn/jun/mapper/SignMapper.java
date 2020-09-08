package cn.jun.mapper;

import cn.jun.entity.request.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: jun
 * @Date: 2020/9/8 14:07
 * @Version: 1.0
 * @Description:
 */

@Mapper
public interface SignMapper {

    @Select({"select appid,signature,nonce,timestamp from system_auth where appid = #{appid,jdbcType=VARCHAR} and signature = #{signature,jdbcType=VARCHAR}"})
    Sign selectByAppid(@Param("appid") String appid, @Param("signature") String signature);
}
