package com.msd;

import com.msd.crawlers.Crawler;

/**
 * The Main class is the entry point of the application.
 * It initializes and runs the web crawler.
 */
public class Main {

    /**
     * The main method creates a Crawler object and invokes its run method to start the application.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {

        // Create a new instance of the Crawler class
        Crawler crawler = new Crawler();

        // Run the web crawler
        crawler.run();
    }

}
