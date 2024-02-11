package com.msd.crawlers;

import com.msd.utilities.JsonFileUtility;
import com.msd.entities.JobEntity;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The JoobleOrg class implements the ICrawler interface and provides the specific web crawling logic
 * for the bg.jooble.org website. It extracts job information, such as job title, salary, employer name, and more.
 */
@Getter
public class JoobleOrg implements ICrawler {

    /**
     * List to store the extracted JobEntity objects during crawling.
     */
    private final List<JobEntity> jobs = new ArrayList<>();

    /**
     * Performs web crawling on the bg.jooble.org website and extracts job-related information.
     */
    @Override
    public void crawl() {

        // Specify URL to be crawled
        String url = "https://bg.jooble.org/SearchResult";

        try {

            // Start a new user agent
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).get();

            // Extract all elements by class name "ojoFrF"
            Elements items = document.getElementsByClass("ojoFrF");

            // For each extracted element
            for (Element item : items) {

                // Extract job title
                String jobTitle = item.select("div.MhjGza h2.sXM9Eq a").attr("href");

                // Extract job link
                String jobLink = item.select("div.MhjGza h2.sXM9Eq a").html();

                // Get salary element
                Elements salaryElement = item.select("div.slQ-DR p.W3cvaC");

                // Store salary
                String salary = "Не е посочена";

                // If salary element exists
                if (!salaryElement.isEmpty())
                    // Extract formatted salary
                    salary = Objects.requireNonNull(salaryElement.first()).html();

                // Extract employer name
                String employerName = item.select("div.L4BhzZ div.E6E0jY div.pXyhD4 div > p.z6WlhX").html();

                // Extract employer link
                String employerLink = "Не е посочен";

                // Add new job to jobs list
                jobs.add(
                        new JobEntity(
                                jobTitle,
                                jobLink,
                                salary,
                                employerName,
                                employerLink
                        )
                );
            }

            // Create file content -> valid JSON
            String fileContent = JsonFileUtility.ConvertToJson(jobs);

            // Save .json file
            JsonFileUtility.SaveJsonFile("JoobleOrg", fileContent);

        } catch (IOException e) {
            System.out.println("The website bg.jooble.org is currently unavailable!");
        }
    }
}