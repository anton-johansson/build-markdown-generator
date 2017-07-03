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

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Defines a single violation.
 */
public class CheckstyleViolation
{
    private String className = "";
    private int line;
    private String message = "";
    private CheckstyleSeverity severity;

    /**
     * Creates a new, empty violation.
     */
    public CheckstyleViolation()
    {
    }

    /**
     * Creates a new violation with the given data.
     */
    public CheckstyleViolation(String className, int line, String message, CheckstyleSeverity severity)
    {
        this.className = className;
        this.line = line;
        this.message = message;
        this.severity = severity;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public int getLine()
    {
        return line;
    }

    public void setLine(int line)
    {
        this.line = line;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public CheckstyleSeverity getSeverity()
    {
        return severity;
    }

    public void setSeverity(CheckstyleSeverity severity)
    {
        this.severity = severity;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(className)
                .append(line)
                .append(message)
                .append(severity)
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

        CheckstyleViolation that = (CheckstyleViolation) obj;
        return new EqualsBuilder()
                .append(this.className, that.className)
                .append(this.line, that.line)
                .append(this.message, that.message)
                .append(this.severity, that.severity)
                .isEquals();
    }

    @Override
    public String toString()
    {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
