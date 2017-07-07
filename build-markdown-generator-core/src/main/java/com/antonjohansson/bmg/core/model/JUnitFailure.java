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
 * Defines a JUnit failure or error.
 */
public class JUnitFailure
{
    private String className = "";
    private String testName = "";
    private String message = "";
    private String stacktrace = "";
    private BigDecimal executionTime = BigDecimal.ZERO;
    private String detailedReportURL = "";

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getTestName()
    {
        return testName;
    }

    public void setTestName(String testName)
    {
        this.testName = testName;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getStacktrace()
    {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace)
    {
        this.stacktrace = stacktrace;
    }

    public BigDecimal getExecutionTime()
    {
        return executionTime;
    }

    public void setExecutionTime(BigDecimal executionTime)
    {
        this.executionTime = executionTime;
    }

    public String getDetailedReportURL()
    {
        return detailedReportURL;
    }

    public void setDetailedReportURL(String detailedReportURL)
    {
        this.detailedReportURL = detailedReportURL;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(className)
                .append(testName)
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

        JUnitFailure that = (JUnitFailure) obj;
        return new EqualsBuilder()
                .append(this.className, that.className)
                .append(this.testName, that.testName)
                .append(this.message, that.message)
                .append(this.stacktrace, that.stacktrace)
                .append(this.executionTime, that.executionTime)
                .append(this.detailedReportURL, that.detailedReportURL)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
