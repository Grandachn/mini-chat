package com.example.minichat.interceptor;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liqiao
 * @version 2018.07.30
 */
@Data
public class SessionContext {

    private static ThreadLocal<String> threadLocalUid = new ThreadLocal<>();

    static void init(HttpServletRequest request) {
        if (StringUtils.isNotBlank(request.getHeader("X-Uid"))) {
            threadLocalUid.set(request.getHeader("X-Uid"));
        }
    }

    static void clear() {
        threadLocalUid.remove();
    }

    public static String getUid() {
        return threadLocalUid.get();
    }

}
