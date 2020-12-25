import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] arg = new String[] {"http://ege.edu.ru/ru/","1","1"};
        int depth = 0;
        int numThreads = 0;
        if (arg.length != 3) {

            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }
        try {
            depth = Integer.parseInt(arg[1]);
            numThreads = Integer.parseInt(arg[2]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }
        URLDepthPair currentDepthPair = new URLDepthPair(arg[0], 0);//текущая ссылка
        URLPool pool = new URLPool(depth);//объект класса URLPool
        pool.put(currentDepthPair);//кладем ссылку в объект класса URLPool
        int initialActiveThreads = Thread.activeCount();//Возвращает оценку количества
        // активных потоков в группе потоков текущего потока и ее подгруппах
        while (pool.getWaitThreads() != numThreads) {//пока кол-во потоков не равно заданному
            if (Thread.activeCount() - initialActiveThreads < numThreads) {
                CrawlerTask crawler = new CrawlerTask(pool);//создание нового потока
                new Thread(crawler).start();//запуск нового потока
            }
            else {
                try {//Мы можем приостановить выполнение потока на заданное время с помощью статического метода Thread.sleep()
                    Thread.sleep(500); // 0,5 секунды
                }
                catch (InterruptedException ie) {
                    System.out.println("Caught unexpected InterruptedException,"
                            + " ignoring...");
                }
            }
        }//Используем итератор для перебора списка processedURLs
        Iterator<URLDepthPair> iter = pool.processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.exit(0);
    }
}
