
package WebCrawler;

import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

/******************************************************************
 *DocumentSearch class is used to for documents search by searching the topic
 * through the hash map
 * @author Ivy wainaina Concorde Habineza and Vivens Mutangana
 * @see IndexSearch
 *
 ******************************************************************/
public class DocumentSearch
{

    private final String KEYWORD;
    private final HashMap<String, Set<URL>> SEARCH_MAP;
    private final JTextArea OUTPUT;
    private final JLabel LABEL;
    private final JProgressBar PROGRESS_BAR;
    
    
     /****************************************************************************************
    * This the constructor that is used to create object of documentSearch
    * @param keyword this the string  that is needed to be added in hash map
    * @param searchMap This is the linkedList that needed when hash map is created
    * @param output This is the variable used to display results in GUI
    * @param Label  This variable is used to label the progress bar
    * @param progressBar This variable used to show the progress of searching
    ****************************************************************************************/
    public DocumentSearch(String keyword, HashMap<String, Set<URL>> searchMap, JTextArea output, JLabel Label, JProgressBar progressBar)
    {
        
        this.SEARCH_MAP=searchMap;
        this.OUTPUT = output;
        this.LABEL = Label;
        this.PROGRESS_BAR = progressBar;
        this.KEYWORD = keyword.toLowerCase();
    }

      /*************************************************************************************
     * Method: emptyKeyWord is used to if the variable topic is not empty
     * it does not have any parameter
     * 
     * @return boolean true is the topic is empty or false if it is not empty
     ***************************************************************************************/
    private Boolean emptyKeyWord()
    {
        if (KEYWORD.trim() == null)
        {
            System.out.println("Please enter a key word to search");
            return true;
        }
        return false;
    }
/**************************************************************************************
     *  METHOD: get()  is used to return the list of URL's with its corresponding to document
     * 
     * @param searchList this parameter is is hash map that need to searched for topic
     * @return urlList if the topic is found in the hash map else it return null
     *************************************************************************************/
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

    
    /*************************************************************************************
     * METHOD: search is used add search results in GUI and show the progress bar
     * 
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
                                sleep(10);
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
