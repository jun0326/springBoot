package cn.jun.entity.response;

import org.springframework.stereotype.Repository;

/**
 * @Author: jun
 * @Date: 2020/9/1 14:06
 * @Version: 1.0
 * @Description:
 */

public class RespEntity {

    private int code;
    private String message;
    private Object data;

    public RespEntity(RespCode respCode){
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }

    public RespEntity(RespCode respCode,Object data){
        this(respCode);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
