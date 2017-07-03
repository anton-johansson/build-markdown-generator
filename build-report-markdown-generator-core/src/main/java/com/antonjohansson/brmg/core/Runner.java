package com.antonjohansson.brmg.core;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.antonjohansson.brmg.core.model.Model;

/**
 * Runs the report generator.
 */
public class Runner
{
    private Processor processor = new Processor();
    private Generator generator = new Generator();

    void setProcessor(Processor processor)
    {
        this.processor = processor;
    }

    void setGenerator(Generator generator)
    {
        this.generator = generator;
    }

    /**
     * Runs the report generator with the given configurations.
     *
     * @param inputConfig The input configurations to use.
     * @param outputConfig The output configurations to use.
     */
    void run(InputConfig inputConfig, OutputConfig outputConfig)
    {
        Model model = processor.process(inputConfig);
        String markdown = generator.generate(model, outputConfig.getTemplate());
        try
        {
            FileUtils.write(outputConfig.getOutputFile(), markdown, "UTF-8");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
