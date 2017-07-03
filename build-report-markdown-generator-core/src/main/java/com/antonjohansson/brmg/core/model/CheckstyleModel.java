package com.antonjohansson.brmg.core.model;

/**
 * Provides a model for checkstyle violations.
 */
public class CheckstyleModel
{
    private boolean resultsPresent;
    private int numberOfViolations;

    public boolean isResultsPresent()
    {
        return resultsPresent;
    }

    public void setResultsPresent(boolean resultsPresent)
    {
        this.resultsPresent = resultsPresent;
    }

    public int getNumberOfViolations()
    {
        return numberOfViolations;
    }

    public void setNumberOfViolations(int numberOfViolations)
    {
        this.numberOfViolations = numberOfViolations;
    }
}
