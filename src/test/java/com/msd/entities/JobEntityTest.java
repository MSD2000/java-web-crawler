package com.msd.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobEntityTest {

    private JobEntity jobEntity;

    @BeforeEach
    void setUp() {
        // Initialize a JobEntity instance before each test
        jobEntity = new JobEntity(
            "Software Engineer",
            "https://example.com/job",
            "50000 - 70000",
            "ABC Inc.",
            "https://example.com/company"
        );
    }

    @Test
    void testToString() {
        // Test the toString method
        assertEquals("""
                           {
                              jobTitle: "Software Engineer",
                              jobLink: "https://example.com/job",
                              salary: "50000 - 70000",
                              employerName: "ABC Inc.",
                              employerLink: "https://example.com/company"
                           },""", jobEntity.toString());
    }

    @Test
    void getJobTitle() {
        // Test the getJobTitle method
        assertEquals("Software Engineer", jobEntity.getJobTitle());
    }

    @Test
    void getJobLink() {
        // Test the getJobLink method
        assertEquals("https://example.com/job", jobEntity.getJobLink());
    }

    @Test
    void getSalary() {
        // Test the getSalary method
        assertEquals("50000 - 70000", jobEntity.getSalary());
    }

    @Test
    void getEmployerName() {
        // Test the getEmployerName method
        assertEquals("ABC Inc.", jobEntity.getEmployerName());
    }

    @Test
    void getEmployerLink() {
        // Test the getEmployerLink method
        assertEquals("https://example.com/company", jobEntity.getEmployerLink());
    }

    @Test
    void setJobTitle() {
        // Test the setJobTitle method
        jobEntity.setJobTitle("Data Scientist");
        assertEquals("Data Scientist", jobEntity.getJobTitle());
    }

    @Test
    void setJobLink() {
        // Test the setJobLink method
        jobEntity.setJobLink("https://example.com/data-scientist");
        assertEquals("https://example.com/data-scientist", jobEntity.getJobLink());
    }

    @Test
    void setSalary() {
        // Test the setSalary method
        jobEntity.setSalary("80000 - 100000");
        assertEquals("80000 - 100000", jobEntity.getSalary());
    }

    @Test
    void setEmployerName() {
        // Test the setEmployerName method
        jobEntity.setEmployerName("XYZ Corporation");
        assertEquals("XYZ Corporation", jobEntity.getEmployerName());
    }

    @Test
    void setEmployerLink() {
        // Test the setEmployerLink method
        jobEntity.setEmployerLink("https://example.com/xyz-corp");
        assertEquals("https://example.com/xyz-corp", jobEntity.getEmployerLink());
    }
}
