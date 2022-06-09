package br.com.example;

import javax.crypto.SecretKey;

public interface Builder  {

    Builder secretKey(SecretKey secretKey);

    Builder encryptAlgo(String encryptAlgo);

    Builder ivLengthByte(Integer ivLengthByte);

    Builder tagLengthBit(Integer tagLengthBit);

    Action build();
}
