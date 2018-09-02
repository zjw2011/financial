package io.sanye.open.financial.seller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.sanye.open.financial.util.JsonUtil;

/**
 * 签名明文.
 *
 * TODO: 可否用Gson或者fastjson替换
 * @author jiawei zhang
 * 2018/9/2 下午7:33
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {
    default String toText() {
        return JsonUtil.toJson(this);
    }
}
