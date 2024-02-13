package com.github.passdrive.Environment;

import java.util.HashMap;

// TODO: IMPL
// must have "master": "master password"
// must have "platform": "win32" / "linux" / "darwin"
// must implement singleton approach
// return null if not present

public class EnvironmentImpl implements Environment {
    private static HashMap<String, Object> env = null;

    @SuppressWarnings("rawtypes")
    public HashMap getInstance() {
        if (env == null) {
            env = new HashMap<String, Object>();
        }
        return env;
    }

    public EnvironmentImpl() {
        getInstance();
    }

    /**
     * @brief Inmemory master password for utility
     */
    public void initMasterPassword(String masterPassword) {
        if (getInstance() != null) {
            env.put("master", masterPassword);
        }
    }

    /*
     * @brief Default platform for utility
     */
    public void initPlatform(String platform) {
        if (getInstance() != null) {
            env.put("platform", platform);
        }
    }

    public void setMultipleEnvironmentMap(HashMap<String, Object> map) {
        if (getInstance() != null) {
            env.putAll(map);
        }
    }

    public void clearEnvironmentMap() {
        if (getInstance() != null) {
            env.clear();
        }
    }

    @Override
    public Object getEnvironmentMap(String key) {
        return getInstance() != null ? env.get(key) : null;
    }

    @Override
    public void setEnvironmentMap(String key, Object val) {
        Object temp = getInstance() != null ? env.put(key, val) : null;
    }

}
