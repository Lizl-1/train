package com.lizl.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.lizl.train.common.exception.BusinessException;
import com.lizl.train.common.exception.BusinessExceptionEnum;
import com.lizl.train.member.domain.Member;
import com.lizl.train.member.domain.MemberExample;
import com.lizl.train.member.mapper.MemberMapper;
import com.lizl.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
