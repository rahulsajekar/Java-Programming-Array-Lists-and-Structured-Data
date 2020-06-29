
/**
  * count how many times each codon occurs in a DNA Strand
 * @author (Rahul Sajekar) 
 * @version (6/6/2020)
 */
import java.util.*;
import edu.duke.*;
public class codonCount {
    //Create a hashMap to store codon and its occurrences
    private HashMap<String, Integer> codons;
    public codonCount()
    {
        //Initialize the hashmap
        codons = new HashMap<String, Integer>();
    }
    
    //Build a map of codons mapped to their counts from the string dna
    public void buildCodonMap(int start, String dna)
    {
        for(int i=start; i<dna.length(); i=i+3)
        {
            if(i  > dna.length()-3){break;}
            int end = i+3;
            String currCodon = dna.substring(i,end);
            if(codons.keySet().contains(currCodon))//codon is already present in hashmap
            {
                //update the codons count
                codons.put(currCodon, codons.get(currCodon)+1);
            }
            else
            {
                //put new codon into the hashmap
                codons.put(currCodon,1);
            }
        }
    }
    
    //returns a codon that has largest occurrences
    public String getMostCommonCodon()
    {
        int largestOccur = 0;
        String mostCommonCodon ="";
        for(String w: codons.keySet())
        {
            if(codons.get(w) > largestOccur)
            {
                largestOccur = codons.get(w);
                mostCommonCodon = w;
            }
        }
        return mostCommonCodon;
    }
    
    //print all the codons whose occurences are between start and end
    public void printCodonCounts(int start, int end)
    {
        for(String w : codons.keySet())
        {
            int occurences = codons.get(w);
            if((occurences >= start) && (occurences <= end))
            {
                System.out.println(w +"\t"+ occurences);
            }
        }
    }
    
    //tester method
    public void tester()
    {
        FileResource dnaFile = new FileResource();
        String dna ="";
        for(String s : dnaFile.lines())
        {
            dna += s;
        }
        dna=dna.trim();
        //Reading frame = 0
        System.out.println("Readinig Frame = 0");
        buildCodonMap(0,dna);
        System.out.println("Unique Codons are : "+codons.size());
        String commonCodon = getMostCommonCodon();
        System.out.println("Most Common codon is : "+commonCodon+" "+codons.get(commonCodon));
        System.out.println("Codons with count between 7 and 7 :");
        printCodonCounts(7,7);
    }
}
