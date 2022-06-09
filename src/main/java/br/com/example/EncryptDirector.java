package br.com.example;

public class EncryptDirector extends Director{

    private EncryptDirector(String keyBase64) {
        super(EncryptBuilder.init(), keyBase64);
    }

    public static EncryptDirector init(String keyBase64) {
        return new EncryptDirector(keyBase64);
    }
}
