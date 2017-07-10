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

import static java.util.Arrays.asList;

import java.io.File;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.model.JUnitFailure;
import com.antonjohansson.bmg.core.model.JUnitModel;

/**
 * Unit tests of {@link JUnitModule}.
 */
public class JUnitModuleTest extends Assert
{
    private final JUnitModule module = new JUnitModule();

    @Test
    public void test()
    {
        InputConfig config = new InputConfig();
        config.setRoot(new File("src/test/resources/junit-with-failures"));
        config.setJunitReportPatterns(asList("**/target/surefire-reports/TEST-*.xml"));
        config.setJunitDetailedReportURL("https://my-jenkins-instance/job/build/130/testReport/");
        config.setJunitDetailedReportForTestURL("https://my-jenkins-instance/job/build/130/testReport/[packageName]/[simpleClassName]/[testName]");

        JUnitModel actual = module.process(config);
        JUnitModel expected = new JUnitModel();
        expected.setExecutionTime(new BigDecimal("0.01"));
        expected.setNumberOfTests(5);
        expected.setNumberOfFailures(1);
        expected.setNumberOfErrors(1);
        expected.setFailures(asList(
                failure("test_something3", "0.003", "FAIL!", "java.lang.AssertionError: FAIL!\n"
                    + "\tat org.junit.Assert.fail(Assert.java:88)\n"
                    + "\tat com.some.test.MyClassTest.test_something3(MyClassTest.java:26)\n", false),
                failure("test_something4", "0.002", "ERROR!", "java.lang.RuntimeException: ERROR!\n"
                    + "\tat com.some.test.MyClassTest.test_something4(MyClassTest.java:32)\n", true)));
        expected.setDetailedReportURL("https://my-jenkins-instance/job/build/130/testReport/");
        expected.setResultsPresent(true);

        System.out.println(actual.toString().replace("\n", ""));
        System.out.println(expected.toString().replace("\n", ""));
        assertEquals(expected, actual);
    }

    private JUnitFailure failure(String testName, String executionTime, String message, String stacktrace, boolean error)
    {
        JUnitFailure failure = new JUnitFailure();
        failure.setClassName("com.some.test.MyClassTest");
        failure.setTestName(testName);
        failure.setMessage(message);
        failure.setStacktrace(stacktrace);
        failure.setExecutionTime(new BigDecimal(executionTime));
        failure.setError(error);
        failure.setDetailedReportURL("https://my-jenkins-instance/job/build/130/testReport/com.some.test/MyClassTest/" + testName);
        return failure;
    }
}
