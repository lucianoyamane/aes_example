package br.com.example.tool;

import br.com.example.properties.ValueProperties;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class GenerateKey {

    public static String generateKeyBase64() {
        SecretKey secretKey = null;
        try {
            secretKey = getAESKey(ValueProperties.instance().aesKeyBit());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static SecretKey toSecretKey(String keyBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ValueProperties.instance().algorithm());
    }

    private static SecretKey getAESKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ValueProperties.instance().algorithm());
        keyGen.init(keySize, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }
}
