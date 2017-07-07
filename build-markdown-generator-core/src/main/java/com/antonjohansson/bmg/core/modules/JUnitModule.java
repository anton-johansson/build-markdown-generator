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
package com.antonjohansson.bmg.core.modules;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlTransient;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.common.FileVisitor;
import com.antonjohansson.bmg.core.model.JUnitFailure;
import com.antonjohansson.bmg.core.model.JUnitModel;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

/**
 * Processes JUnit reports.
 */
public class JUnitModule
{
    private static final XmlMapper MAPPER = new XmlMapper();

    static
    {
        MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        // TODO: Add when Jackson 2.9.0 is out. For now, the test data is incorrect: TEST-com.some.test.MyClassTest.xml, <skipped><a></a></skipped> should be <skipped/>
        // MAPPER.configure(FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL, true);
    }

    /**
     * Processes JUnit reports.
     *
     * @param config The configuration to use.
     * @return Returns the model.
     */
    public JUnitModel process(InputConfig config)
    {
        JUnitModel model = new JUnitModel();
        if (config.getJunitReportPatterns().isEmpty())
        {
            return model;
        }

        FileVisitor visitor = new FileVisitor(config.getJunitReportPatterns());
        visitor.visit(config.getRoot());
        List<File> foundFiles = visitor.getFoundFiles();

        BigDecimal executionTime = BigDecimal.ZERO;
        int numberOfTests = 0;
        int numberOfFailures = 0;
        int numberOfErrors = 0;
        List<JUnitFailure> failures = new ArrayList<>();

        for (File resultFile : foundFiles)
        {
            try
            {
                TestSuite result = MAPPER.readValue(resultFile, TestSuite.class);

                executionTime = executionTime.add(result.time);
                numberOfTests += result.getTests() - result.getSkipped();
                numberOfFailures += result.getFailures();
                numberOfErrors += result.getErrors();

                result.getTestcases()
                        .stream()
                        .filter(TestCase::isFailure)
                        .map(testcase ->
                        {
                            JUnitFailure failure = new JUnitFailure();
                            failure.setClassName(testcase.getClassname());
                            failure.setTestName(testcase.getName());
                            failure.setMessage(testcase.getMessage());
                            failure.setStacktrace(testcase.getStacktrace());
                            failure.setExecutionTime(testcase.getTime());
                            String detailedReportURL = getDetailedReportURL(config, failure);
                            failure.setDetailedReportURL(detailedReportURL);
                            return failure;
                        })
                        .forEach(failures::add);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        model.setExecutionTime(executionTime.stripTrailingZeros());
        model.setNumberOfTests(numberOfTests);
        model.setNumberOfFailures(numberOfFailures);
        model.setNumberOfErrors(numberOfErrors);
        model.setFailures(unmodifiableList(failures));
        model.setDetailedReportURL(config.getJunitDetailedReportURL());
        model.setResultsPresent(true);
        return model;
    }

    private String getDetailedReportURL(InputConfig config, JUnitFailure failure)
    {
        if (isBlank(config.getJunitDetailedReportForTestURL()))
        {
            return "";
        }

        return config.getJunitDetailedReportForTestURL()
                .replace("[className]", failure.getClassName())
                .replace("[packageName]", failure.getPackageName())
                .replace("[simpleClassName]", failure.getSimpleClassName())
                .replace("[testName]", failure.getTestName());
    }

    /**
     * Defines a result.
     */
    public static class TestSuite
    {
        private String name = "";
        private BigDecimal time = BigDecimal.ZERO;
        private int tests;
        private int errors;
        private int skipped;
        private int failures;
        private List<TestCase> testcases = emptyList();

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public BigDecimal getTime()
        {
            return time;
        }

        public void setTime(BigDecimal time)
        {
            this.time = time;
        }

        public int getTests()
        {
            return tests;
        }

        public void setTests(int tests)
        {
            this.tests = tests;
        }

        public int getErrors()
        {
            return errors;
        }

        public void setErrors(int errors)
        {
            this.errors = errors;
        }

        public int getSkipped()
        {
            return skipped;
        }

        public void setSkipped(int skipped)
        {
            this.skipped = skipped;
        }

        public int getFailures()
        {
            return failures;
        }

        public void setFailures(int failures)
        {
            this.failures = failures;
        }

        @JacksonXmlProperty(localName = "testcase")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<TestCase> getTestcases()
        {
            return testcases;
        }

        public void setTestcases(List<TestCase> testcases)
        {
            this.testcases = testcases;
        }
    }

    /**
     * Defines a single test.
     */
    public static class TestCase
    {
        private String name = "";
        private String classname = "";
        private BigDecimal time = BigDecimal.ZERO;
        private TestFailure failure;
        private TestError error;
        private TestSkipped skipped;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getClassname()
        {
            return classname;
        }

        public void setClassname(String classname)
        {
            this.classname = classname;
        }

        public BigDecimal getTime()
        {
            return time;
        }

        public void setTime(BigDecimal time)
        {
            this.time = time;
        }

        public TestFailure getFailure()
        {
            return failure;
        }

        public void setFailure(TestFailure failure)
        {
            this.failure = failure;
        }

        public TestError getError()
        {
            return error;
        }

        public void setError(TestError error)
        {
            this.error = error;
        }

        public TestSkipped getSkipped()
        {
            return skipped;
        }

        public void setSkipped(TestSkipped skipped)
        {
            this.skipped = skipped;
        }

        @XmlTransient
        public boolean isFailure()
        {
            return failure != null
                || error != null;
        }

        @XmlTransient
        public String getMessage()
        {
            return Optional.ofNullable(failure).map(TestFailure::getMessage).orElseGet(() -> error.getMessage());
        }

        @XmlTransient
        public String getStacktrace()
        {
            return Optional.ofNullable(failure).map(TestFailure::getStacktrace).orElseGet(() -> error.getStacktrace());
        }
    }

    /**
     * Defines a failure.
     */
    public static class TestFailure
    {
        private String message = "";
        private String type = "";
        private String stacktrace = "";

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        @JacksonXmlText
        public String getStacktrace()
        {
            return stacktrace;
        }

        public void setStacktrace(String stacktrace)
        {
            this.stacktrace = stacktrace;
        }
    }

    /**
     * Defines an error.
     */
    public static class TestError
    {
        private String message = "";
        private String type = "";
        private String stacktrace = "";

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public String getType()
        {
            return type;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        @JacksonXmlText
        public String getStacktrace()
        {
            return stacktrace;
        }

        public void setStacktrace(String stacktrace)
        {
            this.stacktrace = stacktrace;
        }
    }

    /**
     * Defines a skipped test.
     */
    public static class TestSkipped
    {
    }
}
