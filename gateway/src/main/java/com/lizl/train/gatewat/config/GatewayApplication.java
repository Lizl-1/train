package com.lizl.train.gatewat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @Author Lizl
 * @Description
 * @Date 2024/03/26/0026 17:36
 * @Version 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.lizl")
public class GatewayApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("测试地址:\thttp://127.0.0.1:{}/hello", env.getProperty("server.port"));
    }
}
