import Controller.PageControl;
import Entity.Url;
import Entity.Var;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangminh on 09/04/2017.
 */
public class ScapeMain {
    public static void main(String args[]) throws InterruptedException {
        try {
            Var.client = new TransportClient()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));



        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Var.pivot=true;
        List<String> getUrlByYear=new ArrayList<>();
        for(int i=1993;i<1995;i++){
            getUrlByYear.add(Url.urlByYear+i%100+"");
        }
        for(String url:getUrlByYear){
            PageControl.LoadUrlToListPost(url);
            while (Var.pivot){
                Thread.sleep(10000);
            }
            Var.pivot=true;
        }
        System.out.println("Crawling finish!!!");
    }
}
