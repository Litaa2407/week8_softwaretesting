package com.week8;

public class StudentScoreValidator {

    public void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
    }

    public String classifyGrade(int score) {
        validateScore(score);

        if (score >= 85) {
            return "A";
        }
        if (score >= 75) {
            return "B";
        }
        if (score >= 60) {
            return "C";
        }
        if (score >= 50) {
            return "D";
        }
        return "E";
    }

    public boolean isPassing(int score) {
        validateScore(score);
        return score >= 60;
    }
}