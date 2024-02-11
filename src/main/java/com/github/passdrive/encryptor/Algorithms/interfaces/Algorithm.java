package com.github.passdrive.encryptor.Algorithms.interfaces;

/**
 * The encryption algorithm has to follow
 * common interface
 * 
 * Constructor
 * Encrypt
 * Decrypt
 */

public abstract class Algorithm {
    // Overridable
    abstract public String encrypt(String message);

    abstract public String decrypt(String encryptedMessage);
}
