package WebCrawler;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * **********************************************************************************************************
 *
 *
 *
 * Project:The Web Crawler Course:	17630 - Computer Science Principles for
 * Practicing Engineers
 *
 * @author: Anthony J. Lattanze
 * @version: 1.0 - July 1, 2008 CO-AUTHORS: This code has been co-authored for
 * the purpose of WebCrawler Assignment The co-authors have used this code to
 * implement a webcrawler but it has been updated accordingly.
 *
 * Names of CO-Authors: Vivens Mutangana, Concorde Habineza and Ivy Wainaina
 *
 *
 * Purpose:This file containts the classes for a very basic web crawler that
 * illustrates the basic concepts of URL parsing, "robot safe", page processing,
 * and fundamental web crawling. Note that this program should not incorperate
 * malicious elements and ALL STUDENTS MUST IMPLEMENT THE ROBOT SAFE PROTOCOL to
 * respect the crawling wishes of site administrators.
 *
 * Compilation: javac CrawlerEngine.java Usage: java CrawlerEngine <starting
 * URL> [number of pages] The web crawler will start the crawl at <starting URL>
 * and will download up to [number of pages]. The [number of pages] argument is
 * optional.
 *
 * Internal Methods:
 *
 * boolean Initialize( String[] s ); RobotSafe(URL u); AddNewUrl(URL u, String
 * s); LoadPage(URL u); Crawl(String[] s );
 * @see searchDocument
 *
 **************************************************************************************************************
 */
public class CrawlerEngine implements java.io.Serializable
{

    // Global Variables
    private String topic = "";
    private int numPages = 0; //number of pages that are loader and stored in the hash map
    private boolean _done = false;

    private static HashMap<String, Set<URL>> documentHashMap;
    private static HashMap<String, LinkedList<URL>> topicHashMap;
    private static Vector newURLs;			// This is a list of URLs to be searched
    private static Hashtable knownURLs;		// This table contains the list of known URLs
    public String results = new String();
    public static final int MAX_PAGES = 20;		// Default maxiumum pages
    public static final boolean DEBUG = false;		// This can be used to enable or disable
    // debug messages
    public static final String DISALLOW = "Disallow:";					// String used in RobotSafe method to
    // determine if page crawling is disallowed
    public static final int MAX_FILE_SIZE = 20000;	// Max size of file

    //int maxDepth;		        // This is the maximum number of pages to crawl
    /**
     * ***********************************************************************************
     *
     * METHOD:: Initialize(String[] argv)
     *
     * @param argv String[] argv - this are the command line arguments passed
     * directly from main().
     *
     * Purpose: This method initializes program variables and key data
     * structures based on the command line input.
     *
     * @return: boolean true - if the initialize was OK false - if there was a
     * problem with initialization
     *
     **************************************************************************************
     */
    public boolean Initialize(String webLink)
    {
        URL url;
        if (Cache.isFileEmpty("knownURLs.ser"))
        {
            knownURLs = new Hashtable();
        } else
        {
            knownURLs = (Hashtable) Cache.readObject("knownURLs.ser");
        }
        if (Cache.isFileEmpty("newURLs.ser"))
        {
            newURLs = new Vector();
        } else
        {
            newURLs = (Vector) Cache.readObject("newURLs.ser");
        }
        if (Cache.isFileEmpty("DocumentCache.ser"))
        {
        
            documentHashMap = new HashMap<String, Set<URL>>();
        } else
        {
            documentHashMap =  Cache.getDocumentHashMap();
        }
        if (Cache.isFileEmpty("indexCache.ser"))
        {
            topicHashMap = new HashMap<String, LinkedList<URL>>();
        } else
        {
            topicHashMap = Cache.getIndexCache();
        }
        numPages = 0;
        _done = false;

        if (webLink.length() == 0)
        {
            System.out.println("\n\nNo starting URL Provided. Correct Usage::");
            System.out.println("\njava CrawlerEngine <starting URL> [number of pages]");
            System.out.println("\nAfter you hit enter, the web crawler will start the crawl at");
            System.out.println("<starting URL> and search for URLs in these pages and crawl");
            System.out.println("those pages and so on up to [number of pages].Note that the");
            System.out.println("[number of pages] argument is optional and the default is set at 20");

            return false;

        } // if

        try
        {
            url = new URL(webLink);

        } catch (MalformedURLException e)
        {
            System.out.println("\n\nInvalid starting URL " + webLink);
            System.out.println("\n\nValid URLs start with 'http://www...' and so on. You may also");
            System.out.println("specify the number of pages to search as an optional second argument.");

            return false;

        } // try

        knownURLs.put(url,
                new Integer(1));
        newURLs.addElement(url);

        System.out.println(
                "Starting crawl with initial URL:: " + url.toString());
       // System.out.println("Maximum number of pages::" + maxDepth);

        //Set the proxy and port - important for firewalls
        Properties props = new Properties(System.getProperties());

        props.put(
                "http.proxySet", "true");
        props.put(
                "http.proxyHost", "webcache-cup");
        props.put(
                "http.proxyPort", "8080");

        Properties newprops = new Properties(props);

        System.setProperties(newprops);

        return true;

    } // Initialize method

