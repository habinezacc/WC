/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WebCrawler;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 */
public class IndexSearch {
    private String topic;
    public IndexSearch(String topic){
        this.topic = topic.toLowerCase();
    }
    private Boolean emptyKeyWord() {
        if (topic.trim() == null) {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }

    public LinkedList<URL> search(HashMap<String, LinkedList<URL>> searchList) {
        if (!emptyKeyWord()) {
            LinkedList<URL> urlList;
            urlList = searchList.get(topic);
            return urlList;
        }
        return null;
    } 
}
