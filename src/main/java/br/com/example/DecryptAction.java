package br.com.example;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DecryptAction implements Action{

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer ivLenghtByte;
    private Integer tagLenghtBit;

    public static DecryptAction config(SecretKey secretKey, String encryptAlgo, Integer tagLenghtBit, Integer ivLenghtByte) {
        return new DecryptAction(secretKey, encryptAlgo, tagLenghtBit, ivLenghtByte);
    }

    public DecryptAction(SecretKey secretKey, String encryptAlgo, Integer tagLenghtBit, Integer ivLenghtByte) {
        this.secretKey = secretKey;
        this.encryptAlgo = encryptAlgo;
        this.tagLenghtBit = tagLenghtBit;
        this.ivLenghtByte = ivLenghtByte;
    }

    @Override
    public String execute(String cText) {
        try {
            return decrypt(cText);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String decrypt(String cText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        byte[] encryptedTextBase64 = Base64.getDecoder().decode(cText);
        return decryptWithPrefixIV(encryptedTextBase64,this.secretKey);
    }

    private String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        ByteBuffer bb = ByteBuffer.wrap(cText);

        byte[] iv = new byte[this.ivLenghtByte];
        bb.get(iv);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;
    }

    private String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(this.encryptAlgo);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(this.tagLenghtBit, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, StandardCharsets.UTF_8);
    }
}
