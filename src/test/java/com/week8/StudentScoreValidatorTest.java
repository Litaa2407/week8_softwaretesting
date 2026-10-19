package com.week8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Student score validation")
class StudentScoreValidatorTest {

    private StudentScoreValidator validator;

    @BeforeEach
    void setUp() {
        validator = new StudentScoreValidator();
    }

    @ParameterizedTest(name = "score {0} is within the valid boundary")
    @ValueSource(ints = {0, 1, 99, 100})
    @DisplayName("Accepts valid scores at and next to the range boundaries")
    void acceptsScoresAtAndNextToRangeBoundaries(int score) {
        validator.validateScore(score);
    }

    @Test
    @DisplayName("Rejects a score below the minimum")
    void rejectsScoreBelowMinimum() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> validator.validateScore(-1));
        assertEquals("Score must be between 0 and 100.", exception.getMessage());
    }

    @Test
    @DisplayName("Rejects a score above the maximum")
    void rejectsScoreAboveMaximum() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> validator.validateScore(101));
        assertEquals("Score must be between 0 and 100.", exception.getMessage());
    }

    @Test
    @DisplayName("Classifies a high score as grade A")
    void classifiesHighScoreAsA() {
        assertEquals("A", validator.classifyGrade(90));
    }

    @Test
    @DisplayName("Classifies a middle score as grade C")
    void classifiesMiddleScoreAsC() {
        assertEquals("C", validator.classifyGrade(70));
    }

    @Test
    @DisplayName("Classifies a low score as grade E")
    void classifiesLowScoreAsE() {
        assertEquals("E", validator.classifyGrade(49));
    }

    @Test
    @DisplayName("A score at the passing threshold passes")
    void scoreAtPassingThresholdPasses() {
        assertTrue(validator.isPassing(60));
    }

    @Test
    @DisplayName("A score below the passing threshold does not pass")
    void scoreBelowPassingThresholdDoesNotPass() {
        assertFalse(validator.isPassing(59));
    }
}