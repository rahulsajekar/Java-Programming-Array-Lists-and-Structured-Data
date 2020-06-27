
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {

    private ArrayList<String> charNames;
    private ArrayList<Integer> charCount;
    public CharactersInPlay()
    {
        charNames = new ArrayList<String>();
        charCount = new ArrayList<Integer>();
    }
    public void update(String person)
    {
        int index = charNames.indexOf(person);
        if(index == -1)
        {
            charNames.add(person);
            charCount.add(1);
        }
        else
        {
            int value = charCount.get(index);
            charCount.set(index,value+1);
        }
    }
    
    public void findAllCharacters()
    {
        FileResource input = new FileResource();
        for(String s : input.lines())
        {
            int index = s.indexOf(".");
            if(index != -1)
            {
                String person = s.substring(0,index);
                update(person);
            }
        }
    }
    
    public void tester()
    {
        //update("macbeth");
        findAllCharacters();
        maximumCharacter();
        characterWithNumParts(100,300);
        
    }
    
    public void characterWithNumParts(int num1, int num2)
    {
        if(num1>num2)
        {
            System.out.println("Invalid Number Inputs");
        }
        else
        {
            for(int k=0; k<charCount.size(); k++)
            {
                int value = charCount.get(k);
                if(value >= num1 && value <= num2)
                {
                    System.out.println(charNames.get(k)+" "+charCount.get(k));
                }
            }
        }
    }
    public void maximumCharacter()
    {
        int max=0;
        int index=0;
        for(int k=0; k<charCount.size(); k++)
        {
            if(charCount.get(k) > max)
            {
                max = charCount.get(k);
                index = k;
            }
        }
        System.out.println("max character : "+max + "name : "+charNames.get(index));
    }
    
}
