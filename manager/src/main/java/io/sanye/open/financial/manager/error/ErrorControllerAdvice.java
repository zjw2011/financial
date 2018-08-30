package io.sanye.open.financial.manager.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/30 下午3:31
 */
//@ControllerAdvice 默认是所有的
//@ControllerAdvice(basePackages = { "io.sanye.open.financial.manager.controller"})
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception e) {
        Map<String, Object> attrs = new HashMap<>();

        String errorCode = e.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
        attrs.put("message", errorEnum.getMessage());
        attrs.put("code", errorEnum.getCode());
        attrs.put("canRetry", errorEnum.isCanRetry());
        attrs.put("type", "advice");
        Assert.isNull(attrs, "advice");
        return new ResponseEntity(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
