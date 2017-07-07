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

import static com.antonjohansson.bmg.core.model.CheckstyleSeverity.fromCheckstyleName;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.common.FileVisitor;
import com.antonjohansson.bmg.core.model.CheckstyleModel;
import com.antonjohansson.bmg.core.model.CheckstyleViolation;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Processes checkstyle violations.
 */
public class CheckstyleModule
{
    private static final XmlMapper MAPPER = new XmlMapper();

    static
    {
        MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    /**
     * Processes checkstyle violations.
     *
     * @param config The configuration to use.
     * @return Returns the model.
     */
    public CheckstyleModel process(InputConfig config)
    {
        CheckstyleModel model = new CheckstyleModel();
        if (config.getCheckstyleReportPatterns().isEmpty())
        {
            return model;
        }

        List<CheckstyleViolation> violations = new ArrayList<>();

        FileVisitor visitor = new FileVisitor(config.getCheckstyleReportPatterns());
        visitor.visit(config.getRoot());
        List<File> foundFiles = visitor.getFoundFiles();

        for (File resultFile : foundFiles)
        {
            try
            {
                CheckstyleResult result = MAPPER.readValue(resultFile, CheckstyleResult.class);

                for (CheckstyleResultFile file : result.getFiles())
                {
                    for (CheckstyleResultFileError error : file.getErrors())
                    {
                        CheckstyleViolation violation = new CheckstyleViolation(file.getName(), error.getLine(), error.getMessage(), fromCheckstyleName(error.getSeverity()));
                        violations.add(violation);
                    }
                }
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }

        model.setResultsPresent(true);
        model.setViolations(unmodifiableList(violations));
        return model;
    }

    /**
     * Defines a result.
     */
    public static class CheckstyleResult
    {
        private String version = "";
        private List<CheckstyleResultFile> files = emptyList();

        public String getVersion()
        {
            return version;
        }

        public void setVersion(String version)
        {
            this.version = version;
        }

        @JacksonXmlProperty(localName = "file")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<CheckstyleResultFile> getFiles()
        {
            return files;
        }

        public void setFiles(List<CheckstyleResultFile> files)
        {
            this.files = files;
        }
    }

    /**
     * Defines a file within a result.
     */
    public static class CheckstyleResultFile
    {
        private String name = "";
        private List<CheckstyleResultFileError> errors = emptyList();

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        @JacksonXmlProperty(localName = "error")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<CheckstyleResultFileError> getErrors()
        {
            return errors;
        }

        public void setErrors(List<CheckstyleResultFileError> errors)
        {
            this.errors = errors;
        }
    }

    /**
     * Defines an error within a file.
     */
    public static class CheckstyleResultFileError
    {
        private int line;
        private String severity = "";
        private String message = "";
        private String source = "";

        public int getLine()
        {
            return line;
        }

        public void setLine(int line)
        {
            this.line = line;
        }

        public String getSeverity()
        {
            return severity;
        }

        public void setSeverity(String severity)
        {
            this.severity = severity;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public String getSource()
        {
            return source;
        }

        public void setSource(String source)
        {
            this.source = source;
        }
    }
}
