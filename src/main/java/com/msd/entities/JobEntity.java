package com.msd.entities;

import lombok.*;

/**
 * Represents a job entry from JobsBg website.
 * This class contains information such as job title, job link,
 * employer name, employer link, and employer jobs link.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    /**
     * The title of the job.
     */
    private String jobTitle = "";

    /**
     * The link to the job details.
     */
    private String jobLink = "";

    /**
     * The salary of the job.
     */
    private String salary = "";

    /**
     * The name of the employer offering the job.
     */
    private String employerName = "";

    /**
     * The link to the employer's details.
     */
    private String employerLink = "";

    /**
     * Returns a JSON-formatted string representation of the JobsBg object.
     *
     * @return A JSON-formatted string representation of the object.
     */
    @Override
    public String toString() {
        return String.format("""
                           {
                              jobTitle: "%s",
                              jobLink: "%s",
                              salary: "%s",
                              employerName: "%s",
                              employerLink: "%s"
                           },""",
                jobTitle, jobLink, salary, employerName, employerLink);
    }

}
