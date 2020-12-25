import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
public class Crawler {


    static LinkedList<String> getAllLinks(URLDepthPair myDepthPair) throws IOException {
        LinkedList<String> URLs = new LinkedList<String>();
        Socket sock;
        try {
            sock = new Socket(myDepthPair.getWebHost(), 80);//Программа должна подключиться к указанному сайту в URL-адресе
            //на порт 80 с использованием сокета и запросить указанную вебстраницу.
        }
        catch (UnknownHostException e) {
            System.err.println("UnknownHostException: " + e.getMessage());
            return URLs;
        }
        catch (IOException ex) {
            return URLs;
        }
        /** устанавливает время ожидания сокета
        (Socket) в миллисекундах.**/
        try {
            sock.setSoTimeout(3000);
        }
        catch (SocketException exc) {
            System.err.println("SocketException: " + exc.getMessage());
            return URLs;
        }
        String docPath = myDepthPair.getDocPath();
        String webHost = myDepthPair.getWebHost();
        OutputStream outStream;//Этот абстрактный класс является суперклассом всех классов, представляющих выходной поток байтов. Выходной поток принимает выходные байты и отправляет их в некоторый приемник.
        try {
            outStream = sock.getOutputStream();//Этот метод возвращает объект, OutputStream в который можно записать
            // данные, и выдает соответствующее исключение, если это невозможно
        }
        catch (IOException exce) {
            return URLs;
        }
        /**Потоки вывода организованы проще
         * можно создать экземпляр PrintWriter непосредственно из объекта OutputStream,
         * а затем вызвать его метод println для отправки строки текста на другой конец соединения
         **/

        // Инициализируем сокет и отравляем запрос на сайт.
        PrintWriter myWriter = new PrintWriter(outStream, true);//Параметр autoFlush установите в значение true. Это приведет к очищению
        //буфера вывода после каждого вызова метода println.



        //Пример docPath: docPath - /metrika/tag.js,
        // полный url - https://mc.yandex.ru/metrika/tag.js


        //Пример webHost: www.iprbookshop.ru и http://www.iprbookshop.ru

        if (docPath.length() == 0) {
            myWriter.println("GET / HTTP/1.1");
            myWriter.println("Host: " + webHost);
            myWriter.println("Connection: close");
            myWriter.println();
        }
        else {
            myWriter.println("GET " + docPath + " HTTP/1.1");
            myWriter.println("Host: " + webHost);
            myWriter.println("Connection: close");
            myWriter.println();
        }
        InputStream inStream;
        try {
            inStream = sock.getInputStream();//Этот метод позволяет сокету получать данные с другой стороны
            //соединения
        }
        catch (IOException excep) {
            return URLs;
        }
        /**inStreamReader имеет тип InputStreamReader,
         * который может читать символы из сокета. но так как это неудобно
         * будем использовать класс BufferedReader.
         **/
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader BuffReader = new BufferedReader(inStreamReader);
        int serverCode = 0;
        String lineCode;
        try {
            lineCode = BuffReader.readLine();//readLine предусмотренный в BufferedReader.
            // Данный метод будет читать
            //целую строку с другого конца соединения.
        }
        catch (IOException except) {
            return URLs;
        }
        //В классе Pattern также определен метод matcher(String input), который в качестве
// параметра принимает строку, где надо проводить поиск, и возвращает объект Matcher
        Pattern patternCode = Pattern.compile("([234])[0-9]{2}");
        Matcher matcherCode = patternCode.matcher(lineCode); //Создает сопоставление, которое сопоставляет данный ввод с этим шаблоном
        while (matcherCode.find()) {// возвращает true, если в строке есть подстрока, которая совпадает с шаблоном, и переходит к этой подстроке
            serverCode = Integer.parseInt(lineCode.substring(matcherCode.start(), matcherCode.end() - 2));
        }

        //все ссылки
        if (serverCode == 2) {
            while (true) {
                String line;
                try {
                    line = BuffReader.readLine();
                }
                catch (IOException except) {
                    return URLs;
                }
                if (line == null) {
                    break;
                }
                Pattern patternURL = Pattern.compile(
                        "[\"]" + "[https?://]{7,8}" + "([w]{3})?" + "[\\w\\.\\-]+" + "\\." + "[A-Za-z]{2,6}" + "[\\w\\.-/]*" + "[\"]");
                Matcher matcherURL = patternURL.matcher(line);
                while (matcherURL.find()) {
                    String newLink = line.substring(matcherURL.start() + 1,
                            matcherURL.end() - 1);
                    URLs.add(newLink);
                }
            }
            sock.close();
            return URLs;
        }
        //уникальные ссылки
        if (serverCode == 3) {
            String newURL = "";
            String tempLine;
            while (true) {
                try {
                    tempLine = BuffReader.readLine();
                }
                catch (IOException except) {
                    return URLs;
                }
                if (tempLine == null) {
                    break;
                }
                Pattern patternNewURL = Pattern.compile("(Location: ){1}[\\S]+");
                Matcher matcherNewURL = patternNewURL.matcher(tempLine);
                while (matcherNewURL.find()) {
                    newURL = tempLine.substring(matcherNewURL.start() + 10,
                            matcherNewURL.end());
                }
            }
            if (newURL.equals(myDepthPair.getURL())) {//если новый url совпадает с url аргумента
                sock.close(); //закрываем сокет
                return URLs;
            }
            URLDepthPair newDepthPair;
            newDepthPair = new URLDepthPair(newURL, myDepthPair.getDepth());//новый URL с глубиной
            return getAllLinks(newDepthPair);//вызов метода с новой ссылкой
        }
        return URLs;
    }
}