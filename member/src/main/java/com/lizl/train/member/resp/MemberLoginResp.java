package com.lizl.train.member.resp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/4/8 下午2:18
 * @Version 1.0.0
 */
public class MemberLoginResp {

    private String id;

    private String mobile;

    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MemberLoginResp{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
