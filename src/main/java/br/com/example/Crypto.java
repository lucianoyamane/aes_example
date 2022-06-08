package br.com.example;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Crypto {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;

    private SecretKey secretKey;

    private Crypto(String keyBase64) {
        this.setSecretKey(keyBase64);
    }

    public static Crypto init(String keyBase64) {
        return new Crypto(keyBase64);
    }

    private void setSecretKey(String keyBase64) {
        byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    private SecretKey getSecretKey() {
        return this.secretKey;
    }

    public String decrypt(String cText){
        return DecryptAction.config(this.getSecretKey(), ENCRYPT_ALGO, TAG_LENGTH_BIT, IV_LENGTH_BYTE).execute(cText);
    }

    public String encrypt(String pText){
        return EncryptAction.config(this.getSecretKey(),ENCRYPT_ALGO, TAG_LENGTH_BIT, IV_LENGTH_BYTE).execute(pText);
    }

}
