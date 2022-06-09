package br.com.example;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptAction implements Action{

    private SecretKey secretKey;
    private String encryptAlgo;
    private Integer tagLengthBit;
    private Integer ivLengthByte;

    public static EncryptAction config(SecretKey secretKey, String encryptAlgo, Integer tagLenghtBit, Integer ivLenghtByte) {
        return new EncryptAction(secretKey, encryptAlgo, tagLenghtBit, ivLenghtByte);
    }

    private EncryptAction(SecretKey secretKey, String encryptAlgo, Integer tagLengthBit, Integer ivLengthByte) {
        this.secretKey = secretKey;
        this.encryptAlgo = encryptAlgo;
        this.tagLengthBit = tagLengthBit;
        this.ivLengthByte = ivLengthByte;
    }

    @Override
    public String execute(String pText) {
        try {
            return encrypt(pText);
        } catch (InvalidAlgorithmParameterException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String encrypt(String pText) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        byte[] encryptedText = encryptWithPrefixIV(pText.getBytes(StandardCharsets.UTF_8), this.secretKey, generateIV());
        return Base64.getEncoder().encodeToString(encryptedText);
    }

    private byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        byte[] cipherText = encrypt(pText, secret, iv);
        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return cipherTextWithIv;
    }

    private byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(this.encryptAlgo);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(this.tagLengthBit, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;
    }

    private static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    private byte[] generateIV() {
        return getRandomNonce(this.ivLengthByte);
    }

}
