package com.example.minichat.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author lihailian
 * @date 20180711
 */
@Data
@Builder
public class Result<E> implements Serializable {


    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    private static final long serialVersionUID = -4578560278556203640L;

    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;


    public static <T> Result<T> success(T data) {
        return Result.<T>builder().code(0).msg("OK").data(data).build();
    }

    public static <T> Result<T> success() {
        return Result.<T>builder().code(0).msg("OK").data(null).build();
    }

    public static <T> Result<T> error(ErrorResult errorResult) {
        return Result.<T>builder().code(errorResult.getCode()).msg(errorResult.getMessage()).data(null).build();
    }

    public static <T> Result<T> error(int code, String message) {
        return Result.<T>builder().code(code).msg(message).data(null).build();
    }

}
