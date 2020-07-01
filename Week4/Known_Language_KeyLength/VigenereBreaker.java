import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    String encrypted;
    //constructor to select file for decrypt
    public VigenereBreaker()
    {
        FileResource fr = new FileResource();
        encrypted = fr.asString();
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for(int i=whichSlice; i<message.length(); i+=totalSlices)
        {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker();
        for(int i=0; i<klength; i++)
        {
            String currSlice = sliceString(encrypted,i,klength);
            int currKey = cc.getKey(currSlice);
            key[i] = currKey;
        }
        
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        
        int[] key = tryKeyLength(encrypted,4,'e');
        VigenereCipher vc = new VigenereCipher(key);
        String decryptedString = vc.decrypt(encrypted);
        System.out.print(decryptedString);
        for(int i=0;i<key.length;i++)
        {System.out.println(key[i]);}
    }
    
}
