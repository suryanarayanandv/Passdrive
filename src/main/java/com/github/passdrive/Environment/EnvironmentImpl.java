package com.github.passdrive.Environment;

import java.util.HashMap;

// must have "master": "master password"
// must have "platform": "win32" / "linux" / "darwin"
// must implement singleton approach
// return null if not present

public class EnvironmentImpl {
    private static HashMap<String, Object> env = null;

    /**
     * @brief Inmemory master password for utility
     */
    public void initMasterPassword(String masterPassword) {
        if (env == null) {
            env = new HashMap<String, Object>();
        }
        env.put("master", masterPassword);
    }

    /*
     * @brief Default platform for utility
     */
    public static void initPlatform(String platform) {
        if (env == null) {
            env = new HashMap<String, Object>();
        }
        env.put("platform", platform);
    }

    public void clearEnvironmentMap() {
        env = null;
    }

    public static Object getEnvironmentMap(String key) {
        if (env != null) {
            return env.get(key);
        }
        return false;
    }

    public static void setEnvironmentMap(String key, Object val) {
        if ( env == null ) {
            env = new HashMap<String, Object>();
        }
        env.put(key, val);
    }

}
