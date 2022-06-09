package br.com.example;

import javax.crypto.SecretKey;

public class EncryptBuilder {

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer tagLenghtBit;
    private Integer ivLenghtByte;

    public static EncryptBuilder init() {
        return new EncryptBuilder();
    }

    private EncryptBuilder() {
    }

    public EncryptBuilder secretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public EncryptBuilder encryptAlgo(String encryptAlgo) {
        this.encryptAlgo = encryptAlgo;
        return this;
    }

    public EncryptBuilder tagLenghtBit(Integer tagLenghtBit) {
        this.tagLenghtBit = tagLenghtBit;
        return this;
    }

    public EncryptBuilder ivLenghtByte(Integer ivLenghtByte) {
        this.ivLenghtByte = ivLenghtByte;
        return this;
    }

    public EncryptAction build() {
        return EncryptAction.config(this.secretKey, this.encryptAlgo, this.tagLenghtBit, this.ivLenghtByte);
    }




}
