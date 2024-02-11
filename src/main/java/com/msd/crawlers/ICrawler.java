package com.msd.crawlers;

/**
 * The ICrawler interface defines the contract for web crawlers in the web crawling application.
 * Classes implementing this interface are expected to provide the logic for crawling a specific website.
 */
public interface ICrawler {

    /**
     * Default method for crawling a website. Implementing classes should override this method
     * to provide the specific crawling logic for the targeted website.
     */
    default void crawl() {
        // Implementing classes should override this method with specific crawling logic.
    }
}
