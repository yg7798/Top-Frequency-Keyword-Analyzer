package tech.codingclub.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.database.GenericDB;

import java.io.IOException;
import java.util.Date;

public class WikepediaDownloader implements Runnable{
 private    String keyword;

    public WikepediaDownloader(String keyword) {
        this.keyword = keyword;
    }

    public void run() {
        //1. get clean Keyword
        //2. get the url of wikipedia
        //3. make GET request to wikipedia
        //4. parsing the useful result using Jsoup
        //5. showing results
        if (this.keyword==null || this.keyword.length()==0)
            return;
        //step1
        this.keyword=this.keyword.trim().replaceAll("[ ]+","_");
        //step2
        String wikiUrl=getWikiUrl(keyword);
        String wikipediaResponse= null;
        String response="";
        String imageURL = null;
        try {
            //step3
            wikipediaResponse = HttpURLConnectionEx.sendget(wikiUrl);
           // System.out.println(wikipediaResponse);

            //step4
            Document document= Jsoup.parse(wikipediaResponse,"https://en.wikipedia.org/wiki/");
            Elements childElement=document.body().select(".mw-parser-output > *");
            int state=0;
            for (Element elements:childElement)
            {
                if (state==0)
                {
                    if (elements.tagName().equals("table"))
                        state=1;
                }
                else if(state==1)
                {
                    if (elements.tagName().equals("p"))
                    {
                        state=2;
                        response=elements.text();
                        break;
                    }
                }
            }
            try {
                imageURL=document.body().select(".infobox img").get(0).attr("src");
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageURL.startsWith("//"))
        {
            imageURL="https:"+imageURL;
        }
      WikiResult wikiResult=new WikiResult(this.keyword,response,imageURL);
        //new GenericDB<WikiResult>().addRow(tech.codingclub.tables.WikiResult.WIKIRESULT,wikiResult);
        //push result into database
        //for now just print the json
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(wikiResult);
        System.out.println(json);
        
    }

    private String getWikiUrl(String cleankeyword) {
        return "https://en.wikipedia.org/wiki/"+cleankeyword;
    }

    public static void main(String[] args) {
       TaskManager taskManager=new TaskManager(20);
       String arr[]={"India", "United States"};
       System.out.println("this side is Yash garg");
        System.out.println("WikipediaDownloader was running at "+new Date().toString());
       for (String keyword:arr)
        taskManager.waitTillQueueIsFreeAndAddTask(new WikepediaDownloader(keyword));
    }
}
