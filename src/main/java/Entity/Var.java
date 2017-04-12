package Entity;

import org.elasticsearch.client.Client;

/**
 * Created by quangminh on 09/04/2017.
 */
public class Var {
    public static Client client;
    public static int numOfThread=0;
    public static boolean pivot=true;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        Var.client = client;
    }

    public int getNumOfThread() {
        return numOfThread;
    }

    public void setNumOfThread(int numOfThread) {
        this.numOfThread = numOfThread;
    }

    public boolean isPivot() {
        return pivot;
    }

    public void setPivot(boolean pivot) {
        this.pivot = pivot;
    }
}
