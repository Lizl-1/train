package com.lizl.train.member.req;

import jakarta.validation.constraints.NotBlank;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/4/8 下午2:18
 * @Version 1.0.0
 */
public class MemberRegisterReq {

    @NotBlank(message = "手机号不能为空")
    private String mobile;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MemberRegisterReq{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
