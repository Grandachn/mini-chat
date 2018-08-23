package com.example.minichat.core.dto;

/**
 * @author liqiao
 * @version 2018.04.09
 */

public enum  ErrorResult {
    /**
     * 错误类型
     */
    ILLEGAL_PARAMS(400, "illegal arguments"),
    INVALID_TOKEN(401, "无效的token"),

    SMS_SEND_ERROR(101, "验证码发送失败"),
    SMS_VERIFY_ERROR(102, "验证码不正确"),
    PAGE_SIZE_TOO_LONG(103, "单页请求数据请不要大于100条"),
    SURVEY_GROUP_ID_NULL(104,  "不存在的surveyGroupId"),
    USER_NOT_TEST(105, "用户未进行测试"),
    UPDATE_USER_INFO_FAIL(106, "更新用户信息失败"),
    WRONG_PASSWORD(107, "密码错误"),
    SAVE_NOTE_FAIL(108, "保存小计失败"),
    USER_NOT_EXIST(109, "用户不存在");


    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
