package cn.jun.entity.response;

import org.springframework.stereotype.Repository;

/**
 * @Author: jun
 * @Date: 2020/9/1 14:08
 * @Version: 1.0
 * @Description:
 */


public enum  RespCode {

    SUCCESS(0,"请求成功"),
    WARN(-1,"请求失败，请与管理员联系");

    private int code;
    private String message;

    RespCode(int code,String message){
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
