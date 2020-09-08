package cn.jun.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

/**
 * @Author: jun
 * @Date: 2020/9/8 11:30
 * @Version: 1.0
 * @Description:
 */
@Repository
public class Sign {

    private String appid;
    private String signature;
    private String nonce;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp timestamp;

    public Sign(){

    }

    public Sign(String appid, String signature, String nonce, Timestamp timestamp) {
        this.appid = appid;
        this.signature = signature;
        this.nonce = nonce;
        this.timestamp = timestamp;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "appid='" + appid + '\'' +
                ", signature='" + signature + '\'' +
                ", nonce='" + nonce + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
