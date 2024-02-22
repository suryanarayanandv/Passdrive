package com.github.passdrive.protector.configProtection;

import com.github.passdrive.Environment.EnvironmentImpl;

public class setRootPath {
    public static void setRoot(String rootPath) {
        EnvironmentImpl.setEnvironmentMap("root", rootPath);
    }
}
