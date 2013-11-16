package WebCrawler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author chabineza
 */

public class CrawlerPoller {

    private CrawlerEngine _crawler = null;
    private double _percent = 0;
    private int _maxDepth = 0;

    public CrawlerPoller(CrawlerEngine crawler) {
        _crawler = crawler;
        _maxDepth = _crawler.getMaxDepth();
    }

    public int getPercentDone() {
        if (_crawler == null) {
            return (0);
        }

        //return(_crawler.getPercentDone());
        return (getSimulatedPercentDone());
    }

    public int getSimulatedPercentDone() {
        int currentDepth = 0;
        double percentDepth = 0;
        double step = .25;

        if (_crawler.isDone()) {
            return 100;
        }

        if (_maxDepth == 0) {
            _maxDepth = _crawler.getMaxDepth();
            if (_maxDepth == 0) {
                return 0;
            }
        }

        // increase the granularity as the depth increases
        if (_maxDepth < 3) {
            step = 1.0;
        } else if (_maxDepth <= 10) {
            step = .75;
        } else if (_maxDepth <= 15) {
            step = .5;
        } else {
            step = .25;
        }

        currentDepth = _crawler.getCurrentDepth();
        percentDepth = ((double) currentDepth / _maxDepth) * 100;

        /*
         *  0% - 20% of the depth can take up to 60% of the estimated time
         * 20% - 40% of the depth can take up to 80% of the estimated time
         * 40% - 75% of the depth can take up to 90% of the estimated time
         * 75% - 100% of the depth can take up to 98% of the estimated time
         * The last 2% will wait until the crawler is done.
         */
        if (percentDepth <= 20.0) // up to 20% of the depth
        {
            if (_percent < 60.0) // takes up to 60% of the estimated time
            {
                _percent += step;
            }
        } else if (percentDepth <= 40.0) // up to 40% of the depth
        {
            if (_percent < 80.0) // takes up to 80% of the estimated time
            {
                _percent += step;
            }
        } else if (percentDepth <= 75.0) // up to 75% of the depth
        {
            if (_percent < 90.0) // takes up to 90% of the estimated time
            {
                _percent += step;
            }
        } else if (_percent < 97.0) {
            _percent += step;
        } else if (_percent < 98) {
            _percent += .1;
        }

        // slow it down
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        System.out.println("Depth is " + percentDepth + "% percent done is " + _percent + "%");

        return (int) _percent;
    }
}