package com.lizl.train.member.controller;

import com.lizl.train.common.context.LoginMemberContext;
import com.lizl.train.common.resp.CommonResp;
import com.lizl.train.member.req.PassengerQueryReq;
import com.lizl.train.member.req.PassengerSaveReq;
import com.lizl.train.member.resp.PassengerQueryResp;
import com.lizl.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/03/26/0026 17:40
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<List<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

}
