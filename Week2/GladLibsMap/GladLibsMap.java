
/**
 * Write a description of GladLibsMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibsMap 
{
    //create hashmap for all the categories
    private HashMap<String,ArrayList<String>> myMap;
    //Create ArrayList for each category
    /*private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    */
    // keep track of used words
    private ArrayList<String> wordUsed;
    //Declare random variable to select random item from the category
    private Random myRandom;
    //keep track of the category used
    private ArrayList<String> categoryUsed;
    
    //Set data sources
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibsMap(){
        // Initializa all the categories 
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        // Select random 
        myRandom = new Random();
    }
    
    public GladLibsMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels={"adjective","noun","color","country","name","animal",
            "timeframe","verb","fruit"};
        for(String s :labels)
        {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
        /*adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");   
        verbList = readIt(source+ "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
        */
    }
    
    private String randomFrom(ArrayList<String> source){
        //select random value from called category
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        /*if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }*/
        if(categoryUsed.indexOf(label) == -1){categoryUsed.add(label);}
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        /*if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }*/
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        //change the label with value and return the story
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //Check the current substitute is used or not
        while(wordUsed.contains(sub))
        {
            sub = getSubstitute(w.substring(first+1,last));//get the new substitution.
        }
        wordUsed.add(sub);//add the substitution to word used array list.
        return prefix+"\""+sub+"\""+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        wordUsed = new ArrayList<String>();
        categoryUsed = new ArrayList<String>();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n"+"Number of word used: "+wordUsed.size());
        System.out.println("\n Total Number of words availabel : "+totalWordsInMap());
        totalWordConsider();
    }
    
    public int totalWordsInMap()
    {
        int total=0;
        for(String key : myMap.keySet())
        {
            ArrayList<String> currList = myMap.get(key);
            total += currList.size();
        }
        return total;
    }
    //print the total number of words in used categories
    public void totalWordConsider()
    {
        int total =0;
        for(String s : categoryUsed)
        {
            if(myMap.keySet().contains(s))
            {
                total += myMap.get(s).size();
            }
        }
        System.out.println("Category Used : "+categoryUsed.size());
        System.out.println("Total number of words in that categories : "+total);
    }
}

