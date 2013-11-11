package crawler;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to create the hashmap that stores a string as a key and
 * the url where the used is found as a value;
 *
 * @author chabineza
 */
public class SearchHashMap {

    private HashMap<String, Set<URL>> docSearch;

    /**
     * This constructor to initializes the hashmap
     *
     */
    public SearchHashMap() {
        docSearch = new HashMap<>();
    }

    public boolean isEmpty() {
        return docSearch.isEmpty();
    }

    /**
     * This method is used to add string to the hashmap
     * @param word this parameter is used to add in url
     * @param url : Is used to enter inside the
     * @see printHashMap
     */
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
    /**
     * 
     * @return HashMap
     */

    public HashMap getHashMap() {
        return docSearch;
    }
    /**
     * this method 
     */

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
