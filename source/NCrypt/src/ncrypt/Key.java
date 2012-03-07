package ncrypt;

/**
 *
 * @author phoenix
 */
public class Key {
    /* return a valid 32 bit key suitable for AES,Serpent,Twofish encryption algorithm */
    public static String get32BitKey(String key) {
        int length = key.length();
        if (length < 32) {
            StringBuilder outKey = new StringBuilder(key.toString());
            int i = 0;
            while (outKey.length() < 32) {
                outKey.append(outKey.charAt(i));
                i++;
            }
            return outKey.toString();
        } else {
            return (key.substring(0, 32));
        }
    }
}
