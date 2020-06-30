
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     WebLogParser wlp = new WebLogParser();
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String s : fr.lines())
         {
             records.add(wlp.parseEntry(s));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public HashMap<String, Integer> countVisitsPerIp()
     {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records)
         {
             String ip = le.getIpAddress();
             if(! counts.containsKey(ip))
             {
                 counts.put(ip,1);
             }
             else
             {
                 counts.put(ip, counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIp(HashMap<String, Integer> count)
     {
         int max=0;;
         for(String s : count.keySet())
         {
             int current = count.get(s);
             if(current > max)
             {
                 max=current;
             }
         }
         return max;
     }
     
     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> count)
     {
         ArrayList<String> maxVisits = new ArrayList<String>();
         int maxi = mostNumberVisitsByIp(count);
         for(String s : count.keySet())
         {
             int curr = count.get(s);
             if(curr == maxi)
             {
                 maxVisits.add(s);
             }
         }
         return maxVisits;
     }
     
     public HashMap<String, ArrayList<String>> IPsForDays()
     {
         HashMap<String, ArrayList<String>> ipForDays = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records)
         {
            //Split date format to get month and date
            Date d = le.getAccessTime();
            String currDay = d.toString();
            String[] curr=currDay.split(" ");
            String currMonth = curr[1];
            String currDate = curr[2];
            
            //create key for hashmap
            String hmKey = currMonth+currDate;
            
            //get ip address
            String ip = le.getIpAddress();
            
            if(!ipForDays.containsKey(hmKey))
            {
                ArrayList<String> currIP = new ArrayList<String>();
                currIP.add(ip);
                ipForDays.put(hmKey,currIP);
            }
            else
            {
                ArrayList<String> exitIPList = ipForDays.get(hmKey);
                exitIPList.add(ip);
                ipForDays.put(hmKey,exitIPList);
            }
         }
         return ipForDays;
     }
     
     public String dayWithMostIpVisits(HashMap<String, ArrayList<String>> ipsOfDay)
     {
         String answer="";
         int max=0;
         for(String s : ipsOfDay.keySet())
         {
            int currSize = ipsOfDay.get(s).size();
            if(currSize > max)
            {
                max = currSize;
                answer = s;
            }
         }
         return answer;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsOfDay,String someday)
     {
         ArrayList<String> answer = new ArrayList<String>();
         for(String s:ipsOfDay.keySet())
         {
            if(s.equals(someday))
            {
                ArrayList<String> curr = ipsOfDay.get(s);
                HashMap<String,Integer> count = createMap(curr);
                answer=IPsMostVisits(count);
            }
         }
         return answer;
     }
     public HashMap<String,Integer> createMap(ArrayList<String> curr)
     {
         HashMap<String, Integer> answer = new HashMap<String, Integer>();
         for(String s : curr)
         {
             if(!answer.containsKey(s))
             {
                 answer.put(s,1);
             }
             else
             {
                 int currentValue = answer.get(s);
                 answer.put(s,currentValue+1);
             }
         }
         return answer;
     }
}
