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

import static java.util.Collections.emptyList;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * Contains configurations for running the processor.
 */
public class InputConfig
{
    private File root;
    private String detailedReportURL = "";
    private List<String> checkstyleReportPatterns = emptyList();
    private String checkstyleDetailedReportURL = "";
    private List<String> junitReportPatterns = emptyList();
    private String junitDetailedReportURL = "";
    private String junitDetailedReportForTestURL = "";
    private String coberturaCoverageReport = "";
    private BigDecimal coberturaLineThreshold = BigDecimal.ZERO;
    private BigDecimal coberturaBranchThreshold = BigDecimal.ZERO;
    private String coberturaDetailedReportURL = "";

    public File getRoot()
    {
        return root;
    }

    public void setRoot(File root)
    {
        this.root = root;
    }

    public String getDetailedReportURL()
    {
        return detailedReportURL;
    }

    public void setDetailedReportURL(String detailedReportURL)
    {
        this.detailedReportURL = detailedReportURL;
    }

    public List<String> getCheckstyleReportPatterns()
    {
        return checkstyleReportPatterns;
    }

    public void setCheckstyleReportPatterns(List<String> checkstyleReportPatterns)
    {
        this.checkstyleReportPatterns = checkstyleReportPatterns;
    }

    public String getCheckstyleDetailedReportURL()
    {
        return checkstyleDetailedReportURL;
    }

    public void setCheckstyleDetailedReportURL(String checkstyleDetailedReportURL)
    {
        this.checkstyleDetailedReportURL = checkstyleDetailedReportURL;
    }

    public List<String> getJunitReportPatterns()
    {
        return junitReportPatterns;
    }

    public void setJunitReportPatterns(List<String> junitReportPatterns)
    {
        this.junitReportPatterns = junitReportPatterns;
    }

    public String getJunitDetailedReportURL()
    {
        return junitDetailedReportURL;
    }

    public void setJunitDetailedReportURL(String junitDetailedReportURL)
    {
        this.junitDetailedReportURL = junitDetailedReportURL;
    }

    public String getJunitDetailedReportForTestURL()
    {
        return junitDetailedReportForTestURL;
    }

    public void setJunitDetailedReportForTestURL(String junitDetailedReportForTestURL)
    {
        this.junitDetailedReportForTestURL = junitDetailedReportForTestURL;
    }

    public String getCoberturaCoverageReport()
    {
        return coberturaCoverageReport;
    }

    public void setCoberturaCoverageReport(String coberturaCoverageReport)
    {
        this.coberturaCoverageReport = coberturaCoverageReport;
    }

    public BigDecimal getCoberturaLineThreshold()
    {
        return coberturaLineThreshold;
    }

    public void setCoberturaLineThreshold(BigDecimal coberturaLineThreshold)
    {
        this.coberturaLineThreshold = coberturaLineThreshold;
    }

    public BigDecimal getCoberturaBranchThreshold()
    {
        return coberturaBranchThreshold;
    }

    public void setCoberturaBranchThreshold(BigDecimal coberturaBranchThreshold)
    {
        this.coberturaBranchThreshold = coberturaBranchThreshold;
    }

    public String getCoberturaDetailedReportURL()
    {
        return coberturaDetailedReportURL;
    }

    public void setCoberturaDetailedReportURL(String coberturaDetailedReportURL)
    {
        this.coberturaDetailedReportURL = coberturaDetailedReportURL;
    }
}
