package br.com.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class GenerateKey {
    private static final int AES_KEY_BIT = 256;

    public static String generateKeyBase64() throws NoSuchAlgorithmException {
        SecretKey secretKey = getAESKey(AES_KEY_BIT);
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static SecretKey toSecretKey(String keyBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    private static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keysize, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }
}
