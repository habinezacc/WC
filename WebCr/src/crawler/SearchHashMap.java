package crawler;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chabineza
 */
public class SearchHashMap {

    private HashMap<String, Set<URL>> docSearch;

    public SearchHashMap() {
        docSearch = new HashMap<>();
    }

    public boolean isEmpty() {
        return docSearch.isEmpty();
    }

    public void addString(String word, URL url) {
        Set<URL> set = new HashSet();
        if (docSearch.containsKey(word)) {
            set = docSearch.get(word);
            if (!(set.contains(url))) {
                set.add(url);
            }

        } else {
            set.add(url);
        }
        docSearch.put(word, set);
    }

    public HashMap getHashMap() {
        return docSearch;
    }

    public void printHashMap() {
        Set<String> keyset = docSearch.keySet();
        for (String key : keyset) {
            printSet(docSearch.get(key));
        }
    }
    public void printSet(Set<URL> set) {
        for (URL url : set) {
            System.out.println(url.toString());
        }
    }
    
    public void printSetString(Set<String> contentSet) {
        for (String url : contentSet) {
            System.out.println(url.toString());
        }
    }
}
