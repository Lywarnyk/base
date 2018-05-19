package security;

import exception.MainException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing password
 *
 * @author D. Kuliha
 */
public class Password {

    public static String hash(String password){
        StringBuilder hash = new StringBuilder();
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new MainException("Wrong algorithm",e);
        }
        byte[] bytes = sha1.digest(password.getBytes());
        for(byte b : bytes){
            hash.append(String.format("%02X", b));
        }
        return hash.toString();
    }
}
