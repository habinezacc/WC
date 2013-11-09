/*
 * To change this template, choose Tools | Templates
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