    /**
     * *********************************************************************************************************
     *
     * METHOD:: RobotSafe(URL url)
     *
     * Arguments:
     *
     * @param url URL url - this is the current url to process.
     *
     * Purpose: This method checks that the robot exclusion protocol does not
     * prohibit downloading and parsing the URL. This method will check the
     * /robots.txt file to for instructions about the site's Robot Exclusion
     * Protocol/Policy. Before crawling a site, this method is invoked to check
     * the robots.txt to see if the "Disallow:" string is is present. Any
     * "Disallow:" is assumed to apply to this crawler and will stop the
     * crawling process.on the site.
     *
     * @return: boolean false: crawling is disallowed true: crawling is
     * permitted.
     *
     *******************************************************************************************************
     */
    public boolean RobotSafe(URL url)
    {
        // We start this method by establishing  the complete URL of the robots.txt file.
        // More information about the robot.txt file and the standards governing "robot safe"
        // can be found at "http://www.robotstxt.org/"

        String strCommands;
        String strHost = url.getHost();
        String strRobot = "http://" + strHost + "/robots.txt";
        URL urlRobot;

        try
        {
            urlRobot = new URL(strRobot);

        } catch (MalformedURLException e)
        {

            // Something is wrong with the host - to be safe we mark it as unsafe
            // or assume that this side "disallows" crawling
            return false;

        } // try

        if (DEBUG)
        {
            System.out.println("Checking robot protocol:" + urlRobot.toString());
        }

        try
        {
            try (InputStream urlRobotStream = urlRobot.openStream())
            {
                byte b[] = new byte[10000];
                int numRead = urlRobotStream.read(b);
                strCommands = new String(b, 0, numRead);

                while (numRead != -1)
                {
                    numRead = urlRobotStream.read(b);

                    if (numRead != -1)
                    {
                        String newCommands = new String(b, 0, numRead);
                        strCommands += newCommands;
                    } // if

                }
            }

        } catch (IOException e)
        {
            // This means there is no robots.txt file. We assume it is OK to
            // crawl the site and continue the search.

            return true;
        } // try

        if (DEBUG)
        {
            System.out.println(strCommands);
        }

        // OK we found a robots.txt file and read it into the strCommands data structure.
        // We will assume that this robots.txt applies to us just to be on the safe side.
        // Next we will parse the file and search for the "Disallow:" string.
        String strURL = url.getFile();
        int index = 0;

        while ((index = strCommands.indexOf(DISALLOW, index)) != -1)
        {
            index += DISALLOW.length();
            String strPath = strCommands.substring(index);
            StringTokenizer st = new StringTokenizer(strPath);

            if (!st.hasMoreTokens())
            {
                break;

            } // if

            String strBadPath = st.nextToken();
            if (strURL.indexOf(strBadPath) == 0)
            {
                return false;

            } // if

        } // while

        return true;

    } // RobotSafe

    /**
     * *******************************************************************************************
     *
     * METHOD:: AddNewUrl(URL oldURL, String newUrlString)
     *
     *
     *
     * Arguments:
     *
     * @param oldURL - this is the current url that is being searched for URLs
     * @param newUrlString - this is a new URL found in the oldURL page.
     *
     * Note that URLs can be either absolute or relative.
     *
     * Purpose: This method adds new URLs that are found at the current (oldURL)
     * to the queue of URLs to crawl. This method will only add URLs that end in
     * htm or html.
     *
     * @return : void
     * @see LoadPage
     * ****************************************************************************************
     */
    public void AddNewUrl(URL oldURL, String newUrlString)
    {
        URL url;
        String urlOld;
        int dotIndex;

        if (DEBUG)
        {
            System.out.println("URL String " + newUrlString);
        }

        try
        {
            if (!(oldURL.toString().endsWith("/")) && (!oldURL.toString().endsWith(".html")))
            {
                urlOld = oldURL.toString();
                oldURL = new URL(urlOld + "/");
            }

            url = new URL(oldURL, newUrlString);
            addTopic(topic, url);
            if (!knownURLs.containsKey(url))
            {

                if (validURL(url.toString()))
                {
                    dotIndex = url.toString().length() - 4;
                    LoadPage(url);
                    
                    knownURLs.put(url, new Integer(1));
                    newURLs.addElement(url);

                    System.out.println("Found new URL " + url.toString());
                }// if
            } // if

        } catch (MalformedURLException e)
        {
        } // try
    }

