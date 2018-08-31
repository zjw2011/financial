package io.sanye.open.financial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.sanye.open.financial.swagger.EnableMySwagger;

/**
 * 启动.
 *
 * @author jiawei zhang
 */
@SpringBootApplication
@EnableMySwagger
public class ManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class, args);
    }
}
