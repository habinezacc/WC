/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCrawler;

import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/**
 *
 */
public class IndexSearch
{

    private final String TOPIC;
    private final HashMap<String, LinkedList<URL>> SEARCH_MAP;
    private final JTextArea OUTPUT;
    private final JLabel LABEL;
    private final JProgressBar PROGRESS_BAR;

    public IndexSearch(String topic, HashMap<String, LinkedList<URL>> searchMap, JTextArea output, JLabel Label, JProgressBar progressBar)
    {
        this.TOPIC = topic.toLowerCase();
        this.OUTPUT = output;
        this.LABEL = Label;
        this.PROGRESS_BAR = progressBar;
        this.SEARCH_MAP= searchMap;
    }

    private Boolean emptyKeyWord()
    {
        if (TOPIC.trim() == null)
        {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }

    private LinkedList<URL> get(HashMap<String, LinkedList<URL>> searchList)
    {
        if (!emptyKeyWord())
        {
            LinkedList<URL> urlList;
            urlList = searchList.get(TOPIC);
            return urlList;
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
                if (!(TOPIC.trim() == null || TOPIC.equals("")))
                {
                    try
                    {
                        LinkedList<URL> list;

                        list = get(SEARCH_MAP);
                        String name = "";
                        if (list != null)
                        {
                            for (URL url : list)
                            {
                                int j = 0, progress = 0;
                                sleep(100);
                                name = url.toString().substring(url.toString().lastIndexOf("/") + 1);
                                progress = (j + 1) * 100 / list.size();
                                PROGRESS_BAR.setValue(progress);
                                if (PROGRESS_BAR.getValue() < 100)
                                {
                                    LABEL.setText("Please wait while searching...");
                                    OUTPUT.append(name + "  ===>  " + url.toString() + "\n");
                                    j++;
                                } else
                                {
                                    LABEL.setText("Search complete");
                                    OUTPUT.append(name + "  ===>  " + url.toString() + "\n");
                                    j++;
                                }
                            }
                        } else
                        {
                            OUTPUT.setText("\"" + TOPIC + "\"" + " was NOT FOUND!");
                        }
                    } catch (InterruptedException ex)
                    {
                        System.out.println("Something went wrong with the progress bar \n" + ex);
                    }
                } else
                {
                    LABEL.setText("Please enter a topic");
                }
            }

        }.start();
    }
}
