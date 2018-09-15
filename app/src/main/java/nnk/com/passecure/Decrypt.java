package nnk.com.passecure;




import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;


public class Decrypt {
          static String messageAfterDecrypt;
    public String Dec_one(String pass, String upass)
    {
        String password = pass;
        String encryptedMsg = upass;
        try {
             messageAfterDecrypt = AESCrypt.decrypt(password, encryptedMsg);
        }catch (GeneralSecurityException e){
            //handle error - could be due to incorrect password or tampered encryptedMsg


        }

        return messageAfterDecrypt;
    }
}
