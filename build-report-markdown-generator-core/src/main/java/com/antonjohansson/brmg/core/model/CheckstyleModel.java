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
package com.antonjohansson.brmg.core.model;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Provides a model for checkstyle violations.
 */
public class CheckstyleModel
{
    private boolean resultsPresent;
    private String detailedReportURL = "";
    private List<CheckstyleViolation> violations = emptyList();

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

    public List<CheckstyleViolation> getViolations()
    {
        return violations;
    }

    public void setViolations(List<CheckstyleViolation> violations)
    {
        this.violations = violations;
    }

    public int getNumberOfViolations()
    {
        return violations.size();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(resultsPresent)
                .append(detailedReportURL)
                .append(violations)
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

        CheckstyleModel that = (CheckstyleModel) obj;
        return new EqualsBuilder()
                .append(this.resultsPresent, that.resultsPresent)
                .append(this.detailedReportURL, that.detailedReportURL)
                .append(this.violations, that.violations)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
