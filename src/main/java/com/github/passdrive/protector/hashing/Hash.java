package com.github.passdrive.protector.hashing;

import com.github.passdrive.protector.hashing.HashFunctions.SHA;
import com.github.passdrive.protector.hashing.interfaces.hash;

// Default SHA-256

public class Hash extends hash {
    private static hash Hasher = null;
    public static hash getAlgorithm(String algorithm) {
        if (algorithm.equals("SHA-256") && Hasher == null) {
            Hasher = new SHA();
            return Hasher;
        } else if (algorithm.equals("SHA-512") && Hasher == null) {
            // this.algorithm = new SHA512();
            return Hasher;
        } else if (algorithm.equals("MD5") && Hasher == null) {
            // this.algorithm = new MD5();
            return Hasher;
        }
        return Hasher;
    }

    @Override
    public String doHash(String message) {
        return Hasher.doHash(message);
    }
}
