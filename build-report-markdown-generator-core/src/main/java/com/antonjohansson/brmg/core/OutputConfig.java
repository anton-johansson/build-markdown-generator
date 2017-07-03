package com.antonjohansson.brmg.core;

import java.io.File;

/**
 * Contains information about how to store and generate the report.
 */
public class OutputConfig
{
    private File outputFile;

    public File getOutputFile()
    {
        return outputFile;
    }

    public void setOutputFile(File outputFile)
    {
        this.outputFile = outputFile;
    }
}
