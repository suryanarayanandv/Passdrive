package com.github.passdrive.encryptor;

import com.github.passdrive.encryptor.Algorithms.interfaces.Algorithm;

/**
 * Encrypter
 */

public class Encipher {
    private Algorithm algorithm;

    public Encipher(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String encryptData(String message) {
        return algorithm.encrypt(message);
    }

    public String decryptData(String encryptedMessage) {
        return algorithm.decrypt(encryptedMessage);
    }
}
