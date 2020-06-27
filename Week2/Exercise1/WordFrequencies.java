
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique()
    {
        myWords.clear();
        myFreqs.clear();
        FileResource input = new FileResource();
        for(String s : input.words())
        {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            
            if(index == -1)
            {
                myWords.add(s);
                myFreqs.add(1);
            }
            else
            {
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    public void tester()
    {
        findUnique();
        System.out.println("Unique Words : "+myWords.size());
        //for(int k=0; k<myWords.size(); k++)
        //{
        //    System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
        //}
        //int max = findIndexOfMax();
        //System.out.println("The word that occurs most often and its count are : " 
           // +myWords.get(max) + " "+ myFreqs.get(max));
    }
    public int findIndexOfMax()
    {
        int maxIndex=0;
        for(int k=0; k<myFreqs.size(); k++)
        {
            if(myFreqs.get(k) > myFreqs.get(maxIndex))
            {
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
