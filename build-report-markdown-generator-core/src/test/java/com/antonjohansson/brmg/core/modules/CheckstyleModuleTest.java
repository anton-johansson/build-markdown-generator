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
package com.antonjohansson.brmg.core.modules;

import static com.antonjohansson.brmg.core.model.CheckstyleSeverity.WARNING;
import static java.util.Arrays.asList;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.antonjohansson.brmg.core.InputConfig;
import com.antonjohansson.brmg.core.model.CheckstyleModel;
import com.antonjohansson.brmg.core.model.CheckstyleViolation;

/**
 * Unit tests of {@link CheckstyleModule}.
 */
public class CheckstyleModuleTest extends Assert
{
    private final CheckstyleModule module = new CheckstyleModule();

    @Test
    public void test_withViolations()
    {
        InputConfig config = new InputConfig();
        config.setRoot(new File("src/test/resources/checkstyle-with-violations"));
        config.setCheckstyleReportPatterns(asList(
                "**/target/checkstyle-result-main.xml",
                "**/target/checkstyle-result-test.xml"));

        CheckstyleModel actual = module.process(config);
        CheckstyleModel expected = new CheckstyleModel();
        expected.setResultsPresent(true);
        expected.setViolations(asList(
                new CheckstyleViolation("/some-path-to-projects/my-project/my-module1/src/main/java/com/some/test/MyClass.java", 3, "Missing a Javadoc comment.", WARNING),
                new CheckstyleViolation("/some-path-to-projects/my-project/my-module1/src/main/java/com/some/test/MyClass2.java", 3, "Missing a Javadoc comment.", WARNING),
                new CheckstyleViolation("/some-path-to-projects/my-project/my-module1/src/main/java/com/some/test/MyClass2Test.java", 3, "Missing a Javadoc comment.", WARNING)));

        System.out.println(actual);
        System.out.println(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void test_withoutViolations()
    {
        InputConfig config = new InputConfig();
        config.setRoot(new File("src/test/resources/checkstyle-without-violations"));
        config.setCheckstyleReportPatterns(asList(
                "**/target/checkstyle-result-main.xml",
                "**/target/checkstyle-result-test.xml"));

        CheckstyleModel actual = module.process(config);
        CheckstyleModel expected = new CheckstyleModel();
        expected.setResultsPresent(true);

        assertEquals(expected, actual);
    }

    @Test
    public void test_withNoConfig()
    {
        InputConfig config = new InputConfig();
        config.setRoot(new File("src/test/resources/checkstyle-with-violations"));

        CheckstyleModel expected = new CheckstyleModel();
        CheckstyleModel actual = module.process(config);

        assertEquals(expected, actual);
    }
}
