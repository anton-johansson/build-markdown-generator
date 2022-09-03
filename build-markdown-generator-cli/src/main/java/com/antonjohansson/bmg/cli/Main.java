package com.antonjohansson.bmg.cli;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.OutputConfig;
import com.antonjohansson.bmg.core.Runner;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Arturo Volpe
 * @since 2022-09-02
 */
public class Main {

    public static void main(String[] args) throws ParseException {

        Options options = new Options();

        options.addOption("root", true, "Root folder");

        options.addOption("detailedReportURL", true, "URL of the detailed report (HTML)");

        options.addOption("checkstyleReportPatterns", true, "List of pattern for checkstyle reports");
        options.addOption("checkstyleDetailedReportURL", true, "URL of the detailed report for checkstyle");

        options.addOption("junitReportPatterns", true, "List of patterns for junit reports");
        options.addOption("junitDetailedReportURL", true, "URL of the detailed report for junit");
        options.addOption("junitDetailedReportForTestURL", true, "URL for the detailed report for a single junit test url");

        options.addOption("outputFile", true, "Path of the result, default ./build-markdown.md");

        options.addOption("coberturaCoverageReport", true, "Path to the cobertura report");
        options.addOption("coberturaLineThreshold", true, "Line threshold for cobertura, default 0");
        options.addOption("coberturaBranchThreshold", true, "Branch threshold for cobertura, default 0");
        options.addOption("coberturaDetailedReportURL", true, "URL of the detailed report of cobertura");

        options.addOption("help", false, "Print the help");
        options.addOption("template", true, "The output template");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (cmd.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar build-markdown-generator-cli.jar", options);
            return;
        }

        InputConfig input = new InputConfig();

        input.setRoot(new File(cmd.getOptionValue("root", ".")));
        input.setDetailedReportURL(cmd.getOptionValue("detailedReportURL"));

        input.setCheckstyleReportPatterns(toList(cmd, "checkstyleReportPatterns"));
        input.setCheckstyleDetailedReportURL(cmd.getOptionValue("checkstyleDetailedReportURL"));

        input.setJunitReportPatterns(toList(cmd, "junitReportPatterns"));
        input.setJunitDetailedReportURL(cmd.getOptionValue("junitDetailedReportURL"));
        input.setJunitDetailedReportForTestURL(cmd.getOptionValue("junitDetailedReportForTestURL"));

        input.setCoberturaCoverageReport(cmd.getOptionValue("coberturaCoverageReport"));
        input.setCoberturaLineThreshold(new BigDecimal(cmd.getOptionValue("coberturaLineThreshold", "0")));
        input.setCoberturaBranchThreshold(new BigDecimal(cmd.getOptionValue("coberturaBranchThreshold", "0")));
        input.setCoberturaDetailedReportURL(cmd.getOptionValue("coberturaDetailedReportURL"));

        OutputConfig output = new OutputConfig();
        output.setTemplate(getContent(cmd.getOptionValue("template")));


        Runner runner = new Runner();
        System.out.println("Generating markdown...");

        String markdown = runner.run(input, output);

        System.out.println("Successfully generated markdown");
        System.out.println(markdown);

        try {
            FileUtils.write(Paths.get(cmd.getOptionValue("outputFile", "./build-markdown.md")).toFile(), markdown, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Could not write the generated markdown to the outputFile", e);
        }
    }

    private static List<String> toList(CommandLine cmd, String checkstyleReportPatterns) {
        String[] options = cmd.getOptionValues(checkstyleReportPatterns);
        if (options == null || options.length == 0) return Collections.emptyList();
        return Arrays.asList(options);
    }

    private static String getContent(String template) {

        if (isBlank(template)) return null;
        try {
            return IOUtils.toString(Paths.get(template).toUri(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Can't read template: " + template, e);
        }
    }
}
