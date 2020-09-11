package com.alc.leaderboard.containers;

public class Submission {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String linkToProject;

    public Submission(String firstName, String lastName, String emailAddress, String linkToProject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.linkToProject = linkToProject;
    }
}
