package tech.codingclub;

import tech.codingclub.utility.FileUtility;

import java.lang.reflect.Array;
import java.util.*;

public class FileReaderRunnable implements Runnable{
    public int counter;
    public      String threadName;
    private int waitTime;
    public  String path;
    public FileReaderRunnable(String path)
    {
        this.path=path;
    }
    public FileReaderRunnable(String threadName, int counter, int waitTime)
    {
        this.threadName=threadName;
        this.counter=counter;
        this.waitTime=waitTime;
    }
    public void run() {
        Map<String,Integer> mp=new HashMap<String, Integer>();
          //     mp=FileUtility.inputPrintfile(path);
           int [] arr=new int[10];
           int j=0;
        for(String key:mp.keySet()) {
            arr[j++]=mp.get(key);
        }
        Arrays.sort(arr);
        for(  j = 0; j < arr.length/2; j++ )
        {
            int temp = arr[j];
            arr[j] = arr[arr.length - j - 1];
            arr[arr.length - j - 1] = temp;
        }
             int i=1;
             while (i<=9) {
                 for (String key : mp.keySet()) {
                     if(arr[i-1]==mp.get(key))
                     {System.out.println(i + " Line must be " + mp.get(key) + " " + key);i++;}
                 }
             }
        }

    public static void main(String[] args) {
        System.out.println("this side is yash garg");
        System.out.println("this time for running RunnableExample is :"+new Date().toString());
        FileReaderRunnable thread1 = new FileReaderRunnable("C:\\Users\\dream\\IdeaProjects\\tech.codingclub\\data\\practice\\file\\"+"IndianNationalAnthem.txt");
        Thread t1=new Thread(thread1);
        t1.start();
    }

}
