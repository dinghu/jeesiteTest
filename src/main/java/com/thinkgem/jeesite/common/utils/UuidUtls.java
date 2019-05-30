package com.thinkgem.jeesite.common.utils;

import java.util.UUID;

public class UuidUtls {
    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        return IdGen.uuid();
    }
}
