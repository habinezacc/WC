package WebCrawler;

/*******************************************************************************
 * Main class is used to display the results of search of the crawler engine
 *
 * @see UserInterface
 * @see CrawlerEngine
 ********************************************************************************
 */
public class Main extends Thread
{

    /**
     * ****************************************************************************8
     * Main method is used to display the results of search by calling user
     * interface and crawler engine
     *
     * @param args the command line arguments
     ****************************************************************************
     */
    public static void main(String args[])
    {
        UserInterface ui = new UserInterface();
        ui.setVisible(true);
        CrawlerEngine wc = new CrawlerEngine();
        wc.Crawl();
    }

}
