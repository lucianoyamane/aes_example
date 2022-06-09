package br.com.example.builder;

import br.com.example.action.DecryptAction;

import javax.crypto.SecretKey;

public class DecryptBuilder implements Builder {

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer ivLengthByte;
    private Integer tagLengthBit;

    private DecryptBuilder() {
    }

    public static DecryptBuilder init() {
        return new DecryptBuilder();
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
    public Builder ivLengthByte(Integer ivLengthByte) {
        this.ivLengthByte = ivLengthByte;
        return this;
    }

    @Override
    public Builder tagLengthBit(Integer tagLengthBit) {
        this.tagLengthBit = tagLengthBit;
        return this;
    }

    @Override
    public DecryptAction build() {
        return DecryptAction.config(this.secretKey, this.encryptAlgo, this.tagLengthBit, this.ivLengthByte);
    }
}
