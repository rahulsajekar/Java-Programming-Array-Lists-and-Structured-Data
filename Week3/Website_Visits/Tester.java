
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testCountVisitsPerIp()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> count  = la.countVisitsPerIp();
        for(String s : count.keySet())
        {
            System.out.println(s + "\t" + count.get(s));
        }
        
    }
    
    public void testMostNumberVisitsByIp()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> count  = la.countVisitsPerIp();
        int max = la.mostNumberVisitsByIp(count);
        System.out.println("Most Number of visits by ip is : "+max);
    }
    
    public void testIPsMostVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> count  = la.countVisitsPerIp();
        ArrayList<String> maxi = la.IPsMostVisits(count);
        for(String s : maxi)
        {
            System.out.println(s);      
        }
    }
    
    public void testIPsForDays()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> ipsOfDay=la.IPsForDays();
        for(String s : ipsOfDay.keySet())
        {
            ArrayList<String> currentList = ipsOfDay.get(s);
            System.out.println(s+"\t");
            for(String w : currentList)
            {
                System.out.print(w+"\t");
            }
            System.out.println("\n");
        }
    }
    
    public void testDayWithMostIpVisits()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsOfDay=la.IPsForDays();
        String mostVisitedDay = la.dayWithMostIpVisits(ipsOfDay);
        System.out.println("Day With Most Visits "+mostVisitedDay);
    }
    
    public void testIPsWithMostVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> ipsOfDay=la.IPsForDays();
        ArrayList<String> ipAddrs = la.iPsWithMostVisitsOnDay(ipsOfDay,"Sep30");
        for(String s : ipAddrs)
        {
            System.out.println(s);
        }
    }
}
