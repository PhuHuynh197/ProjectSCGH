/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tuan4;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.IESParameterSpec;

/**
 *
 * @author THIS PC
 */
public class ECCCipher {
    private static final String EC_ALGORITHM = "EC";
    private static final String ECC_CIPHER_ALGORITHM = "ECIES";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EC_ALGORITHM, "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }
    public byte[] encrypt(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ECC_CIPHER_ALGORITHM, "BC");
        
        byte[] nonce = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        
        IESParameterSpec params = new IESParameterSpec(null, null, 128, 128, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, params);
        
        return cipher.doFinal(plaintext.getBytes());
    }

    public String decrypt(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ECC_CIPHER_ALGORITHM, "BC");
        
        byte[] nonce = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        
        IESParameterSpec params = new IESParameterSpec(null, null, 128, 128, nonce);
        cipher.init(Cipher.DECRYPT_MODE, privateKey, params);
        
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }
    
    public static PublicKey loadPublicKey(byte[] keyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM, "BC");

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(publicKeySpec);
    }

    public static PrivateKey loadPrivateKey(byte[] keyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(EC_ALGORITHM, "BC");

        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(privateKeySpec);
    }
}
