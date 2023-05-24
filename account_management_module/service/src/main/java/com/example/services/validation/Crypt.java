package com.example.services.validation;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Crypt {

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator kGen = KeyGenerator.getInstance("AES");
        kGen.init(128);
        SecretKey key = kGen.generateKey();
        return key;
    }


    public static byte[] encrypt(String toBeEncrypted) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        byte[] bytes = cipher.doFinal(toBeEncrypted.getBytes());
        return bytes;
    }

    public static byte[] decrypt(byte[] bytes) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, generateKey());
        byte[] decryptedBytes = decryptCipher.doFinal(bytes);
        return decryptedBytes;
    }
}
