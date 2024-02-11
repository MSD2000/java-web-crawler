package com.msd.crawlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * The Crawler class provides a simple command-line interface (CLI) for choosing and running web crawlers.
 * It allows the user to select a specific website to crawl or crawl multiple websites in parallel.
 */
public class Crawler {

    /**
     * The static ICrawler object representing the selected web crawler.
     */
    public static ICrawler crawler;

    /**
     * Returns the instance of the currently set crawler.
     */
    public ICrawler getCrawler() {
        return crawler;
    }

    /**
     * Runs the CLI menu for website selection and initiates the crawling process.
     */
    public void run() {

        // Create a Scanner object to read from the console
        Scanner scanner = new Scanner(System.in);

        // Show CLI menu for website selection
        System.out.println("""
                Choose which website to crawl:
                1. jobs.bg
                2. zaplata.bg
                3. jooble.org
                4. All at once (parallel)
                0. Exit
                Your choice:""");

        // Read the input from the console
        String userInput = scanner.nextLine();

        // Process the user's choice
        switch (userInput) {

            case "1": {
                crawler = new JobsBg();
                break;
            }

            case "2": {
                crawler = new ZaplataBg();
                break;
            }

            case "3": {
                crawler = new JoobleOrg();
                break;
            }

            case "4": {

                // Create new list to store all crawlers
                List<ICrawler> crawlers = new ArrayList<>();

                // Add all crawlers
                crawlers.add(new JobsBg());
                crawlers.add(new ZaplataBg());
                crawlers.add(new JoobleOrg());

                // Create list of threads
                List<Thread> threads = new ArrayList<>();

                // Iterate through all crawlers
                for (ICrawler icrawler : crawlers) {

                    // Create new thread and attach crawler process
                    Thread thread = new Thread(icrawler::crawl);

                    // Add the thread to the list
                    threads.add(thread);

                    // Start a new thread
                    thread.start();
                }

                // Wait for all threads to finish
                try {
                    for (Thread thread : threads) {
                        thread.join();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            }

            case "0": {
                exit(0);
                break;
            }

            default: {
                // Show CLI message for invalid operation
                System.out.println("Invalid operation!");
                break;
            }
        }

        // If the crawler is not empty, crawl the website
        if (crawler != null)
            crawler.crawl();
    }

}