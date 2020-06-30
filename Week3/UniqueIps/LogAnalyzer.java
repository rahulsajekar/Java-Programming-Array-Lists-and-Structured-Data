
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
     
     public int countUniqueIPs()
     {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry record : records)
         {
             String ipAddr = record.getIpAddress();
             if(!uniqueIPs.contains(ipAddr))
             {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
    }
     
     public void printAllHigherThanNum(int num)
    {
        ArrayList<LogEntry> hn = new ArrayList<LogEntry>();
        for(LogEntry log : records)
        {
            int status_code = log.getStatusCode();
            if(status_code > num)
            {
                hn.add(log);
            }
        }
        //print the list
        for(LogEntry s : hn)
        {
            System.out.println(s);
        }
    }
    
    public ArrayList<String> uniqueIpVisitsOnDay(String someday)
    {
        String[] day = someday.split(" ");
        String month = day[0];
        String tarikh = day[1];
        ArrayList<String> uniqueIpVisits = new ArrayList<String>();
        for(LogEntry log : records)
        {
            Date d = log.getAccessTime();
            String currDay = d.toString();
            String[] curr=currDay.split(" ");
            String currMonth = curr[1];
            String currTarikh = curr[2];
            if(currMonth.equals(month) && currTarikh.equals(tarikh))
            {
                String ipAddr = log.getIpAddress();
                if(!uniqueIpVisits.contains(ipAddr))
                {
                    uniqueIpVisits.add(ipAddr);
                }
            }
        }
        return uniqueIpVisits;
    }
    
    public int countUniqueIpsInRange(int low, int high)
    {
        ArrayList<LogEntry> count = new ArrayList<LogEntry>();
        ArrayList<String> count1 = new ArrayList<String>();
        for(LogEntry log : records)
        {
            int status_code = log.getStatusCode();
            String ipAddr = log.getIpAddress();
            if(status_code >=low && status_code<=high && !count1.contains(ipAddr))
            {
                count.add(log);
                count1.add(ipAddr);
            }
        }
        return count.size();
    }
}
