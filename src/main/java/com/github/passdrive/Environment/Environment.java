package com.github.passdrive.Environment;

import java.util.HashMap;

/**
 * Environment Seperated by ';' 'key=value;..'
 * Or
 * Mapped Environment -> key: value
 */

public interface Environment {
    @SuppressWarnings("rawtypes")
    HashMap getInstance();

    Object getEnvironmentMap(String key);

    void setEnvironmentMap(String key, Object val);
}
