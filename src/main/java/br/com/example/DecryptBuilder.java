package br.com.example;

import javax.crypto.SecretKey;

public class DecryptBuilder {

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer ivLenghtByte;
    private Integer tagLenghtBit;

    private DecryptBuilder() {
    }

    public static DecryptBuilder init() {
        return new DecryptBuilder();
    }

    public DecryptBuilder secretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public DecryptBuilder encryptAlgo(String encryptAlgo) {
        this.encryptAlgo = encryptAlgo;
        return this;
    }

    public DecryptBuilder ivLenghtByte(Integer ivLenghtByte) {
        this.ivLenghtByte = ivLenghtByte;
        return this;
    }

    public DecryptBuilder tagLenghtBit(Integer tagLenghtBit) {
        this.tagLenghtBit = tagLenghtBit;
        return this;
    }

    public DecryptAction build() {
        return DecryptAction.config(this.secretKey, this.encryptAlgo, this.tagLenghtBit, this.ivLenghtByte);
    }
}
