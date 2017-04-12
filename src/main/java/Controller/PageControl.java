package Controller;

import Entity.Var;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 09/04/2017.
 */
public class PageControl {
    //create many thread in here
    public static void LoadUrlToListPost(String url){
        List<String> listPostbyMonth=new ArrayList<>();
        try {
/*
            Document doc= Jsoup.connect(url).timeout(70000).header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36").get();
*/

            Document doc= Jsoup.connect(url).header("Host","https://arxiv.org/").timeout(70000).get();
            Elements elements=doc.select("div#content li");
            for(Element e: elements){
                String bold=e.select("b").html();
                String i=e.select("i").html();
                int numPost=Integer.parseInt(bold)+Integer.parseInt(i);
                listPostbyMonth.add(e.select("a").attr("abs:href")+"?show="+numPost);
            }
            Var.numOfThread=listPostbyMonth.size();
            for(String urlPostByMonth:listPostbyMonth){
                LoadPostThread run=new LoadPostThread();
                run.setUrlListPost(urlPostByMonth);
                Thread thread=new Thread(run);
                thread.run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
