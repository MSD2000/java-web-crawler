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
 * The ZaplataBg class implements the ICrawler interface and provides the specific web crawling logic
 * for the www.zaplata.bg website. It extracts job information, such as job title, salary, employer name, and more.
 */
@Getter
public class ZaplataBg implements ICrawler {

    /**
     * List to store the extracted JobEntity objects during crawling.
     */
    private final List<JobEntity> jobs = new ArrayList<>();

    /**
     * Performs web crawling on the www.zaplata.bg website and extracts job-related information.
     */
    @Override
    public void crawl() {

        // Specify URL to be crawled
        String url = "https://www.zaplata.bg/search/?go=";

        try {

            // Start a new user agent
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).get();

            // Extract all elements by tag "item"
            Elements items = document.getElementsByTag("item");

            // For each extracted element
            for (Element item : items) {

                // Extract job title
                String jobTitle = item.select("div.title a").html();

                // Extract job link
                String jobLink = item.select("div.title a").attr("href");

                // Get salary element
                Elements salaryElement = item.select("div.salary strong");

                // Store salary
                String salary = "Не е посочена";

                // If salary element exists
                if (!salaryElement.isEmpty())
                    // Extract formatted salary
                    salary = Objects.requireNonNull(salaryElement.first()).html() + " - " + Objects.requireNonNull(salaryElement.last()).html();

                // Extract employer name
                String employerName = item.select("div.company a").html();

                // Extract employer link
                String employerLink = item.select("div.company a").attr("href");

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
            JsonFileUtility.SaveJsonFile("ZaplataBg", fileContent);

        } catch (IOException e) {
            System.out.println("The website zaplata.bg is currently unavailable!");
        }
    }
}