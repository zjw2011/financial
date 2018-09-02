package io.sanye.open.financial.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

import io.sanye.open.financial.swagger.EnableMySwagger;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 上午11:23
 */
@SpringBootApplication
@EnableCaching
@EntityScan("io.sanye.open.financial.entity")
@EnableMySwagger
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }

}
