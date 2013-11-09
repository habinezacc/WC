/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import webcr.HashSerializer;

/**
 *
 * @author chabineza
 */
public class SearchDocument {
    private String keyword;
    public SearchDocument() {}

    public Boolean emptyStringError() {
        if (keyword == null) {
            System.out.println("Empyt keywork to search!");
            System.exit(1);
            return true;
        }
        return false;
    }
    
    public Set<URL> search(HashSerializer hsr, String s) {
        this.keyword = s;
        Set<URL> set = null;
        if (!emptyStringError()) {
            HashMap<String, Set<URL>> hm = (HashMap<String, Set<URL>>) hsr.deSerialize("cash.ser");
            set = hm.get(s);
        }
        return set;
    }

    public Set<String> searchTwoWords(String word1, String word2) {
        Set<String> result = new HashSet<>();
        return result;
    }
}
