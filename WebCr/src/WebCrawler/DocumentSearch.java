/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCrawler;

import java.net.URL;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author chabineza
 */
public class DocumentSearch
{

    private String keyword;

    public DocumentSearch(String keyword)
    {
        this.keyword = keyword.toLowerCase();
    }

    private Boolean emptyKeyWord()
    {
        if (keyword.trim() == null)
        {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }

    public Set<URL> search(HashMap<String, Set<URL>> searchMap)
    {
        if (!emptyKeyWord())
        {
            String[] keywords = keyword.split(" ");
            Set<URL> urlSet = searchMap.get(keywords[0]);
            if (keywords.length > 1)
            {
                Set<URL> other;
                for (int index = 1; index < keywords.length; index++)
                {
                    other = searchMap.get((keywords[index]));
                    if (other != null)
                    {
                        urlSet.retainAll(other);
                    }
                }
                urlSet.addAll(searchMap.get(keyword));//the file where the whole string is found
            }
            return urlSet;
        }
        return null;
    }
}
