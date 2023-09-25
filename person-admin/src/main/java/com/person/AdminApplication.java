package com.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:20
 * @Description: 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("人事管理系統，启动成功！");
        System.out.println(" * 0. QQ:553039957\n" +
                " * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）\n" +
                " * 2. github主页：https://github.com/doudoutangs\n" +
                " * 3. gitee(码云)主页：https://gitee.com/spdoudoutang");

    }
}
