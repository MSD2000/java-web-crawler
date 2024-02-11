package com.msd.utilities;

import com.msd.entities.JobEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The JsonFileUtility class provides utility methods for converting a list of JobEntity objects to JSON format
 * and saving the JSON content to a file with a dynamically generated filename.
 */
public class JsonFileUtility {

    /**
     * Converts a list of JobEntity objects to a JSON-formatted string.
     *
     * @param jobs The list of JobEntity objects to be converted.
     * @return A string representing the JSON format of the provided job list.
     */
    public static String ConvertToJson(List<JobEntity> jobs) {
        // Create new string builder for the JSON
        StringBuilder json = new StringBuilder();

        // Append all jobs in the string builder
        for (JobEntity job : jobs)
            json.append(job.toString());

        // Check if the StringBuilder has content and if the last character is a comma
        if (!json.isEmpty() && json.charAt(json.length() - 1) == ',')
            // Remove the last character (the comma)
            json.deleteCharAt(json.length() - 1);

        // Convert the StringBuilder to a simple string in valid JSON format
        return "[" + json + "]";
    }

    /**
     * Saves the provided JSON content to a file with a dynamically generated filename.
     *
     * @param fileNameBase  The base name for the output file.
     * @param fileContent   The JSON content to be saved to the file.
     * @throws IOException  If an I/O error occurs while writing to the file.
     */
    public static void SaveJsonFile(String fileNameBase, String fileContent) throws IOException {

        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        // Format output file name
        String outputFileName = fileNameBase + "_" + formattedDateTime + ".json";

        // Convert string to bytes
        byte[] jsonBytes = fileContent.getBytes();

        // Specify the file path
        Path path = Paths.get("output/" + outputFileName);

        // Write bytes to the file
        Files.write(path, jsonBytes);

        // Show message to user
        System.out.println("JSON content successfully saved to file: " + outputFileName);
    }
}