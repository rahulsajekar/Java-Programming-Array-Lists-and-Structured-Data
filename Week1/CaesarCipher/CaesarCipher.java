
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String ShiftedAlphabet = Alphabet.substring(key)+Alphabet.substring(0,key);
        String shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        for(int i=0; i<encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if(Character.isLowerCase(currChar) && (Character.isLetter(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                char en = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i,en);
            }
            else if(Character.isLetter(currChar))
            {
                int idx = Alphabet.indexOf(currChar);
                char en = ShiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i,en);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesarCipher()
    {
        //encryption with 1 key
        System.out.println(encrypt("First Legion",17));
        //encryption with 2 key
        System.out.println(encryptTwoKey("First Legion",23,17));
    }
    
    public String encryptTwoKey(String input, int key1, int key2)
    {
        StringBuilder sb = new StringBuilder(input);
        for(int i=0; i<sb.length(); i++)
        {
            char currChar = sb.charAt(i);
            if(i%2 == 0)
            {
                //encrypt with key1
                String curr = encrypt(Character.toString(currChar),key1);
                sb.setCharAt(i,curr.charAt(0));
            }
            else
            {
                //encrypt with key2
                String curr = encrypt(Character.toString(currChar),key2);
                sb.setCharAt(i,curr.charAt(0));
            }
        }
        return  sb.toString();
    }
}
