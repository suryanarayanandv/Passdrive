package com.github.passdrive.Environment;

/**
 * Environment Seperated by ';' 'key=value;..'
 * Or
 * Mapped Environment -> key: value
 */

 public abstract class Environment {
    abstract public Object getEnvironmentMap(String key);

    abstract public void setEnvironmentMap(String key, Object val);
}
