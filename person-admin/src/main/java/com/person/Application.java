package com.person;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:20
 * @Description:
 * 1. gitcode主页： https://gitcode.com/user/tbb414/repos （推荐）
 * 2. github主页：https://github.com/doudoutangs
 */
@Slf4j
@SpringBootApplication
public class Application {


    public static final String version = "1.0.0";

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment env = app.getEnvironment();
        String appName = Optional.ofNullable(env.getProperty("spring.application.name")).orElse("");
        String path = Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse("");
        String port = env.getProperty("server.port");
        String ip = "127.0.0.1";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        log.info("\n---------------------------人事管理系統，版本号：" + version + " 启动成功-----------------------\n\t\t\t" +
                "Application: " + appName + "\n\t\t\t" +
                "联系方式: 0. QQ:553039957" + "\n\t\t\t" +
                "联系方式: 1. gitcode主页：\t https://gitcode.com/user/tbb414/repos （推荐）" + "\n\t\t\t" +
                "联系方式: 2. github主页：\t https://github.com/doudoutangs" + "\n\t\t\t" +
                "登录地址: \t\t\t\t http://" + ip + ":" + port + path + "\n" +
                "--------------------------------------------------------------------------------");
    }
}
