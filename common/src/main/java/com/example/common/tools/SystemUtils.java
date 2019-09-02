package com.example.common.tools;

import com.example.common.entity.System;

public class SystemUtils {
    private static System system;

    public static System getSystem() {
        return system;
    }

    public static void setSystem(System system) {
        SystemUtils.system = system;
    }
}
