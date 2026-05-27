package com.caiza.clinical_alerts.security.model;

import java.lang.ThreadLocal;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

    public static  void setContext(UserContext userContext) {
        CONTEXT.set(userContext);
    }

    public static UserContext getContext() {
        return CONTEXT.get();
    }

     public static void clear() {
        CONTEXT.remove();
    }
}
