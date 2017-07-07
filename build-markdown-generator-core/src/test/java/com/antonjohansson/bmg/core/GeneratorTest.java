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
package com.antonjohansson.bmg.core;

import static com.antonjohansson.bmg.core.model.CheckstyleSeverity.WARNING;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.antonjohansson.bmg.core.model.CheckstyleModel;
import com.antonjohansson.bmg.core.model.CheckstyleViolation;
import com.antonjohansson.bmg.core.model.JUnitFailure;
import com.antonjohansson.bmg.core.model.JUnitModel;
import com.antonjohansson.bmg.core.model.Model;

/**
 * Unit tests of {@link Generator}.
 */
public class GeneratorTest extends Assert
{
    private final Generator generator = new Generator();

    @Test
    public void test_none()
    {
        Model model = new Model();
        model.setDetailedReportURL("https://my-jenkins-instance/job/build/130/");

        String expected = file("/markdown/expected-none.md");
        String actual = generator.generate(model, null);

        assertEquals(expected, actual);
    }

    @Test
    public void test_all()
    {
        CheckstyleModel checkstyle = new CheckstyleModel();
        checkstyle.setResultsPresent(true);
        checkstyle.setDetailedReportURL("https://my-jenkins-instance/job/build/130/checkstyleResult/");
        checkstyle.setViolations(asList(
                new CheckstyleViolation("MyClass.java", 3, "Missing JavaDoc.", WARNING),
                new CheckstyleViolation("SomeOtherClass.java", 15, "Redundant newline(s).", WARNING)));

        JUnitModel junit = new JUnitModel();
        junit.setExecutionTime(new BigDecimal("0.01"));
        junit.setNumberOfTests(5);
        junit.setNumberOfFailures(1);
        junit.setNumberOfErrors(1);
        junit.setFailures(asList(
                failure("test_something3", "0.003", "FAIL!", "java.lang.AssertionError: FAIL!\n"
                    + "\tat org.junit.Assert.fail(Assert.java:88)\n"
                    + "\tat com.some.test.MyClassTest.test_something3(MyClassTest.java:26)\n"),
                failure("test_something4", "0.002", "ERROR!", "java.lang.RuntimeException: ERROR!\n"
                    + "\tat com.some.test.MyClassTest.test_something4(MyClassTest.java:32)\n")));
        junit.setDetailedReportURL("https://my-jenkins-instance/job/build/130/testReport/");
        junit.setResultsPresent(true);

        Model model = new Model();
        model.setDetailedReportURL("https://my-jenkins-instance/job/build/130/");
        model.setCheckstyle(checkstyle);
        model.setJunit(junit);

        String expected = file("/markdown/expected-all.md");
        String actual = generator.generate(model, null);

        assertEquals(expected, actual);
    }

    private JUnitFailure failure(String testName, String executionTime, String message, String stacktrace)
    {
        JUnitFailure failure = new JUnitFailure();
        failure.setClassName("com.some.test.MyClassTest");
        failure.setTestName(testName);
        failure.setMessage(message);
        failure.setStacktrace(stacktrace);
        failure.setExecutionTime(new BigDecimal(executionTime));
        failure.setDetailedReportURL("https://my-jenkins-instance/job/build/130/testReport/com.some.test/MyClassTest/" + testName + "/");
        return failure;
    }

    private String file(String file)
    {
        try (InputStream stream = GeneratorTest.class.getResourceAsStream(file))
        {
            return IOUtils.toString(stream, "UTF-8");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
