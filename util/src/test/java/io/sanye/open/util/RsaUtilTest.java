package io.sanye.open.util;

import io.sanye.open.financial.util.RSAUtil;
import org.junit.Test;

/**
 * 测试加签验签.
 *
 * @author jiawei zhang
 * 2018/9/2 上午12:14
 */
public class RsaUtilTest {
    private static final String publicKey  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCoYw8VYVXz7s0FEWF4o9wFM1C3\n" +
            "sjWH1yM1LFfVHhLqxzEsB8gCJybN+D9VLIlLwVf77S+EV+b2gHsTeuHQvuwsMiN8\n" +
            "5KJ2vDSwZ1eTjFO6/z1oOHiX/GM3kd65MvaDvlRjnMcWLtLikZGpgByUtfr4+jSR\n" +
            "g7cK/BEo7o4X48Ox7QIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKhjDxVhVfPuzQUR\n" +
            "YXij3AUzULeyNYfXIzUsV9UeEurHMSwHyAInJs34P1UsiUvBV/vtL4RX5vaAexN6\n" +
            "4dC+7CwyI3zkona8NLBnV5OMU7r/PWg4eJf8YzeR3rky9oO+VGOcxxYu0uKRkamA\n" +
            "HJS1+vj6NJGDtwr8ESjujhfjw7HtAgMBAAECgYAQYIIJDEx1yxX5ljUqrJUrlFAX\n" +
            "baezLINqGoP4YFJqrzDjHWagfvpRMgHG4OwBiC5sfCcQ8hOuidRukpb/3HHPuik+\n" +
            "yPHQBoIhYuIPX8LRdNQdatDkk+48FFvDotmYLG+5TxfcbX0oxEUzUBGlkVCARbZX\n" +
            "1WBbAcxZ46SHgQhRwQJBANoRBvkox5Hi3eUJkSAzBwxh6rq9Xt9wUjTkCiecaybS\n" +
            "yCLqh6Zztujvu9JpB2FK1DS8Zb3SKQawdtHMCAdKdqkCQQDFrbGDq3KWHH1nGKmW\n" +
            "sWbuDhX+UkLMq5DWCYta01kDJc337UisxrNu4RToOIG5p85peJdCyz4/TcBUkWsh\n" +
            "KN+lAkEAqfqt0dgcwDX0O01ax5ue+eL1gnyMkGTQX5QF6lx+FbQl9zmTZYc7Y5VD\n" +
            "db8S1p7qjPTx5aYE/sbcx6pf87byiQJALDykfvy9Fc6mWY3xVaG5kQhh75nlDPxP\n" +
            "2zYjk128i7HIF/Vn0BJfUS7ZiOYsUam1zXCJGQ92jKAPrF0mhjMpaQJAKtYU5TSB\n" +
            "/Rjan0TCNGoaTbphnLjSad/BdabXXThTZTexnUrkSyCxeTcnNYfRcIi+y9k1MPdb\n" +
            "777LaljC6tKjng==";


    @Test
    public void signTest() {
        String text = "{\"amount\":10,\"chanId\":\"111\",\"chanUserId\":\"123\",\"createAt\":\"2018-09-02 11:54:09\",\"memo\":\"string\",\"outerOrderId\":\"10002\",\"productId\":\"T001\"}";
        String sign = RSAUtil.sign(text, privateKey);
        System.out.println(sign);
        boolean verify = RSAUtil.verify(text, sign, publicKey);
        System.out.println(verify);
    }

}
