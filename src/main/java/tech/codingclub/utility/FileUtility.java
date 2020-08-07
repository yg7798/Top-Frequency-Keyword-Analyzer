package tech.codingclub.utility;

import java.io.*;
import java.util.*;

public class FileUtility {
  public  static boolean createFile(String path)
  {
      File file =new File(path);
      boolean created=false;
      try {
          created=file.createNewFile();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return  created;
  }
   public static int countWords(String word, String path)
   {
       File file=new File(path);
       Scanner sc=null;
       int count = 0;
       try {

            sc = new Scanner(file);
           while (sc.hasNextLine()) {
               String nextToken = sc.next();
               if (nextToken.equalsIgnoreCase(word))
                   count++;
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }finally {
           if (sc!=null)
               sc.close();
       }
       return count;
   }

    public static ArrayList<String> inputPrintfile(String name) {
      ArrayList<String > a=new ArrayList<String>();
      File file=new File(name);
        Scanner sc= null;
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                String data=sc.nextLine();
                a.add(data);
               // System.out.println(ct+" Line must be "+count+" "+data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (sc!=null)
                sc.close();
        }
        return a;
    }
    public  static boolean writeFile(String path,String content)
    {
        try {
            FileWriter fw=new FileWriter(path);
            fw.append(content);
            //fw.append("\n");
            fw.close();
        }catch (Exception e)
        {
            return  false;
        }
        return  true;
    }
    public static boolean appendFile(String name,String content)
    {
        try {
            FileWriter fw=new FileWriter(name,true);
            fw.append("\n");
            fw.append(content);
            fw.close();
        }catch (Exception e)
        {
            return  false;
        }
        return  true;
    }

    public static void main(String[] args) {
       System.out.println("this side is yash garg:");
        System.out.println("file utility running at :"+new Date().toString());
        String name="C:\\Users\\dream\\IdeaProjects\\tech.codingclub\\data\\practice\\file\\" + "create.txt";
            boolean x=createFile(name);
            System.out.println("file created is :"+x);
           /* ArrayList<String> data=inputPrintfile(name);
            for (String str:data)
            {
                System.out.println(str);
            }
            System.out.println("no. of lines are :"+data.size());
            String namew="C:\\Users\\dream\\IdeaProjects\\tech.codingclub\\data\\practice\\file\\" + "file-create.txt";
           boolean ans =writeFile(namew,"hello content write by yash garg: ");
           System.out.println(ans);

           for (int i=1;i<=100;i++)
           {
               String txt=i+"";
               appendFile(namew,txt);
           }
           System.out.println("appended file length is: "+inputPrintfile(namew).size());*/
    }

}
