package com.lizl.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.lizl.train.common.context.LoginMemberContext;
import com.lizl.train.common.util.SnowUtil;
import com.lizl.train.member.domain.Passenger;
import com.lizl.train.member.domain.PassengerExample;
import com.lizl.train.member.mapper.PassengerMapper;
import com.lizl.train.member.req.PassengerQueryReq;
import com.lizl.train.member.req.PassengerSaveReq;
import com.lizl.train.member.resp.PassengerQueryResp;
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
public class PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample= new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        List<Passenger> passengersList = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(passengersList, PassengerQueryResp.class);
    }
}
