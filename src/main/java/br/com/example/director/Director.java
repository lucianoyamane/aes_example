package br.com.example.director;

import br.com.example.tool.GenerateKey;
import br.com.example.action.Action;
import br.com.example.builder.Builder;
import br.com.example.properties.ValueProperties;

import javax.crypto.SecretKey;

public abstract class Director {

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
                .encryptAlgo(ValueProperties.instance().encryptAlgo())
                .tagLengthBit(ValueProperties.instance().tagLengthBit())
                .ivLengthByte(ValueProperties.instance().ivLengthByte())
                .build();
        return action.execute(text);
    }

}
