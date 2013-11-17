package WebCrawler;

/*
 * The is the main class of the project. 
 * and open the template in the editor.
 */
public class Main extends Thread
{

    /**
     * the main sub routing call the user interface and then proceed with
     *
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        UserInterface cv = new UserInterface();
        cv.setVisible(true);
        CrawlerEngine wc = new CrawlerEngine();
        wc.Crawl();
    }

}
