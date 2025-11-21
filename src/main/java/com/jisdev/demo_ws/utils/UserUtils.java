package com.jisdev.demo_ws.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtils {

    public static String generateUserId() {
        return java.util.UUID.randomUUID().toString();
    }

}
