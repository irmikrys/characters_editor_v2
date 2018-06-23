package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncoder {
    private static final String algorithmName = "SHA-256";

    public static String getEncodedPassword(String password) {
        String encodedPassword = "";

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithmName);
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] passwordHash = digest.digest(passwordBytes);

            encodedPassword = Base64.getEncoder().encodeToString(passwordHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

        return encodedPassword;
    }

}
