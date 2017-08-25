package org.app.william;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String [] args){
        try {
            InputStream in = new FileInputStream("C:\\Users\\gluo7\\IdeaProjects\\gs-accessing-data-mysql\\rssreader\\src\\main\\resources\\buildapcsales.rss");
            RssParser rssParser = new RssParser(in);
            rssParser.readXML();
            rssParser.printFeed();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
