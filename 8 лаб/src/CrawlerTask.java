import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.*;
//Чтобы выполнить веб-сканирование в нескольких потоках, необходимо
//создать класс CrawlerTask, который реализует интерфейс Runnable
public class CrawlerTask implements Runnable {
    public URLDepthPair depthPair;
     public URLPool pool;
    public CrawlerTask (URLPool newPool) {
        pool = newPool;
    }
    public void run() {
        depthPair = pool.get();
        int depth = depthPair.getDepth();//определение глубины
        LinkedList<String> linksList = null;
        try {
            linksList = Crawler.getAllLinks(depthPair);
        }
        catch (IOException ex) {
            Logger.getLogger(CrawlerTask.class.getName()).log(Level.SEVERE,null, ex);
        }
        for (int i = 0; i < linksList.size(); i++) {//перебор linksList
            String newURL = linksList.get(i);
            URLDepthPair newDepthPair = new URLDepthPair(newURL, depth + 1);//запись
            // уникального url с увеличением глубины
            pool.put(newDepthPair);//запись в pool
        }
    }
}