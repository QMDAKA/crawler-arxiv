package Entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 09/04/2017.
 */
public class Post {
    String abstractPost;
    String title;
    String subject;
    String journal;
    List<String> authors;
    int year;
    String url;
    String code;
    String time;



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAbstractPost() {
        return abstractPost;
    }

    public void setAbstractPost(String abstractPost) {
        this.abstractPost = abstractPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public Post(){}

    public Post(String urlByElenment) {
        try {
            authors=new ArrayList<>();
            Document doc= Jsoup.connect(urlByElenment).timeout(700000).get();
            title=doc.select("div#abs h1.title").text().substring(7);
            abstractPost=doc.select("blockquote").text().substring(10);
            subject=doc.select("td.tablecell.subjects span").html();
            url=urlByElenment;
            code=urlByElenment.substring(29);
            year=(code.charAt(0)-48)*10+code.charAt(1)-48;
            journal=doc.select("div.metatable td.tablecell.jref").text();
            if(year>90)
                year+=1900;
            else
                year+=2000;
            Elements elements=doc.select("div.authors a");
            for(Element e:elements)
            {
                authors.add(e.html());
            }
            time=doc.select("div.dateline").html();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
