package br.com.example.director;

import br.com.example.GenerateKey;
import br.com.example.action.Action;
import br.com.example.builder.Builder;

import javax.crypto.SecretKey;

public abstract class Director {

    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;

    private SecretKey secretKey;
    private Builder builder;

    protected Director(Builder builder, String keyBase64) {
        this.builder = builder;
        this.setSecretKey(keyBase64);
    }
    private void setSecretKey(String keyBase64) {
        this.secretKey = GenerateKey.toSecretKey(keyBase64);
    }

    private SecretKey getSecretKey() {
        return this.secretKey;
    }

    public Builder getBuilder() {
        return builder;
    }

    public String execute(String text) {
        Action action = this.getBuilder()
                .secretKey(this.getSecretKey())
                .encryptAlgo(ENCRYPT_ALGO)
                .tagLengthBit(TAG_LENGTH_BIT)
                .ivLengthByte(IV_LENGTH_BYTE)
                .build();
        return action.execute(text);
    }

}
