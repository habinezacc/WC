
package WebCrawler;

import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/******************************************************************
 *IndexSearch class is used to for index search by searching the topic
 * through the hash map
 * @author Ivy wainaina Concorde Habineza and Vivens Mutangana
 * @see DocumentSearch
 * 
 ******************************************************************/
public class IndexSearch
{

    private final String TOPIC;
    private final HashMap<String, LinkedList<URL>> SEARCH_MAP;
    private final JTextArea OUTPUT;
    private final JLabel LABEL;
    private final JProgressBar PROGRESS_BAR;
    /****************************************************************************************
    * This the constructor that is used to create object of indexSearch
    * @param topic this the string topic that is needed to be added in hash map
    * @param searchMap This is the linkedList that needed when hash map is created
    * @param output This is the variable used to display results in GUI
    * @param Label  This variable is used to label the progress bar
    * @param progressBar This variable used to show the progress of searching
    ****************************************************************************************/
    public IndexSearch(String topic, HashMap<String, LinkedList<URL>> searchMap, JTextArea output, JLabel Label, JProgressBar progressBar)
    {
        this.TOPIC = topic.toLowerCase();
        this.OUTPUT = output;
        this.LABEL = Label;
        this.PROGRESS_BAR = progressBar;
        this.SEARCH_MAP= searchMap;
    }
/*************************************************************************************
     * This method is used to if the variable topic is not empty
     * it does not have any parameter
     * 
     * @return boolean true is the topic is empty or false if it is not empty
     ***************************************************************************************/
    private Boolean emptyKeyWord()
    {
        if (TOPIC.trim() == null)
        {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }
 /**************************************************************************************
     *  METHOD: get()  is used to return the list of URL's with its corresponding topic
     * 
     * @param searchList this parameter is is hash map that need to searched for topic
     * @return urlList if the topic is found in the hash map else it return null
     *************************************************************************************/
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
    
    
    /*************************************************************************************
     * METHOD: search is used add search results in GUI and show the progress bar
     * @param no argumets
     * @return void
     * 
     ************************************************************************************/
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
                                sleep(10);
                                name = url.toString().substring(url.toString().lastIndexOf("/") + 1);
                                progress = (j + 1) * 100 / list.size();
                                PROGRESS_BAR.setValue(progress);
                                if (PROGRESS_BAR.getValue() < 100)
                                {
                                    LABEL.setText("Please wait while searching...");
                                    OUTPUT.append(name + "\t===>  " + url.toString() + "\n");
                                    j++;
                                } else
                                {
                                    LABEL.setText("Search complete");
                                    OUTPUT.append(name + "\t===>  " + url.toString() + "\n");
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
