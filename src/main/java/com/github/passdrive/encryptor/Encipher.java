package com.github.passdrive.encryptor;

import com.github.passdrive.encryptor.Algorithms.interfaces.Algorithm;

/**
 * Encrypter
 */

public class Encipher {
    private static Algorithm algorithm = null;

    public static Algorithm getAlgorithm(String alg) {
        if (alg.equals("AES")) {
            Encipher.algorithm = new com.github.passdrive.encryptor.Algorithms.AES();
        } else if (alg.equals("DES")) {
            // Encipher.algorithm = new com.github.passdrive.encryptor.Algorithms.DES();
        } else if (alg.equals("RSA")) {
            // Encipher.algorithm = new com.github.passdrive.encryptor.Algorithms.RSA();
        }
        return algorithm;
    }

    public static String encryptData(String message) {
        return algorithm.encrypt(message);
    }

    public static String decryptData(String encryptedMessage) {
        return algorithm.decrypt(encryptedMessage);
    }
}
