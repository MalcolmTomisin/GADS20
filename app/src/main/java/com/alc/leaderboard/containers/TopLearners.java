package com.alc.leaderboard.containers;

public class TopLearners {
    private String name;
    private int score;
    private String country;

    public TopLearners(String name, int score, String country) {
        this.name = name;
        this.score = score;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
