
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {

    public void countWordLengths(FileResource resource, int[] count)
    {
        for(String w : resource.words())
        {
            int currLength = 0;
            if(Character.isLetter(w.charAt(0)))
            {
                if(Character.isLetter(w.charAt(w.length()-1)))
                {
                    currLength = w.length();
                }
                else{
                    currLength = w.length()-1;
                }
            }
            else
            {
                if(Character.isLetter(w.charAt(w.length()-1)))
                {
                    currLength = w.length()-1;
                }
                else{
                    currLength = w.length()-2;
                }
            }
            
            if(currLength >= 30)
            {
                count[30]++;
            }
            else
            {
                count[currLength]++;
            }
        }
    }
    
    public void testCountWordLengths()
    {
        FileResource resource = new FileResource();
        int[] count = new int[31];
        countWordLengths(resource,count);
        for(int i=0; i<31; i++)
        {
            if(count[i] != 0)
            {
                System.out.println(count[i]+" words of length "+i);
            }
        }
    }
    
}
