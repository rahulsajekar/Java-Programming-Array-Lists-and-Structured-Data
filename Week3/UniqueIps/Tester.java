
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
    //count unique ip addresses
    public void testUniqueIp()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println("There are "+la.countUniqueIPs()+" Unique IP Address");
    }
    
    public void testPrintAllHigherThanNum()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIpVisits()
    {
       LogAnalyzer la = new LogAnalyzer();
       la.readFile("weblog1_log");
       ArrayList<String> ips = la.uniqueIpVisitsOnDay("Mar 24");
       for(String s: ips)
       {
           System.out.println(s);
       }
       System.out.println(ips.size());
       int count = la.countUniqueIpsInRange(300,399);
       System.out.println(count+" unique addresses with status code between 300 and 399");
    }
}