    /**
     * **************************************************************************************************
     *
     * this METHOD is validURL(String newurl)
     *
     * @author Wainaina
     *
     *
     * Purpose: This method adds new URLs that are found at the current (oldURL)
     * to the queue of URLs to crawl. This method will only add URLs that end in
     * htm or html.
     *
     *
     * Arguments:
     * @param newurl this is the url that is needed to be checked to see if it
     * is valid
     *
     *
     *
     *
     * @return boolean true if the url does not include the black and white
     * subdirectory and it is on the textfiles.com website false otherwise
     *
     ************************************************************************************************
     */
    protected boolean validURL(String newurl)
    {
        if (newurl.contains("wdirectory.html")
                || !(newurl.contains("www.textfiles.com")))
        {

            return false;
        }
        return true;
    }// validURL Method

    /**
     * ********************************************************************************************
     *
     * METHOD:: LoadPage(URL url)
     *
     * Arguments:
     *
     * @param URL url - this the URL to the page to download
     *
     * Note that URLs can be either absolute or relative.
     *
     * Purpose: This method adds new URLs that are found at the current (oldURL)
     * to the queue of URLs to crawl. This method will only add URLs that end in
     * htm or html.
     *
     * @return : String
     * ********************************************************************************************
     */
    public String LoadPage(URL url)
    {
        int dotIndex;
        try
        {
            // We first try to open the file pointed to by "url"

            URLConnection urlConnection = url.openConnection();
            System.out.println("Downloading URL::" + url.toString());
            urlConnection.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();

            // OK, now we will read in the entire file or page pointed by "url."
            // There is a maximum file size that can be read in that is established
            // by MAX_FILE_SIZE.
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            String content = new String(b, 0, numRead);

            while ((numRead != -1) && (content.length() < MAX_FILE_SIZE))
            {
                numRead = urlStream.read(b);

                if (numRead != -1)
                {

                    String newContent = new String(b, 0, numRead);
                    content += newContent;

                } // if

            } // while
            dotIndex = url.toString().length();
            if (url.toString().charAt(dotIndex - 4) == '.' && !(url.toString().contains("htm")))
            {
                storeDocument(url, content);
                numPages++;

                if (numPages % 10 == 0 && numPages > 0)
                {
                    Cache.writeObject(documentHashMap, "documentCache");
                    Cache.writeObject(topicHashMap, "indexCache");

                }
            }
            return content;

        } catch (IOException e)
        {
            System.out.println("ERROR: couldn't open URL ");
            return "";

        } // try

    }//LoadPage method

    /**
     * ********************************************************************************************
     *
     * METHOD:: store(URL url, String content)
     *
     * @author Concorde Habineza
     *
     *
     * Arguments:
     * @param URL url - this the URL to the page whose content was download
     * @param content - content of the page on the URL url
     *
     *
     * Purpose: This method adds each string found in the content into the
     * hashamap and map it to the given URL url.
     *
     * @return : void
     * @see validUrl
     * ********************************************************************************************
     */
    public void storeDocument(URL url, String content)
    {
        String[] words;
        words = content.split(" ");
        for (String word : words)
        {
            word = word.trim();
            if (!(word.trim().equals("")) && !url.toString().endsWith(".com"))
            {
                word = word.toLowerCase();
                Set<URL> set = new HashSet();
                if (documentHashMap.containsKey(word))
                {
                    set = documentHashMap.get(word);
                    if (!(set.contains(url)))
                    {
                        set.add(url);
                    }

                } else
                {
                    set.add(url);
                }
                documentHashMap.put(word, set);

            }
        }
    }// store method

    /**
     * ********************************************************************************************
     *
     * METHOD:: ProcessPage(URL url, String page)
     *
     *
     *
     * Arguments:
     *
     * @param URL url - this the URL to the page to downloadad
     * @param page String - this is the page that was down loaded from "url."
     *
     *
     * This method will parse through the page looking for valid URLs. A valid
     * URL is defined as "<a href=" ... and ends with a close angle bracket,
     * preceded by a close quote. In some cases it may be possibly preceded by a
     * hatch mark indicated a fragment.
     *
     * @return : void
     * @see validUrl
     *
     *********************************************************************************************
     */
    public void ProcessPage(URL url, String page)
    {
        String lcPage = page.toLowerCase();  // Convert all text in the page to lower case.
        int index = 0;        // Character position in the page.
        int iEndAngle, ihref, iURL, bold,
                iCloseQuote, iHatchMark, iEnd;  // Key token characters for parsing

        while ((index = lcPage.indexOf("<a", index)) != -1)
        {
            iEndAngle = lcPage.indexOf(">", index);
            ihref = lcPage.indexOf("href", index);
            bold = lcPage.indexOf("<b>", index);
            if (ihref != -1)
            {
                iURL = lcPage.indexOf("\"", ihref) + 1;
                if ((iURL != -1) && (iEndAngle != -1) && (iURL < iEndAngle))
                {
                    iCloseQuote = lcPage.indexOf("\"", iURL);
                    iHatchMark = lcPage.indexOf("#", iURL);

                    if ((iCloseQuote != -1) && (iCloseQuote < iEndAngle))
                    {
                        iEnd = iCloseQuote;
                        if ((iHatchMark != -1) && (iHatchMark < iCloseQuote))
                        {
                            iEnd = iHatchMark;
                        }
                        if (bold != -1)
                        {
                            topic = page.substring(iEndAngle + 1, page.indexOf("</", ihref));

                        }
                        String newUrlString = page.substring(iURL, iEnd);
                        AddNewUrl(url, newUrlString);
                        Cache.writeObject(topicHashMap, "indexCache");
                    } // if
                } // if
            } // if

            index = iEndAngle;

        } // while
    } // ProcessPage method

