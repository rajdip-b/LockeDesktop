package com.app.locker.utils.classes;

import javax.crypto.Cipher;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Crypto {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Crypto(){
        try{
            InputStream publicKeyStream = getClass().getResourceAsStream("/keys/public.key");
            InputStream privateKeyStream = getClass().getResourceAsStream("/keys/private.key");
            byte[] publicKeyBytes =  publicKeyStream.readAllBytes();
            byte[] privateKeyBytes = privateKeyStream.readAllBytes();

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

            publicKey = keyFactory.generatePublic(publicKeySpec);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String encrypt(String data){
        try{
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedMessageBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedMessageBytes);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String data){
        try{
            Cipher decryptionCipher = Cipher.getInstance("RSA");
            decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decryptedMessageBytes = decryptionCipher.doFinal(Base64.getDecoder().decode(data));

            return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
