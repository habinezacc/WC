package crawler;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to create the hashmap that stores a string as a key and
 * the url where the used is found as a value;
 * @author habineza, Ivy Wainaina, Mutangana
 */
public class SearchHashMap {
    private HashMap<String, Set<URL>> docSearch;

    /**
     *This constructor to initializes the hashmap
     *The hashmap maps String to an URL as a value
     */
    public SearchHashMap() {
        docSearch = new HashMap<>();
    }
    
    /**
     * This method checks if the hash map contains no entry
     * @return boolean false if the underlying hash map is empty 
     * and false if the hash map contains at least an entry.
     */
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
        word = word.toLowerCase();
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
     * This method is the accessor for the underlying hashmap
     * @return HashMap
     */
    public HashMap getHashMap() {
        return docSearch;
    }
   /**
    * This method prints out the set of urls
    * @param set 
    */
    public void printSet(Set<URL> set) {
        for (URL url : set) {
            System.out.println(url.toString());
        }
    }
}
