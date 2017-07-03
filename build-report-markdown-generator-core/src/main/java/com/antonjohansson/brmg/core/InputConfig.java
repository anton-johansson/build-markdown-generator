package com.antonjohansson.brmg.core;

import static java.util.Collections.emptyList;

import java.util.List;

/**
 * Contains configurations for running the processor.
 */
public class InputConfig
{
    private List<String> checkstyleReportPatterns = emptyList();

    public List<String> getCheckstyleReportPatterns()
    {
        return checkstyleReportPatterns;
    }

    public void setCheckstyleReportPatterns(List<String> checkstyleReportPatterns)
    {
        this.checkstyleReportPatterns = checkstyleReportPatterns;
    }
}
