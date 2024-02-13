package com.github.passdrive.protector.hashing.interfaces;

/**
 * Common interface for hashing
 */

abstract public class hash {
    public final Boolean checkHash(String hash, String message) {
        return this.doHash(message).equals(hash);
    }

    // Overridable
    abstract public String doHash(String message);
}
