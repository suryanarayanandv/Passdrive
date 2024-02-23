package com.github.passdrive.protector.hashing.interfaces;

/**
 * Common interface for hashing
 */

abstract public class hash {
    public Boolean checkHash(String hash, String password) {
        return this.doHash(password).equals(hash);
    }

    // Overridable
    abstract public String doHash(String message);
}
