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
