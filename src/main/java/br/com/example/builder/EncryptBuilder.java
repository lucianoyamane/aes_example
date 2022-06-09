package br.com.example.builder;

import br.com.example.action.EncryptAction;

import javax.crypto.SecretKey;

public class EncryptBuilder implements Builder {

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer tagLengthBit;
    private Integer ivLengthByte;

    public static EncryptBuilder init() {
        return new EncryptBuilder();
    }

    private EncryptBuilder() {
    }

    @Override
    public Builder secretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    @Override
    public Builder encryptAlgo(String encryptAlgo) {
        this.encryptAlgo = encryptAlgo;
        return this;
    }

    @Override
    public Builder tagLengthBit(Integer tagLengthBit) {
        this.tagLengthBit = tagLengthBit;
        return this;
    }

    @Override
    public Builder ivLengthByte(Integer ivLengthByte) {
        this.ivLengthByte = ivLengthByte;
        return this;
    }

    @Override
    public EncryptAction build() {
        return EncryptAction.config(this.secretKey, this.encryptAlgo, this.tagLengthBit, this.ivLengthByte);
    }




}
