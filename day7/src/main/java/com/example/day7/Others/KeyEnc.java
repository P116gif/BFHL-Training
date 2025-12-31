package com.example.day7.Others;

import java.security.KeyPair;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class KeyEnc {
    
    public static void main(String[] args) throws Exception {
        
        //Generating AES Key
        SecretKey aeskey = AESUtil.generateKey();

        //RSA keyPair
        KeyPair rsaPair = RSAUtil.generateRSAKeyPair();
        
        //Encrypting AES key using RSA Public Key
        String encryptedAesKey = RSAUtil.encryptData(Base64.getEncoder().encodeToString(aeskey.getEncoded()), rsaPair.getPublic());

        
        //Encrypting Data using AES Key
        String data = "Hello";
        String encryptedData = AESUtil.encryptData(data, aeskey);

        //Decrypting AES Key using RSA Private Key
        String decryptedAesKey = RSAUtil.decryptData(encryptedAesKey, rsaPair.getPrivate());
        SecretKey originalAesKey = new SecretKeySpec(
                    Base64.getDecoder().decode(decryptedAesKey), 
                    "AES"); 

        String decryptedData = AESUtil.decryptData(encryptedData, originalAesKey);

        System.out.println("Original Data: " + data);
        System.out.println("Encrypted Data: " + encryptedData); 
        System.out.println("Decrypted Data: " + decryptedData);
    
    }
}
