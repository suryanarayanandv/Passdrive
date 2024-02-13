package com.github.passdrive.encryptor.Algorithms;

/*
 * UNDER
 * JAVA CRYPTOGRAPHIC PROVIDER
 * FRAMEWORK
 */

import com.github.passdrive.encryptor.Algorithms.interfaces.Algorithm;
import com.github.passdrive.Environment.EnvironmentImpl;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES extends Algorithm {

    // Key Generation password based
    private static SecretKey getKeyFromPassword(String masterPassword, String salt) {

        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(masterPassword.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                    .getEncoded(), "AES");
            return secret;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Init Vector
    private static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    // Salt generation
    private static String getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        int saltLength = 16;

        byte[] salt = new byte[saltLength];
        secureRandom.nextBytes(salt);

        return salt.toString();
    }

    // Storing secret / iv to Environment
    private static void init() {
        // Persistant Env
        EnvironmentImpl env = new EnvironmentImpl();

        String masterPassword = (String) env.getEnvironmentMap("master");
        String salt = AES.getSalt();
        SecretKey secret = AES.getKeyFromPassword(masterPassword, salt);
        IvParameterSpec iv = AES.generateIv();

        if (env.getEnvironmentMap("secret-aes") == null)
            env.setEnvironmentMap("secret-aes", secret);

        // Adding secret to Environment
        if (env.getEnvironmentMap("iv-aes") == null)
            env.setEnvironmentMap("iv-aes", iv);
    }

    // Encryption helper function
    private static String encrypt(String algorithm, String input, SecretKey key,
            IvParameterSpec iv) {

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes());
            return Base64.getEncoder()
                    .encodeToString(cipherText);
        } catch (InvalidKeyException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            return null;
        }
    }

    // Decryption helper function
    private static String decrypt(String algorithm, String cipherText, SecretKey key,
            IvParameterSpec iv) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder()
                    .decode(cipherText));
            return new String(plainText);
        } catch (InvalidKeyException | IllegalBlockSizeException | InvalidAlgorithmParameterException
                | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            return null;
        }
    }

    @Override
    public String encrypt(String message) {
        init();
        EnvironmentImpl env = new EnvironmentImpl();
        SecretKey secret = (SecretKey) env.getEnvironmentMap("secret-aes");
        IvParameterSpec iv = (IvParameterSpec) env.getEnvironmentMap("iv-aes");

        return AES.encrypt("AES/CBC/PKCS5Padding", message, secret, iv);
    }

    @Override
    public String decrypt(String encryptedMessage) {
        EnvironmentImpl env = new EnvironmentImpl();
        SecretKey secret = (SecretKey) env.getEnvironmentMap("secret");
        IvParameterSpec iv = (IvParameterSpec) env.getEnvironmentMap("iv");

        return AES.decrypt("AES/CBC/PKCS5Padding", encryptedMessage, secret, iv);
    }

}
