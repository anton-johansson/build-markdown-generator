package com.antonjohansson.brmg.core.model;

import static java.util.Arrays.asList;

import java.util.List;

/**
 * Defines severities.
 */
public enum CheckstyleSeverity
{
    WARNING("warning"),
    ERROR("error");

    private static final List<CheckstyleSeverity> ALL = asList(CheckstyleSeverity.values());

    private final String checkstyleName;

    CheckstyleSeverity(String checkstyleName)
    {
        this.checkstyleName = checkstyleName;
    }

    /**
     * Gets a checkstyle severity by its internal name.
     */
    public static CheckstyleSeverity fromCheckstyleName(String checkstyleName)
    {
        return ALL.stream()
                .filter(severity -> severity.checkstyleName.equals(checkstyleName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("checkstyleName"));
    }
}
