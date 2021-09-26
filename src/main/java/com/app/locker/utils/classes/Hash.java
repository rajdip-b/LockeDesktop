package com.app.locker.utils.classes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    @SuppressWarnings("unused")
    public static String toSHA256(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA256");
        }catch (NoSuchAlgorithmException e) {
            System.exit(1);
        }
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hex = new StringBuilder(number.toString(16));
        while(hex.length() < 32)
            hex.insert(0, '0');
        return hex.toString();
    }

}