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

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Provides a model for Cobertura code coverage.
 */
public class CoberturaModel
{
    private boolean resultsPresent;
    private String detailedReportURL = "";
    private BigDecimal lineCoverage = BigDecimal.ZERO;
    private int linesCovered;
    private int linesTotal;
    private boolean linesPassedThreshold;
    private BigDecimal branchCoverage = BigDecimal.ZERO;
    private int branchesCovered;
    private int branchesTotal;
    private boolean branchesPassedThreshold;

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

    public BigDecimal getLineCoverage()
    {
        return lineCoverage;
    }

    public void setLineCoverage(BigDecimal lineCoverage)
    {
        this.lineCoverage = lineCoverage;
    }

    public int getLinesCovered()
    {
        return linesCovered;
    }

    public void setLinesCovered(int linesCovered)
    {
        this.linesCovered = linesCovered;
    }

    public int getLinesTotal()
    {
        return linesTotal;
    }

    public void setLinesTotal(int linesTotal)
    {
        this.linesTotal = linesTotal;
    }

    public boolean isLinesPassedThreshold()
    {
        return linesPassedThreshold;
    }

    public void setLinesPassedThreshold(boolean linesPassedThreshold)
    {
        this.linesPassedThreshold = linesPassedThreshold;
    }

    public BigDecimal getBranchCoverage()
    {
        return branchCoverage;
    }

    public void setBranchCoverage(BigDecimal branchCoverage)
    {
        this.branchCoverage = branchCoverage;
    }

    public int getBranchesCovered()
    {
        return branchesCovered;
    }

    public void setBranchesCovered(int branchesCovered)
    {
        this.branchesCovered = branchesCovered;
    }

    public int getBranchesTotal()
    {
        return branchesTotal;
    }

    public void setBranchesTotal(int branchesTotal)
    {
        this.branchesTotal = branchesTotal;
    }

    public boolean isBranchesPassedThreshold()
    {
        return branchesPassedThreshold;
    }

    public void setBranchesPassedThreshold(boolean branchesPassedThreshold)
    {
        this.branchesPassedThreshold = branchesPassedThreshold;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(resultsPresent)
                .append(detailedReportURL)
                .append(lineCoverage)
                .append(linesCovered)
                .append(linesTotal)
                .append(linesPassedThreshold)
                .append(branchCoverage)
                .append(branchesCovered)
                .append(branchesTotal)
                .append(branchesPassedThreshold)
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

        CoberturaModel that = (CoberturaModel) obj;
        return new EqualsBuilder()
                .append(this.resultsPresent, that.resultsPresent)
                .append(this.detailedReportURL, that.detailedReportURL)
                .append(this.lineCoverage, that.lineCoverage)
                .append(this.linesCovered, that.linesCovered)
                .append(this.linesTotal, that.linesTotal)
                .append(this.linesPassedThreshold, that.linesPassedThreshold)
                .append(this.branchCoverage, that.branchCoverage)
                .append(this.branchesCovered, that.branchesCovered)
                .append(this.branchesTotal, that.branchesTotal)
                .append(this.branchesPassedThreshold, that.branchesPassedThreshold)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
