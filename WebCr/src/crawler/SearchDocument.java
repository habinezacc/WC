package crawler;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import webcr.HashSerializer;

public class SearchDocument {

    private String keyword;

    public SearchDocument() {
    }

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
            HashMap<String, Set<URL>> hm = (HashMap<String, Set<URL>>) HashSerializer.deSerialize("cash.ser");
            set = hm.get(s);
        }
        return set;
    }

}
