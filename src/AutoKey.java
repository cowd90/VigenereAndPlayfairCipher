
import java.lang.*;
import java.util.Scanner;

public class AutoKey {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Scanner in = new Scanner(System.in);;

    public static void main(String[] args)
    {
        System.out.print("Nhập văn bản: ");
        String msg = in.nextLine();
        msg = msg.toUpperCase();

        //key input
        System.out.print("Nhập khóa: ");
        String key = in.next();
        in.nextLine();
        key = key.toUpperCase();

        if (key.matches("[-+]?\\d*\\.?\\d+"))
            key = "" + alphabet.charAt(Integer.parseInt(key));
        String enc = autoEncryption(msg, key);

        System.out.println("Plaintext : " + msg);
        System.out.println("Encrypted : " + enc);
        System.out.println("Decrypted : " + autoDecryption(enc, key));
    }

    public static String autoEncryption(String msg, String key)
    {
        int len = msg.length();

        // generating the keystream
        String newKey = key.concat(msg);
        newKey = newKey.substring(0, newKey.length() - key.length());
        String encryptMsg = "";

        // applying encryption algorithm
        for (int x = 0; x < len; x++) {
            int first = alphabet.indexOf(msg.charAt(x));
            int second = alphabet.indexOf(newKey.charAt(x));
            int total = (first + second) % 26;
            encryptMsg += alphabet.charAt(total);
        }
        return encryptMsg;
    }

    public static String autoDecryption(String msg, String key)
    {
        String currentKey = key;
        String decryptMsg = "";

        // applying decryption algorithm
        for (int x = 0; x < msg.length(); x++) {
            int get1 = alphabet.indexOf(msg.charAt(x));
            int get2 = alphabet.indexOf(currentKey.charAt(x));
            int total = (get1 - get2) % 26;
            total = (total < 0) ? total + 26 : total;
            decryptMsg += alphabet.charAt(total);
            currentKey += alphabet.charAt(total);
        }
        return decryptMsg;
    }
}