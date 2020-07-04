
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

    public boolean isVowel(char ch)
    {
        char ch1 = Character.toLowerCase(ch);
        if(ch1=='a' || ch1=='e' || ch1=='i' || ch1=='o' || ch1=='u')
        {return true;}
        else{return false;}
    }
    public void testIsVowel()
    {
        System.out.println(isVowel('F'));
        System.out.println(replaceVowel("Hello World",'*'));
        System.out.println(emphasis("Mary Bella Abracadabra",'a'));
    }
    
    public String replaceVowel(String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        for(int  i=0; i<sb.length(); i++)
        {
            char currChar = sb.charAt(i);
            if(isVowel(currChar))
            {
                sb.setCharAt(i,ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasis(String phrase, char ch)
    {
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0; i<phrase.length(); i++)
        {
            char currChar = Character.toLowerCase(sb.charAt(i));
            if(currChar == (ch))
            {
                if((i%2) == 0)
                {
                    sb.setCharAt(i,'*');
                }
                else{sb.setCharAt(i,'+');}
            }
        }
        return sb.toString();
    }
}
