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
package com.antonjohansson.bmg.maven;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.OutputConfig;
import com.antonjohansson.bmg.core.Runner;

/**
 * Generates markdown.
 */
@Mojo(name = "generate", aggregator = true)
public class GenerateMojo extends AbstractMojo
{
    @Parameter(property = "buildMarkdownGenerator.detailedReportURL")
    private String detailedReportURL;

    @Parameter(property = "buildMarkdownGenerator.checkstyleReportPatterns")
    private List<String> checkstyleReportPatterns;

    @Parameter(property = "buildMarkdownGenerator.checkstyleDetailedReportURL")
    private String checkstyleDetailedReportURL;

    @Parameter(property = "buildMarkdownGenerator.junitReportPatterns")
    private List<String> junitReportPatterns;

    @Parameter(property = "buildMarkdownGenerator.junitDetailedReportURL")
    private String junitDetailedReportURL;

    @Parameter(property = "buildMarkdownGenerator.junitDetailedReportForTestURL")
    private String junitDetailedReportForTestURL;

    @Parameter(property = "buildMarkdownGenerator.root", required = true, defaultValue = "${project.build.directory}")
    private File root;

    @Parameter
    private String template;

    @Parameter(property = "buildMarkdownGenerator.outputFile", required = true, defaultValue = "${project.build.directory}/build-markdown.md")
    private File outputFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException
    {
        InputConfig input = new InputConfig();
        input.setDetailedReportURL(detailedReportURL);
        input.setCheckstyleReportPatterns(checkstyleReportPatterns);
        input.setCheckstyleDetailedReportURL(checkstyleDetailedReportURL);
        input.setJunitReportPatterns(junitReportPatterns);
        input.setJunitDetailedReportURL(junitDetailedReportURL);
        input.setJunitDetailedReportForTestURL(junitDetailedReportForTestURL);
        input.setRoot(root);

        OutputConfig output = new OutputConfig();
        output.setTemplate(template);

        Runner runner = new Runner();
        getLog().info("Generating markdown...");

        String markdown = runner.run(input, output);

        getLog().info("Successfully generated markdown");
        getLog().debug(markdown);

        try
        {
            FileUtils.write(outputFile, markdown, "UTF-8");
        }
        catch (IOException e)
        {
            throw new MojoFailureException("Could not write the generated markdown to the outputFile", e);
        }
    }
}
