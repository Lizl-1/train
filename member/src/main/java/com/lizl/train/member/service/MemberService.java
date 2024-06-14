package com.lizl.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.lizl.train.common.exception.BusinessException;
import com.lizl.train.common.exception.BusinessExceptionEnum;
import com.lizl.train.common.util.SnowUtil;
import com.lizl.train.member.domain.Member;
import com.lizl.train.member.domain.MemberExample;
import com.lizl.train.member.mapper.MemberMapper;
import com.lizl.train.member.req.MemberLoginReq;
import com.lizl.train.member.req.MemberRegisterReq;
import com.lizl.train.member.req.MemberSendCodeReq;
import com.lizl.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        // 手机号不存在，立即注册
        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在，立即注册");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号已存在，无需注册");
        }

        //  生成验证码
        String code = RandomUtil.randomString(4);
        LOG.info("生成短信验证码：{}", code);
        // 保存短信记录表 手机号 短信验证码 有效期 是否使用 业务类型 发送时间 使用时间
        LOG.info("保存短信记录表");        // 对接短信通道，发送短信
        LOG.info("对接短信通道");
    }

    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);
        // 手机号不存在
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        // 校验数据库中验证码
        if (!"1234".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        return BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
