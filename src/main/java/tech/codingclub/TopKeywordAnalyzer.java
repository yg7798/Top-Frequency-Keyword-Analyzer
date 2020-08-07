package tech.codingclub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tech.codingclub.utility.FileUtility;
import tech.codingclub.utility.TaskManager;

import java.util.*;

public class TopKeywordAnalyzer implements Runnable {
    public  String path;
     public TopKeywordAnalyzer(String path)
     {
         this.path=path;
     }

    public TopKeywordAnalyzer() {

    }

    public void run() {
        ArrayList<String > a=new ArrayList<String>();
        a= FileUtility.inputPrintfile(path);
        HashMap<String ,Integer> mp=new HashMap<String, Integer>();
        for (String row:a)
        {
            String[] s=row.split(" ");
            for (String keyword:s)
            {
                String data=keyword.toLowerCase();
                if(!mp.containsKey(data))
                {
                    mp.put(data,1);
                }
                else
                {
                    Integer i=mp.get(data);
                    mp.put(data,i+1);
                }
            }
        }
      ArrayList<KeywordCount>ans=new ArrayList<KeywordCount>();
        for (String x:mp.keySet())
        {
            ans.add(new KeywordCount(x,mp.get(x)));
        }
        Collections.sort(ans, new Comparator<KeywordCount>() {
            @Override
            public int compare(KeywordCount o1, KeywordCount o2) {
                return o2.data-o1.data;
            }
        });
        /*for (KeywordCount obj:ans)
        {
            System.out.println(obj.str+"  "+obj.data);
        }*/
        String json=new Gson().toJson(ans);
        System.out.println(json);

        String convertJson="{\"str\":\"jaya\",\"data\":10}";
        KeywordCount keywordCount=new Gson().fromJson(convertJson,KeywordCount.class);
        System.out.println("convert to object "+keywordCount.str+" "+keywordCount.data);

        String convertArray="[{\"str\":\"jaya\",\"data\":10},{\"str\":\"he\",\"data\":6}]";
        ArrayList<KeywordCount> arr=new Gson().fromJson(convertArray,new TypeToken<ArrayList<KeywordCount>>(){}.getType());
        System.out.println("converted array from json");
        for (KeywordCount temp:arr)
        {
            System.out.println(temp.str+"  "+temp.data);
        }
    }

    public static void main(String[] args) {
        System.out.println("This is Yash Garg. Hello World"+new Date().toString()+"sharp");
        TaskManager taskManager=new TaskManager(1);
        taskManager.waitTillQueueIsFreeAndAddTask(new TopKeywordAnalyzer("C:\\Users\\dream\\IdeaProjects\\tech.codingclub\\data\\practice\\file\\IndianNationalAnthem.txt"));
    }

}
