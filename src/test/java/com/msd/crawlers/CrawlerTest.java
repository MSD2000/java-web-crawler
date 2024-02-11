package com.msd.crawlers;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CrawlerTest {

    @Test
    public void testCrawlerMenuOptionOne() {

        // Simulate user input for option "1"
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(inputStream);

        // Create a Crawler instance
        Crawler crawler = new Crawler();

        // Run the crawler
        assertDoesNotThrow(() -> crawler.run());

        // Check that the crawler field is an instance of JobsBg
        assertEquals(crawler.getCrawler().getClass().getSimpleName(), "JobsBg");

        // Reset System.in
        System.setIn(System.in);
    }

    @Test
    public void testCrawlerMenuOptionFour() {

        // Simulate user input for option "4"
        InputStream inputStream = new ByteArrayInputStream("4\n".getBytes());
        System.setIn(inputStream);

        // Create a Crawler instance
        Crawler crawler = new Crawler();

        // Run the crawler
        assertDoesNotThrow(() -> crawler.run());

        // Check that the crawler field is not null
        assertEquals(crawler.getCrawler(), null);

        // Reset System.in
        System.setIn(System.in);
    }


}