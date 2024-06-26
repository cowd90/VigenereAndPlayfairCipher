import java.util.Scanner;

public class VigenereCipher {

    private static Scanner in;
    private static String message;
    private static String mappedKey;

    public static void main(String[] args){
        System.out.println("Vigenere Cipher");
        in = new Scanner(System.in);
        System.out.print("1.Mã hóa\n2.Giải mã\nChọn(1,2): ");
        int choice = in.nextInt();
        in.nextLine();

        if(choice == 1){
            System.out.println("--- MÃ HÓA ---");
            msgAndKey();
            cipherEncryption(message, mappedKey);
        } else if(choice == 2){
            System.out.println("--- GIẢI MÃ ---");
            msgAndKey();
            cipherDecryption(message, mappedKey);

        } else {
            System.out.println("Vui lòng nhập số thích hợp");
        }
    }

    private static void cipherDecryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                decryptedText.append(" ");
            } else {
                decryptedText.append((char) (65 + itrCount((int) mappedKey.charAt(i), (int) message.charAt(i))));
            }
        }

        System.out.println("Văn bản đã giải mã: " + decryptedText);
    }

    private static int itrCount(int key, int msg) {
        // this function will return the count which it takes from key's letter to reach cipher letter
        // and then this count will be used to calculate decryption of encrypted letter in message.
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if(key+i > 90){
                //90 being ASCII of Z
                result.append((char) (key + (i - 26)));

            } else {
                result.append((char) (key + i));
            }
        }

        //counting from key's letter to cipher letter in vigenere table
        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == msg){
                break; // letter found
            } else {
                counter++;
            }
        }
        return counter;
    }

    private static void cipherEncryption(String message, String mappedKey) {
        int[][] table = createVigenereTable();
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == (char)32 && mappedKey.charAt(i) == (char)32){
                encryptedText.append(" ");
            } else {
                //accessing element at table[i][j] position to replace it with letter in message
                encryptedText.append((char) table[(int) message.charAt(i) - 65][(int) mappedKey.charAt(i) - 65]);
            }
        }

        System.out.println("Văn bản đã mã hóa: " + encryptedText);
    }

    private static int[][] createVigenereTable() {
        // creating 26x26 table containing alphabets
        int[][] tableArr = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                int temp;
                if((i+65)+j > 90){
                    temp = ((i+65)+j) -26;
                } else {
                    temp = (i+65)+j;
                }
                tableArr[i][j] = temp;
            }
        }

        //printing table to check if its correct
//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < 26; j++) {
//                System.out.print((char)tableArr[i][j] + " ");
//            }
//            System.out.println();
//        }

        return tableArr;
    }

    private static void msgAndKey() {
        System.out.println("*** Văn bản và Khóa phải là chữ cái ***");

        //message input
        System.out.print("Nhập văn bản: ");
        String msg = in.nextLine();
        msg = msg.toUpperCase();

        //key input
        System.out.print("Nhập khóa: ");
        String key = in.next();
        in.nextLine();
        key = key.toUpperCase();

        //mapping key to message
        StringBuilder keyMap = new StringBuilder();
        for (int i = 0, j = 0; i < msg.length(); i++) {
            if(msg.charAt(i) == (char)32){
                //ignoring space
                keyMap.append((char) 32);

            } else {
                //mapping letters of key with message
                if(j < key.length()){
                    keyMap.append(key.charAt(j));
                    j++;
                } else {
                    //restarting the key from beginning once its length is complete
                    // and its still not mapped to message
                    j = 0;
                    keyMap.append(key.charAt(j));
                    j++; //without incrementing here, key's first letter will be mapped twice

                }
            } //if-else

        } //for
        message = msg;
        mappedKey = keyMap.toString();

        System.out.println("Message: " + message);
        System.out.println("key: " + mappedKey);
    }

}