
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {

    public void testCeaserCipher()
    {
        CaesarCipher cc  = new CaesarCipher(7);
        char en = cc.encryptLetter('C');
        System.out.println(en);
        char de = cc.decryptLetter('J');
        System.out.println(de);
        
        CaesarCipher cc1 = new CaesarCipher(7);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String en1 = cc1.encrypt(input);
        System.out.println(input + "\n"+ en1);
        String de1 = cc1.decrypt(en1);
        System.out.println("\n"+de1);
    }
    
    public void testCaesarCracker()
    {
        /*CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = cc.decrypt(encrypted);
        System.out.println(encrypted + "\n" + decrypted);*/
        
        CaesarCracker cc1 = new CaesarCracker('a');
        FileResource fr1 = new FileResource();
        String encrypted1 = fr1.asString();
        String decrypted1 = cc1.decrypt(encrypted1);
        System.out.println(encrypted1 + "\n" + decrypted1);
    }
    
    public void testVigenereCipher()
    {
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String input = fr.asString();
        String encrypted = vc.encrypt(input);
        System.out.println(encrypted);
    }
    
    public void testVigenereBreaker()
    {
        VigenereBreaker vb = new VigenereBreaker();
        String slice = vb.sliceString("abcdefghijklm", 4, 5);
        System.out.println(slice);
    }
    
}
