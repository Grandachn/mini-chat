package com.example.minichat.core.annotation;

import java.lang.annotation.*;

/**
 * 如果一个接口不需要进行用户登录态校验的话，加上此注解
 *
 * @author liqiao
 * @version 2018.04.19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OpenApi {

}
