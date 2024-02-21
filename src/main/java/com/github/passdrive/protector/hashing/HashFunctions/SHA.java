package com.github.passdrive.protector.hashing.HashFunctions;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.github.passdrive.protector.hashing.interfaces.hash;

/**
 * Default SHA-256
 */

public class SHA extends hash {

    public static byte[] getSHA(String input) {
        // Static getInstance method is called with hashing SHA
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    @Override
    public String doHash(String message) {
        String hash = null;
        hash = toHexString(getSHA(message));
        return hash;
    }
}
