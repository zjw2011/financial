package io.sanye.open.financial.seller.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.sanye.open.financial.seller.service.SignService;
import io.sanye.open.financial.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 验签.
 *
 * @author jiawei zhang
 * 2018/9/2 下午7:39
 */
@Component
@Aspect
public class SignAop {

    @Autowired
    private SignService signService;

    @Before(value = "execution(* io.sanye.open.financial.seller.controller.*.*(..)) && args(authId, sign, text, ..)")
    public void verify(String authId, String sign, SignText text) {
        String publicKey = signService.getPublicKey(authId);
        Assert.isTrue(RSAUtil.verify(text.toText(), sign, publicKey), "验签失败");
    }
}
