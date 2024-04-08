package com.lizl.train.member.controller;

import com.lizl.train.common.resp.CommonResp;
import com.lizl.train.member.req.MemberRegisterReq;
import com.lizl.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/03/26/0026 17:40
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.count();
        return new CommonResp<>(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

}