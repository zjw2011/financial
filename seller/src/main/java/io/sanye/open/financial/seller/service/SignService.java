package io.sanye.open.financial.seller.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 签名服务.
 *
 * @author jiawei zhang
 * 2018/9/2 下午7:44
 */
@Service
public class SignService {

    private static Map<String, String> PUBLIC_KEYS = new HashMap<>();

    static {
        PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoYw8VYVXz7s0FEWF4o9wFM1C3\n" +
                "sjWH1yM1LFfVHhLqxzEsB8gCJybN+D9VLIlLwVf77S+EV+b2gHsTeuHQvuwsMiN8\n" +
                "5KJ2vDSwZ1eTjFO6/z1oOHiX/GM3kd65MvaDvlRjnMcWLtLikZGpgByUtfr4+jSR\n" +
                "g7cK/BEo7o4X48Ox7QIDAQAB");
    }

    //根据授权编号获取公钥
    public String getPublicKey(String authId) {
        return PUBLIC_KEYS.get(authId);
    }
}
