package com.github.passdrive.Environment;

import java.util.HashMap;

// TODO: IMPL
// must have "master": "master password"
// must implement singleton approach
// return null if not present

public class EnvironmentImpl implements Environment {
    private static HashMap<String, Object> env = null;

    @SuppressWarnings("rawtypes")
    public HashMap getInstance() {
        if ( env == null ) {
            env = new HashMap<String, Object>();
        }
        return env;
    }

    @Override
    public Object getEnvironmentMap(String key) {
        throw new UnsupportedOperationException("Unimplemented method 'getEnvironmentMap'");
    }

    @Override
    public void setEnvironmentMap(String key, Object val) {
        throw new UnsupportedOperationException("Unimplemented method 'setEnvironmentMap'");
    }
    
}
