import java.util.*;
import edu.duke.*;
import java.io.*;
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
        DirectoryResource dict = new DirectoryResource();
        //int[] key = tryKeyLength(encrypted,5,'e');
        HashMap<String, HashSet<String>> lang = new HashMap<String, HashSet<String>>();
        for(File f : dict.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr);
            String language = f.getName();
            lang.put(language, dictionary);
        }
        breakForAllLangs(encrypted,lang);
        //String dec = breakForLanguage(encrypted,dictionary);
        //int dec = breakForLanguage(encrypted,dictionary);
        //System.out.println(dec);
        /*VigenereCipher vc = new VigenereCipher(key);
        String decryptedString = vc.decrypt(encrypted);
        System.out.print(decryptedString);
        for(int i=0;i<key.length;i++)
        {System.out.println(key[i]);}*/
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines())
        {
            String item = line.toLowerCase();
            if(!dictionary.contains(item))
            {
                dictionary.add(item);
            }
        }
        return dictionary;
    }
    //calculate how many real words are in the message
    public int countWords(String message, HashSet<String> dictionary)
    {
        String[] count = message.split("\\W");//split the message into array of words
        int realWords = 0;
        for(int i=0; i<count.length; i++)
        {
            String currWord = count[i].toLowerCase();
            if(dictionary.contains(currWord))
            {
                realWords++;
            }
        }
        return realWords;
    }
    
    /*public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        
        int max = 0;
        int keyLength = 0;
        for(int i=0; i<100; i++)
        {
            int[] currKey = tryKeyLength(encrypted,i,'e');
            VigenereCipher vc = new VigenereCipher(currKey);
            String currDecrypted = vc.decrypt(encrypted);
            int realWords = countWords(currDecrypted,dictionary);
            if(realWords > max)
            {
                max= realWords;
                keyLength=i;
            }
        }
        int[] key = tryKeyLength(encrypted,keyLength,'e');
        System.out.println(key.length);
        VigenereCipher vc1 = new VigenereCipher(key);
        String decrypted = vc1.decrypt(encrypted); 
        return decrypted;
    }*/
     public String breakForLanguage(String encrypted, HashSet<String> dict) 
     {
         int max = 0;
         int keyReturn[] = new int[100];
         int KeyLength = 0;
         String aMessage = new String();
         String largestDecryption = new String();
         String[] decrypted = new String[100];
         char mostCommonChar = mostCommonCharIn(dict);
         for(int klength =1; klength < 100 ; klength++) {
             
             keyReturn = tryKeyLength(encrypted,klength,mostCommonChar);
             VigenereCipher VCipher  = new VigenereCipher(keyReturn) ;
             aMessage = VCipher.decrypt(encrypted);
             //counts is a value returned, no use starting from 0
             int counts = countWords(aMessage, dict);
             if(counts > max)
             {
                 max = counts;
                 largestDecryption = aMessage;
                 KeyLength = klength;
             } 
             
         }
                 
        return largestDecryption;
        
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character,Integer> charCount = new HashMap<Character,Integer>();
        for(String word : dictionary)
        {
            StringBuilder sb = new StringBuilder(word);
            for(int i=0; i<sb.length(); i++)
            {
                char curr = sb.charAt(i);
                if(!charCount.containsKey(curr))
                {
                    charCount.put(curr,1);
                }
                else
                {
                    charCount.put(curr,charCount.get(curr)+1);
                }
            }
        }
        int max = 0;
        char mostCommonChar = ' ';
        for(Character c : charCount.keySet())
        {
            int curr = charCount.get(c);
            if(curr>max)
            {
                max = curr;
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages)
    {
        int globalMax = 0;
        String answer = "";
        String Language = "";
        for(String language : languages.keySet())
        {
            String currDecrypt = breakForLanguage(encrypted, languages.get(language));
            int currCount = countWords(currDecrypt, languages.get(language));
            if(currCount > globalMax)
            {
                globalMax = currCount;
                answer = currDecrypt;
                Language = language;
            }
        }
        System.out.println(answer + "\n" + "Decrypted with language : "+Language);
    }
 
}
