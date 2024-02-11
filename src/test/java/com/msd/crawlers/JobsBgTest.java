package com.msd.crawlers;

import com.msd.entities.JobEntity;
import com.msd.utilities.JsonFileUtility;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobsBgTest {

    @Test
    public void testJobsBgCrawl() {
        // Create a JobsBg instance
        JobsBg jobsBg = new JobsBg();

        // Call the crawl method
        assertDoesNotThrow(jobsBg::crawl);

        // Check that the jobs list is not empty
        List<JobEntity> jobs = jobsBg.getJobs();
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());

        // Additional assertions based on the expected behavior of JobsBg class
        // For example, you can check specific properties of the extracted job entities
    }

    @Test
    public void testJobsBgJsonFileGeneration() {
        // Create a JobsBg instance
        JobsBg jobsBg = new JobsBg();

        // Call the crawl method
        assertDoesNotThrow(jobsBg::crawl);

        // Check that the jobs list is not empty
        List<JobEntity> jobs = jobsBg.getJobs();
        assertNotNull(jobs);
        assertFalse(jobs.isEmpty());

        // Convert jobs list to JSON format
        String jsonContent = JsonFileUtility.ConvertToJson(jobs);

        // Check that the generated JSON content is not empty
        assertNotNull(jsonContent);
        assertFalse(jsonContent.isEmpty());

        // Attempt to save the JSON content to a file
        assertDoesNotThrow(() -> JsonFileUtility.SaveJsonFile("TestJobsBg", jsonContent));
    }

}