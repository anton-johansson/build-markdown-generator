/**
 * Copyright 2017 Anton Johansson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.antonjohansson.bmg.core.model;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Defines a model of JUnit reports.
 */
public class JUnitModel
{
    private boolean resultsPresent;
    private String detailedReportURL = "";
    private BigDecimal executionTime = BigDecimal.ZERO;
    private int numberOfTests;
    private int numberOfFailures;
    private int numberOfErrors;
    private List<JUnitFailure> failures = emptyList();

    public boolean isResultsPresent()
    {
        return resultsPresent;
    }

    public void setResultsPresent(boolean resultsPresent)
    {
        this.resultsPresent = resultsPresent;
    }

    public String getDetailedReportURL()
    {
        return detailedReportURL;
    }

    public void setDetailedReportURL(String detailedReportURL)
    {
        this.detailedReportURL = detailedReportURL;
    }

    public BigDecimal getExecutionTime()
    {
        return executionTime;
    }

    public void setExecutionTime(BigDecimal executionTime)
    {
        this.executionTime = executionTime;
    }

    public int getNumberOfTests()
    {
        return numberOfTests;
    }

    public void setNumberOfTests(int numberOfTests)
    {
        this.numberOfTests = numberOfTests;
    }

    public int getNumberOfFailures()
    {
        return numberOfFailures;
    }

    public void setNumberOfFailures(int numberOfFailures)
    {
        this.numberOfFailures = numberOfFailures;
    }

    public int getNumberOfErrors()
    {
        return numberOfErrors;
    }

    public void setNumberOfErrors(int numberOfErrors)
    {
        this.numberOfErrors = numberOfErrors;
    }

    public List<JUnitFailure> getFailures()
    {
        return failures;
    }

    public void setFailures(List<JUnitFailure> failures)
    {
        this.failures = failures;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(resultsPresent)
                .append(detailedReportURL)
                .append(executionTime)
                .append(numberOfTests)
                .append(numberOfFailures)
                .append(numberOfErrors)
                .append(failures)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || obj.getClass() != getClass())
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }

        JUnitModel that = (JUnitModel) obj;
        return new EqualsBuilder()
                .append(this.resultsPresent, that.resultsPresent)
                .append(this.detailedReportURL, that.detailedReportURL)
                .append(this.executionTime, that.executionTime)
                .append(this.numberOfTests, that.numberOfTests)
                .append(this.numberOfFailures, that.numberOfFailures)
                .append(this.numberOfErrors, that.numberOfErrors)
                .append(this.failures, that.failures)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
