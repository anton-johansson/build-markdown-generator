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
package com.antonjohansson.brmg.core;

import static com.antonjohansson.brmg.core.model.CheckstyleSeverity.WARNING;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.antonjohansson.brmg.core.model.CheckstyleModel;
import com.antonjohansson.brmg.core.model.CheckstyleViolation;
import com.antonjohansson.brmg.core.model.Model;

/**
 * Unit tests of {@link Generator}.
 */
public class GeneratorTest extends Assert
{
    private final Generator generator = new Generator();

    @Test
    public void test_all()
    {
        CheckstyleModel checkstyle = new CheckstyleModel();
        checkstyle.setResultsPresent(true);
        checkstyle.setDetailedReportURL("https://my-jenkins-instance/job/build/130/checkstyleResult/");
        checkstyle.setViolations(asList(
                new CheckstyleViolation("MyClass.java", 3, "Missing JavaDoc.", WARNING),
                new CheckstyleViolation("SomeOtherClass.java", 15, "Redundant newline(s).", WARNING)));

        Model model = new Model();
        model.setDetailedReportURL("https://my-jenkins-instance/job/build/130/");
        model.setCheckstyle(checkstyle);

        String expected = file("/markdown/expected-all.md");
        String actual = generator.generate(model, file("/templates/template.md"));

        assertEquals(expected, actual);
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
