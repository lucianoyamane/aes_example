package br.com.example.director;

import br.com.example.builder.DecryptBuilder;

public class DecryptDirector extends Director {

    private DecryptDirector(String keyBase64) {
        super(DecryptBuilder.init(), keyBase64);
    }

    public static DecryptDirector init(String keyBase64) {
        return new DecryptDirector(keyBase64);
    }
}
