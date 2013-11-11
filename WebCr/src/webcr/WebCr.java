/*
 * The is the main class of the project. 
 * and open the template in the editor.
 */
package webcr;

import crawler.CrawlerEngine;
import gui.Canvas;
/**
 *
 * @author chabineza
 */
public class WebCr {
    /**
     * the main subrouting call the user interface and then proceed with 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Canvas cv = new Canvas();
        cv.setVisible(true);
        if (args == null) {
        }
        CrawlerEngine wc = new CrawlerEngine();
        wc.Crawl();
    }

    
}
