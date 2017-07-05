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

import static java.util.Collections.emptyList;

import java.io.File;
import java.util.List;

/**
 * Contains configurations for running the processor.
 */
public class InputConfig
{
    private File root;
    private List<String> checkstyleReportPatterns = emptyList();
    private List<String> junitReportPatterns = emptyList();

    public File getRoot()
    {
        return root;
    }

    public void setRoot(File root)
    {
        this.root = root;
    }

    public List<String> getCheckstyleReportPatterns()
    {
        return checkstyleReportPatterns;
    }

    public void setCheckstyleReportPatterns(List<String> checkstyleReportPatterns)
    {
        this.checkstyleReportPatterns = checkstyleReportPatterns;
    }

    public List<String> getJunitReportPatterns()
    {
        return junitReportPatterns;
    }

    public void setJunitReportPatterns(List<String> junitReportPatterns)
    {
        this.junitReportPatterns = junitReportPatterns;
    }
}
