package io.sanye.open.financial.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/31 上午11:23
 */
@SpringBootApplication
@EnableCaching
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class);
    }

}
