package com.jiawa.train.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lizl.train.common.context.LoginMemberContext;
import com.lizl.train.common.resp.MemberLoginResp;
import com.lizl.train.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/7/7 下午6:59
 * @Version 1.0.0
 */
@Component
public class MemberInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            LOG.info("获取登录会员信息：{}", token);
            JSONObject loginMember = JwtUtil.getJSONObject(token);
            LOG.info("当前会员登录信息：{}", loginMember);
            LoginMemberContext.setMember(JSONUtil.toBean(loginMember, MemberLoginResp.class));
        }
        return true;
    }

}
