import java.util.*;

/**Реализуйте класс с именем URLPool, который будет хранить список
всех URL-адресов для поиска, а также относительный "уровень" каждого из
этих URL-адресов (также известный как "глубина поиска")**/
public class URLPool {
    private final LinkedList<URLDepthPair> pendingURLs;
    public LinkedList<URLDepthPair> processedURLs;
    private final ArrayList<String> seenURLs = new ArrayList<>();
    public int waitingThreads;
    int maxDepth;
    public URLPool(int maxDepthPair) {
        maxDepth = maxDepthPair;
        waitingThreads = 0;
        pendingURLs = new LinkedList<>();
        processedURLs = new LinkedList<>();
    }
    //Synchronized (с англ. "синхронизированный") - это ключевое слово,
    // которое позволяет заблокировать доступ к методу или части кода, если
    // его уже использует другой поток.

    //кол-во потоков
    public synchronized int getWaitThreads() {
        return waitingThreads;
    }//кол-во ссылок в ожидании
    public synchronized int size() {
        return pendingURLs.size();
    }
    //метод добавляет ссылку pendingURLs(в ожидании urls) или
    // в processedURLs(обработанные urls) и seenURLs (просмотренные urls)
    public synchronized void put(URLDepthPair depthPair) {
        if (waitingThreads != 0) {
            --waitingThreads;
            this.notify(); //notify(): продолжает работу потока,
            // у которого ранее был вызван метод wait()
        }
        if (!seenURLs.contains(depthPair.getURL()) &
                !pendingURLs.contains(depthPair)) {
            //если просмотренные урл совпадают с параметром и
            // ссылки в ожидании не совпадают с параметром
            if (depthPair.getDepth() < maxDepth) {//если глубина аргумента меньше макс. глубины
                pendingURLs.add(depthPair);//добавляем ссылку в ожидание
            }
            else {//в противном случае добавляем сслыку в обработанные
                // urls и просмотренные urls
                processedURLs.add(depthPair);
                seenURLs.add(depthPair.getURL());
            }
        }
    }
    //метод делает ссылку уникальной и добавляем ее к обработанным и просмотренным
    public synchronized URLDepthPair get() {
        URLDepthPair myDepthPair;
        //Метод isEmpty() класса java.util.ArrayList в Java возвращает
        // true, если этот список не содержит элементов
        while (pendingURLs.isEmpty()) {
            waitingThreads++;
            try {
                this.wait();//wait() освобождает монитор и переводит вызывающий
                // поток в состояние ожидания до
                // тех пор, пока другой поток не вызовет метод notify()
            }
            catch (InterruptedException e) {
                System.err.println("MalformedURLException: " + e.getMessage());
                return null;
            }
        }
        //Метод pop() в Java используется для удаления объекта в верхней части этого
        // стека и возвращает этот объект в качестве значения этой функции.
        myDepthPair = pendingURLs.pop(); //удаляет ссылку из pendingURLs(в ожидании urls)

        //Java String contains()- метод чтобы проверить, содержит ли String
        // указанную последовательность символов.
        while (seenURLs.contains(myDepthPair.getURL())) {//пока в прсмотренных находятся
            // myDepthPair(ссылка)

            myDepthPair = pendingURLs.pop();//ссылка "делается" уникальной
        }
//добавляем ее к обработанным и просмотренным
        processedURLs.add(myDepthPair);
        seenURLs.add(myDepthPair.getURL());

        return myDepthPair;
    }
}