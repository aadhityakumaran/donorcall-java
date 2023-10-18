package org.back;

import java.nio.charset.StandardCharsets;
import java.security.*;
public class Utils {
    public static byte[] hashed(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
