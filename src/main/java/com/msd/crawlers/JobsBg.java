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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The JobsBg class implements the ICrawler interface and provides the specific web crawling logic
 * for the jobs.bg website. It extracts job information, such as job title, salary, employer name, and more.
 */
@Getter
public class JobsBg implements ICrawler {

    /**
     * List to store the extracted JobEntity objects during crawling.
     */
    private final List<JobEntity> jobs = new ArrayList<>();

    /**
     * Performs web crawling on the jobs.bg website and extracts job-related information.
     */
    @Override
    public void crawl() {

        // Specify URL to be crawled
        String url = "https://www.jobs.bg/front_job_search.php";

        try {

            // Start a new user agent
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).get();

            // Extract all elements by class "scroll-item"
            Elements items = document.getElementsByClass("scroll-item");

            // For each extracted element
            for (Element item : items) {

                // Extract job title
                String jobTitle = item.select("div.left a").attr("title");

                // Extract job link
                String jobLink = item.select("div.left a").attr("href");

                // Get info card element HTML split into parts
                String[] infoElements = item.select("div.left a div.card-info").html().split("; ");

                // Store salary
                String salary = "Не е посочена";

                // Iterate through info elements
                for (String infoElement : infoElements) {

                    if (infoElement.startsWith("Заплата")) {
                        // Define the pattern to match strings between <b> and </b>
                        Pattern pattern = Pattern.compile("<b>(.*?)</b>");

                        // Create a matcher for the input string
                        Matcher matcher = pattern.matcher(infoElement);

                        // Find all occurrences
                        while (matcher.find())
                            // Extract salary
                            salary = matcher.group(1);
                    }
                }

                // Extract employer name
                String employerName = item.select("div.right a").attr("title");

                // Extract employer link
                String employerLink = item.select("div.right a").attr("href");

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
            JsonFileUtility.SaveJsonFile("JobsBg", fileContent);

        } catch (IOException e) {
            System.out.println("The website jobs.bg is currently unavailable!");
        }
    }
}