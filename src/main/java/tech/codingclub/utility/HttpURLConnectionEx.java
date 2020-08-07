package tech.codingclub.utility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class HttpURLConnectionEx {
   private final static String USER_AGENT="Chrome/";
   //http get request
    public static String sendget(String urlStr) throws IOException {
        StringBuilder result=new  StringBuilder();
        URL url=new URL(urlStr);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("User-Agent", "Chrome");
        conn.setRequestMethod("GET");
        BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line=rd.readLine())!=null)
        {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static void main(String[] args) {

        try {
            System.out.println(sendget("https://codingclub.tech/test-get-request?name=Yash_Garg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
