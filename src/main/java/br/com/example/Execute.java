package br.com.example;

import java.security.NoSuchAlgorithmException;

import static br.com.example.GenerateKey.generateKeyBase64;

public class Execute {

    public static void main(String[] args) {
        String keyTextBase64 = null;
        try {
            keyTextBase64 = generateKeyBase64();
            System.out.println("\n------ KEY ------");
            System.out.println(keyTextBase64);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Crypto aesgcmCrypto = Crypto.init(keyTextBase64);

        String pText = "secret plain text";
        System.out.println("\n------ PTEXT ------");
        System.out.println(pText);

        String cText = aesgcmCrypto.encrypt(pText);
        System.out.println("\n------ CTEXT ------");
        System.out.println(cText);

        String dText = aesgcmCrypto.decrypt(cText);
        System.out.println("\n------ DTEXT ------");
        System.out.println(dText);

    }
}
