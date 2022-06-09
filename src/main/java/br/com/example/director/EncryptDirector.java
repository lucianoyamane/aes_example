package br.com.example.director;

import br.com.example.builder.EncryptBuilder;

public class EncryptDirector extends Director {

    private EncryptDirector(String keyBase64) {
        super(EncryptBuilder.init(), keyBase64);
    }

    public static EncryptDirector init(String keyBase64) {
        return new EncryptDirector(keyBase64);
    }
}
