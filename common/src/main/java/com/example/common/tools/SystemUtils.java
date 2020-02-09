package com.example.common.tools;

import com.example.common.entity.System;
import com.example.common.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class SystemUtils {
    @Autowired
    private SystemService systemService;

    private static SystemService staticSystem;

    private volatile static System system;

    @PostConstruct
    private void beforeInit() {
        staticSystem = this.systemService;
    }

    public static System getSystem() {
        if (system == null) {
            synchronized (SystemUtils.class) {
                if (system == null) {
                    system = staticSystem.selectSystem();
                }
            }
        }
        return system;
    }

    public static void setSystem(System system) {
        SystemUtils.system = system;
    }
}
