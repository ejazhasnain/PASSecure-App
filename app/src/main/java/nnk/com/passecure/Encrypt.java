package nnk.com.passecure;


import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Encrypt {

    static String encryptedMsg;
    public static String one(String pass, String Upass) {

        String password = pass;
        String message = Upass;
        try {
            encryptedMsg = AESCrypt.encrypt(password, message);
        }catch (GeneralSecurityException e){
            //handle error
        }

        return encryptedMsg;
        
    }
}