    /**
     * ********************************************************************************************
     *
     * METHOD:: Crawl(String[] argv)
     *
     *
     *
     * Arguments:
     *
     * @param argv String[]this are the command line arguments passed directly
     * from main().
     *
     *
     *
     * Purpose: This method orchastrates the work of crawling utilizing the
     * above methods. This method will start with the base URL and continue to
     * pop off new URLs as they are added and crawl each in turn.
     *
     * @return void
     * @see validUrl
     *
     *********************************************************************************************
     */
    public void Crawl()
    {
        String link = "http://textfiles.com";
        if (Initialize(link))
        {
            while (!newURLs.isEmpty())
            {
                URL url = (URL) newURLs.elementAt(0);
                newURLs.removeElementAt(0);

                if (DEBUG)
                {
                    System.out.println("Searching " + url.toString());
                }

                if (RobotSafe(url))
                {
                    String page = LoadPage(url);
                    if (DEBUG)
                    {
                        System.out.println(page);
                    }

                    if (page.length() != 0)
                    {
                        ProcessPage(url, page);
                        Cache.writeObject(documentHashMap, "DocumentCache");
                     }

                    if (newURLs.isEmpty())
                    {
                        break;
                    }
                    Cache.writeObject(newURLs, "newURLs");
                    Cache.writeObject(knownURLs, "knownURLs");

                } else
                {
                    System.out.println("URL Disallowed::" + url.toString());
                } // if

            } // for

            System.out.println("Search complete.");
            saveAll();
            _done = true;
        } // if
    }//Crawl

    /****************************************************************************************
     *
     * METHOD: addTopic(String topic, URL url
     *
     * @author Vivens Mutangana
     *
     * @param topic this the string parameter containing topic
     * @param url This is the UrL type that needed to be matched with topic
     *
     * Purpose : This method is used to add topic in hashMap of topic
     *
     * @return void
     * ***************************************************************************************/
    private void addTopic(String topicName, URL url)
    {
        if (topicName.toString().indexOf(".") == -1
                && !topicName.toString().contains("US-")
                && !topicName.toString().trim().startsWith("<"))
        {
            topicName = topicName.toLowerCase();
            LinkedList<URL> list = new LinkedList<>();
            if (topicHashMap.containsKey(topicName))
            {
                list = topicHashMap.get(topicName);
                if (!(list.contains(url)))
                {
                    list.add(url);
                }
            } else
            {
                list.add(url);
            }
            topicHashMap.put(topicName, list);

        }//if
    }//addTopic Method

    /****************************************************************************************
     *
     * METHOD: loadTopics()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used to return the hashMap of topics so that we can load it in the UserInterface to search 
     * topics and sub-topics. 
     *
     * @return HashMap
     * ***************************************************************************************/
    public static HashMap loadTopics()
    {
        return topicHashMap;
    }//en of loadTopics
    
    /****************************************************************************************
     *
     * METHOD: loadDocuments()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used to return the hashMap of documents and their contents so that we can load it in the UserInterface to search 
     * keywords
     *
     * @return HashMap
     * ***************************************************************************************/

    public static HashMap loadDocuments()
    {
        return documentHashMap;
    } //end of loadDocuments
    
    /****************************************************************************************
     *
     * METHOD: saveAll()
     *
     * @author V. Mutangana, I. Wainaina, C. Habineza
     *
     * Purpose : This method is used serialize into objects of the content of three hash maps, one for documents, the other one for known URLs the other for the topics, 
     * and the vector for the new URLs. The objects are saved to disc as files and loaded later when the program starts again
     *
     * ***************************************************************************************/

    public static void saveAll()
    {
        Cache.writeObject(newURLs, "newURLs");
        Cache.writeObject(knownURLs, "knownURLs");
        Cache.writeObject(documentHashMap, "DocumentCache");
        Cache.writeObject(topicHashMap, "indexCache");
    }// end of saveAll
}
