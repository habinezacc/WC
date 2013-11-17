/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCrawler;

import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 * @author chabineza
 */
public class DocumentSearch
{

    private final String KEYWORD;
    private final HashMap<String, Set<URL>> SEARCH_MAP;
    private final JTextArea OUTPUT;
    private final JLabel LABEL;
    private final JProgressBar PROGRESS_BAR;
    public DocumentSearch(String keyword, HashMap<String, Set<URL>> searchMap, JTextArea output, JLabel Label, JProgressBar progressBar)
    {
        
        this.SEARCH_MAP=searchMap;
        this.OUTPUT = output;
        this.LABEL = Label;
        this.PROGRESS_BAR = progressBar;
        this.KEYWORD = keyword.toLowerCase();
    }

    private Boolean emptyKeyWord()
    {
        if (KEYWORD.trim() == null)
        {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }

    public Set<URL> get(HashMap<String, Set<URL>> searchMap)
    {
        if (!emptyKeyWord())
        {
            String[] keywords = KEYWORD.split(" ");
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
                urlSet.addAll(searchMap.get(KEYWORD));//the file where the whole string is found
            }
            return urlSet;
        }
        return null;
    }

    public void search()
    {
        PROGRESS_BAR.setValue(0);
        new Thread()
        {
            @Override
            public void run()
            {
                if (!(KEYWORD.trim() == null || KEYWORD.equals("")))
                {
                    try
                    {
                        Set<URL> set;

                        set = get(SEARCH_MAP);
                        if (set != null)
                        {
                            String document = "";
                            int j = 0, progress = 0;
                            for (URL url : set)
                            {
                                sleep(100);
                                document = url.toString().substring(url.toString().lastIndexOf("/") + 1);
                                progress = (j + 1) * 100 / set.size();
                                PROGRESS_BAR.setValue(progress);
                                if (PROGRESS_BAR.getValue() < 100)
                                {
                                    LABEL.setText("Please wait while searching...");
                                    OUTPUT.append(document + "  ===>  " + url.toString() + "\n");
                                    j++;
                                } else
                                {
                                    LABEL.setText("Search complete");
                                    OUTPUT.append(document + "  ===>  " + url.toString() + "\n");
                                    j++;
                                }
                            }
                        } else
                        {

                            OUTPUT.setText("\"" + KEYWORD + "\"" + " NOT FOUND!");
                        }
                    } catch (InterruptedException ex)
                    {
                        System.out.println("Something went wrong with the progress bar \n" + ex);
                    }
                } else
                {
                    LABEL.setText("Please enter a keyword");
                }
            }

        }.start();
    }
}
