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
        DecryptAction decryptAction = DecryptBuilder.init()
                                                    .secretKey(this.getSecretKey())
                                                    .encryptAlgo(ENCRYPT_ALGO)
                                                    .tagLenghtBit(TAG_LENGTH_BIT)
                                                    .ivLenghtByte(IV_LENGTH_BYTE).build();
        return decryptAction.execute(cText);
    }

    public String encrypt(String pText){
        EncryptAction encryptAction = EncryptBuilder.init()
                                                .secretKey(this.getSecretKey())
                                                .encryptAlgo(ENCRYPT_ALGO)
                                                .tagLenghtBit(TAG_LENGTH_BIT)
                                                .ivLenghtByte(IV_LENGTH_BYTE).build();
        return encryptAction.execute(pText);
    }

}
