package ncrypt.crypto;

/**
 * encrypt and decrypt using three algorithms AES, Serpent, Twofish
 * @author phoenix
 */

import gnu.crypto.cipher.BaseCipher;
import gnu.crypto.cipher.Rijndael;
import gnu.crypto.cipher.Serpent;
import gnu.crypto.cipher.Twofish;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;


public class Crypto {
    public static String encrypt(String type,String value, String key) throws InvalidKeyException, UnsupportedEncodingException {
        BaseCipher crypto=null;
        if(type.equals("Twofish"))
            crypto=new Twofish();
        else if(type.equals("Serpent"))
            crypto=new Serpent();
        else if(type.equals("AES"))
            crypto=new Rijndael();

        byte[] plainText;
        byte[] encryptedText;

        // create a key
        byte[] keyBytes = key.getBytes();
        Object keyObject = crypto.makeKey(keyBytes, 16);
        
        //make the length of the text a multiple of the block size
        if ((value.length() % 16) != 0) {
            while ((value.length() % 16) != 0) {
                value += " ";
            }
        }
        
        // initialize byte arrays for plain/encrypted text
        plainText = value.getBytes("UTF8");
        encryptedText = new byte[value.length()];
        
        // encrypt text in 8-byte chunks
        for (int i = 0; i < Array.getLength(plainText); i += 16) {
            crypto.encrypt(plainText, i, encryptedText, i, keyObject, 16);
        }
        String encryptedString = Base64Coder.encodeLines(encryptedText);
        return encryptedString;
    }

    public static String decrypt(String type,String value, String key) throws InvalidKeyException, UnsupportedEncodingException {
        BaseCipher crypto=null;
        if(type.equals("Twofish"))
            crypto=new Twofish();
        else if(type.equals("Serpent"))
            crypto=new Serpent();
        else if(type.equals("AES"))
            crypto=new Rijndael();

        byte[] encryptedText;
        byte[] decryptedText;
        
        //create the key
        byte[] keyBytes = key.getBytes();
        Object keyObject = crypto.makeKey(keyBytes, 16);
        
        //make the length of the string a multiple of the block size
        if ((value.length() % 16) != 0) {
            while ((value.length() % 16) != 0) {
                value += " ";
            }
        }
        
        //initialize byte arrays that will hold encrypted/decrypted text
        encryptedText = Base64Coder.decodeLines(value);
        decryptedText = new byte[value.length()];
        
        //Iterate over the byte arrays by 16-byte blocks and decrypt.
        for (int i = 0; i < Array.getLength(encryptedText); i += 16) {
            crypto.decrypt(encryptedText, i, decryptedText, i, keyObject, 16);
        }
        String decryptedString = new String(decryptedText, "UTF8");
        return decryptedString;
    }
}
