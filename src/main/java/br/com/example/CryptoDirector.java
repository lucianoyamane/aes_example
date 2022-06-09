package br.com.example;

import javax.crypto.SecretKey;

public class CryptoDirector {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;

    private SecretKey secretKey;

    private CryptoDirector(String keyBase64) {
        this.setSecretKey(keyBase64);
    }

    public static CryptoDirector init(String keyBase64) {
        return new CryptoDirector(keyBase64);
    }

    private void setSecretKey(String keyBase64) {
        this.secretKey = GenerateKey.toSecretKey(keyBase64);
    }

    private SecretKey getSecretKey() {
        return this.secretKey;
    }

    public String decrypt(String cText){
        Action decryptAction = buildAction(DecryptBuilder.init());
        return decryptAction.execute(cText);
    }

    public String encrypt(String pText){
        Action encryptAction = buildAction(EncryptBuilder.init());
        return encryptAction.execute(pText);
    }

    private Action buildAction(Builder builder) {
        return builder
                .secretKey(this.getSecretKey())
                .encryptAlgo(ENCRYPT_ALGO)
                .tagLengthBit(TAG_LENGTH_BIT)
                .ivLengthByte(IV_LENGTH_BYTE)
                .build();
    }

}
