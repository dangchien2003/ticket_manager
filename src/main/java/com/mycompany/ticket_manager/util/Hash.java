/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.util;

/**
 *
 * @author chien
 */
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Arrays;
public class Hash {

    public static String getHash(String string, byte[] salt) {
        try {
            if (salt == null) {
                salt = new byte[16];
                new SecureRandom().nextBytes(salt);
            }

            PBEKeySpec spec = new PBEKeySpec(string.toCharArray(), salt, 100000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            byte[] saltAndHash = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, saltAndHash, 0, salt.length);
            System.arraycopy(hash, 0, saltAndHash, salt.length, hash.length);

            return Base64.getEncoder().encodeToString(saltAndHash);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean verify(String hash, String string) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(hash);
            byte[] salt = new byte[16];
            byte[] storedHash = new byte[decodedData.length - 16];

            System.arraycopy(decodedData, 0, salt, 0, 16);
            System.arraycopy(decodedData, 16, storedHash, 0, storedHash.length);

            PBEKeySpec spec = new PBEKeySpec(string.toCharArray(), salt, 100000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] newHash = factory.generateSecret(spec).getEncoded();

            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(storedHash, "HmacSHA256");
            mac.init(keySpec);
            return Arrays.equals(storedHash, newHash);
        }catch (Exception e){
            System.out.println(e.getMessage()); 
            return false;
        }
    }
}
