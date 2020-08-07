package tech.codingclub.utility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.database.GenericDB;
import tech.codingclub.entity.Songs;

import java.io.IOException;
import java.util.ArrayList;

public class SongsFetchFileRead implements Runnable {
    public String path;
    public String parentURL;
    public String URL;
    public String album;
    public String duration;
    public String singers;
    public String lyricist;
    public String music_director;
    public String download_128;
    public String getDownload_256;
    public String imageURL;
    public SongsFetchFileRead(String path)
    {
        this.path=path;
    }
    public  SongsFetchFileRead()
    {

    }
    public void run() {
        ArrayList<String >a=new ArrayList<String >();
        a=FileUtility.inputPrintfile(path);
        for (String nm:a)
        {
            System.out.println(nm);
            String response= null;
            String imageURL = null;
            try {
                response = HttpURLConnectionEx.sendget(nm);
                //System.out.println(response);
                Document document= Jsoup.parse(response,nm);
                Elements childElement=document.body().select(".archive-body > *");
                for (Element elements:childElement)
                {
                     parentURL="";
                     URL="";
                     album="";
                     duration="";
                     singers="";
                     lyricist="";
                     music_director="";
                     download_128="";
                     getDownload_256="";
                     imageURL="";
                    parentURL=nm;
                        String link = elements.select(".thumb-image a ").attr("href");
                        if (link.startsWith("/")) {
                            link = "https://songspk.mobi" + link;

                            System.out.println(link);
                            URL=link;
                        }
                          imageURL=elements.select(".image-hover img ").attr("src");
                        try {
                            String response1=HttpURLConnectionEx.sendget(link);
                           // System.out.println(response1);
                            Document document1= Jsoup.parse(response1,nm);
                            Elements x=document1.body().select( ".list-group-item > *");
                            int state=0;
                            for (Element e:x)
                            {
                                if (e.text().equals("Album"))
                                    state=1;
                                else if (state==1)
                                {
                                    album=e.text();
                                    state=0;
                                }
                                else if(e.text().equals("Duration"))
                                    state=2;
                                else if (state==2)
                                {
                                    duration=e.text();
                                    state=0;
                                }
                                else if(e.text().equals("Singers"))
                                    state=3;
                                else if (state==3)
                                {
                                    singers=e.text();
                                    state=0;
                                }
                                else if(e.text().equals("Music Director"))
                                    state=4;
                                else if (state==4)
                                {
                                    music_director=e.text();
                                    state=0;
                                }
                                else if(e.text().equals("Lyricist"))
                                    state=5;
                                else if (state==5)
                                {
                                    lyricist=e.text();
                                    state=0;
                                    break;
                                }
                            }
                            System.out.println(imageURL+"   "+album+"  "+duration+"  "+singers+"   "+music_director+"  " +lyricist);
                            download_128=document1.body().select(".col-body a").get(0).attr("href");
                            getDownload_256=document1.body().select(".col-body a").get(1).attr("href");
                            Songs songs=new Songs(parentURL,URL,album,duration,singers,lyricist,music_director,download_128,getDownload_256,imageURL);
                            //put into the database
                            new GenericDB<Songs>().addRow(tech.codingclub.tables.Songs.SONGS,songs);
                        }catch (Exception e)
                        {
                            System.out.println("garg");
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
          System.out.println("yash" );
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager=new TaskManager(20);
        taskManager.waitTillQueueIsFreeAndAddTask(new SongsFetchFileRead("C:\\Users\\dream\\IdeaProjects\\tech.codingclub\\data\\practice\\file\\crawlsonglist.txt"));
    }
}
