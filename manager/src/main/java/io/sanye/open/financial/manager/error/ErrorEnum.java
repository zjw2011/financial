package io.sanye.open.financial.manager.error;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/30 下午2:37
 */
public enum ErrorEnum {
    ID_NOT_NULL("F001", "编号不能为空", false),
    //...
    UNKNOWN("999", "未知异常", false)
    ;

    private String code;
    private String message;
    private boolean canRetry;

    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }

    public void setCanRetry(boolean canRetry) {
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code) {
        for(ErrorEnum errorEnum: ErrorEnum.values()) {
            if (errorEnum.getCode().equals(code)) {
                return errorEnum;
            }
        }
        return UNKNOWN;
    }
}
