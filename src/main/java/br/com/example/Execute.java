package br.com.example;

import static br.com.example.GenerateKey.generateKeyBase64;

public class Execute {

    public static void main(String[] args) {
        String keyTextBase64 = generateKeyBase64();
        System.out.println("\n------ KEY ------");
        System.out.println(keyTextBase64);

        String pText = "secret plain text";
        System.out.println("\n------ PTEXT ------");
        System.out.println(pText);

        String cText = EncryptDirector.init(keyTextBase64).execute(pText);
        System.out.println("\n------ CTEXT ------");
        System.out.println(cText);

        String dText = DecryptDirector.init(keyTextBase64).execute(cText);
        System.out.println("\n------ DTEXT ------");
        System.out.println(dText);

    }
}
