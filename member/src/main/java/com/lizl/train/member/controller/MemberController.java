package com.lizl.train.member.controller;

import com.lizl.train.common.resp.CommonResp;
import com.lizl.train.member.req.MemberLoginReq;
import com.lizl.train.member.req.MemberRegisterReq;
import com.lizl.train.member.req.MemberSendCodeReq;
import com.lizl.train.member.resp.MemberLoginResp;
import com.lizl.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long> sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }

}
