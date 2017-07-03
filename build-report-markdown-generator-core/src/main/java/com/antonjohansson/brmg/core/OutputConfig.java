package com.antonjohansson.brmg.core;

import java.io.File;

/**
 * Contains information about how to store and generate the report.
 */
public class OutputConfig
{
    private File outputFile;
    private String template = "";

    public File getOutputFile()
    {
        return outputFile;
    }

    public void setOutputFile(File outputFile)
    {
        this.outputFile = outputFile;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }
}
