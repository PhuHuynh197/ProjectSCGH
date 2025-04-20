/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tuan3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author THIS PC
 */
public class AESCipher {
    
    private static final String ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "encryptionKey ";
    
    public static String encrypt (String plaintext, String secretKey) throws NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException{
        
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }  
    
    public static String decrypt (String ciphertext, String secretKey) throws NoSuchAlgorithmException,
            InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException{
        
        SecretKey key = generateKey(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  
        return new String(decryptedBytes);
    }
    
    private static SecretKey generateKey(String secretKey)
        throws NoSuchAlgorithmException{
        byte[] KeyBytes = secretKey.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(KeyBytes, ALGORITHM);
        return keySpec;
    }
    
    public static String generateRegistrationKey(String username, String password){
        String registrationKey = username + ":" + password + ":" + ENCRYPTION_KEY;
        return registrationKey;
    }
    
    public static String saveRegistrationeKeyToFile(String registrationKey, String filename)
            throws IOException{
        try(FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(registrationKey);
        }
        return null;
    }
    
    public static String readRegistrationeKeyToFile(String filename)
            throws IOException, ClassNotFoundException{
        try(FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis)){
            return (String) ois.readObject();
        }
    }    
}
