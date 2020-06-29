
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordsInFile {
    //create hashmap
    private HashMap<String,ArrayList<String>> wordMap;
    
    public WordsInFile()
    {
        //initialize hashmap
        wordMap = new HashMap<String,ArrayList<String>>();
    }
    
    // Add words of the file into the map
    private void addWordsFromFile(File f)
    {
        String fileName = f.getName();//get name of the file
        FileResource fr = new FileResource(f);
        for(String w : fr.words())
        {
            if(wordMap.keySet().contains(w))
            {
                ArrayList<String> currList = wordMap.get(w);
                currList.add(fileName);
                wordMap.put(w, currList);
            }
            else
            {
                ArrayList<String> newList = new ArrayList<String>();
                newList.add(fileName);
                wordMap.put(w, newList);
            }
        }
    }
    //build an hashmap for all the files selected
    public void buildWordFileMap()
    {
        DirectoryResource files = new DirectoryResource();
        for(File file : files.selectedFiles())
        {
            addWordsFromFile(file);
        }
    }
    // return the maximum number of files, the wword occurs
    public int maxNumber()
    {
        int max=0;
        for(String key : wordMap.keySet())
        {
            ArrayList<String> currList = wordMap.get(key);
            int size = currList.size();
            if(size > max)
            {
                max = size;
            }
        }
        return max;
    }
    //return an list of words that occur in exactly number of files
    public ArrayList<String> wordsInNumFile(int number)
    {
        ArrayList<String> word = new ArrayList<String>();
        for(String s : wordMap.keySet())
        {
            ArrayList<String> currList = wordMap.get(s);
            if(currList.size() == number && !word.contains(s))
            {
                word.add(s);
            }
        }
        return word;
    }
    //prints the name of the file this word occur in
    public void printFileIn(String word)
    {
        for(String key : wordMap.keySet())
        {
            if(key.equals(word))
            {
                ArrayList<String> wordInFile = wordMap.get(key);
                for(int i=0; i<wordInFile.size(); i++)
                {
                    System.out.println(wordInFile.get(i));
                }
            }
        }
    }
    //tester method
    public void tester()
    {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Maximum number of files any word appear is: "+max);
        ArrayList<String> count = wordsInNumFile(4);
        System.out.println("Words Appear in 4 files : "+ count.size());
        /*for(int i=0; i<count.size(); i++)
        {
            System.out.println(count.get(i));
        }*/
        System.out.println("The Word tree occurs in following files :");
        printFileIn("tree");
        //printmap();
    }
    //print entire map
    public void printmap()
    {
        for(String key : wordMap.keySet())
        {
            ArrayList<String> fileList = wordMap.get(key);
            System.out.print("\n"+key + "\t");
            for(int i=0; i<fileList.size(); i++)
            {
                System.out.print(fileList.get(i)+" ");
            }
        }
    }
}
