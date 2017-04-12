package Controller;

import Entity.Post;
import Entity.Var;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.geometry.Pos;
import org.elasticsearch.action.index.IndexResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by quangminh on 09/04/2017.
 */
public class LoadPostThread implements Runnable{
    String urlListPost;


    public String getUrlListPost() {
        return urlListPost;
    }

    public void setUrlListPost(String urlListPost) {
        this.urlListPost = urlListPost;
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            System.out.println("Open thread for url "+ urlListPost);
            Document doc= Jsoup.connect(urlListPost).timeout(70000).get();
            Elements elements = doc.select("div#content dt span");
            for(Element e:elements){
                String urlByElenment=e.select("a").get(0).attr("abs:href");
                Post post=new Post(urlByElenment);
                System.out.println("Thread:"+urlListPost+";Element:"+post.getTitle());
                System.out.println(urlByElenment);
                json = mapper.writeValueAsString(post);
                IndexResponse response = Var.client.prepareIndex("arxiv" ,"arxiv"+post.getYear())
                        .setSource(json)
                        .get();

            }
            Var.numOfThread--;
            if(Var.numOfThread==0)
                Var.pivot=false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
