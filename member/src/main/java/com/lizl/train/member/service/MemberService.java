package com.lizl.train.member.service;

import com.lizl.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/04/07/0007 14:40
 * @Version 1.0.0
 */
@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;
    public int count() {
        return memberMapper.count();
    }
}
