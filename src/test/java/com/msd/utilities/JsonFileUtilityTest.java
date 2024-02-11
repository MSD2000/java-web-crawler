package com.msd.utilities;

import com.msd.entities.JobEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonFileUtilityTest {

    private JobEntity jobEntity;
    private List<JobEntity> jobs = new ArrayList<>();

    @Test
    void convertToJson() {

        jobEntity = new JobEntity(
            "Software Engineer",
            "https://example.com/job",
            "50000 - 70000",
            "ABC Inc.",
            "https://example.com/company"
        );

        jobs.add(jobEntity);

        String fileContent = JsonFileUtility.ConvertToJson(jobs);

        String jsonContent = """
                           [{
                              jobTitle: "Software Engineer",
                              jobLink: "https://example.com/job",
                              salary: "50000 - 70000",
                              employerName: "ABC Inc.",
                              employerLink: "https://example.com/company"
                           }]""";

        assertEquals(fileContent, jsonContent);
    }

    @Test
    void saveJsonFile() throws IOException {

        jobEntity = new JobEntity(
                "Software Engineer",
                "https://example.com/job",
                "50000 - 70000",
                "ABC Inc.",
                "https://example.com/company"
        );

        jobs.add(jobEntity);

        String fileContent = JsonFileUtility.ConvertToJson(jobs);

        JsonFileUtility.SaveJsonFile("JobsBgTest", fileContent);

        // Search for the output directory
        // Find all files starting with JobsBgTest
        // Read the file
        // Compare file content with read file content



    }
}
