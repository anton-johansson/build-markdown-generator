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
import static java.math.RoundingMode.HALF_UP;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.model.CoberturaModel;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Processes Cobertura coverage reports.
 */
public class CoberturaModule
{
    private static final XmlMapper MAPPER = new XmlMapper();
    private static final BigDecimal MULTIPLIER = new BigDecimal(100);

    static
    {
        MAPPER.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Processes checkstyle violations.
     *
     * @param config The configuration to use.
     * @return Returns the model.
     */
    public CoberturaModel process(InputConfig config)
    {
        CoberturaModel model = new CoberturaModel();
        if (isBlank(config.getCoberturaCoverageReport()))
        {
            return model;
        }

        File reportFile = new File(config.getRoot(), config.getCoberturaCoverageReport());
        try
        {
            CoberturaReport report = MAPPER.readValue(reportFile, CoberturaReport.class);
            model.setLineCoverage(report.getLineRate().multiply(MULTIPLIER).setScale(2, HALF_UP).stripTrailingZeros());
            model.setLinesCovered(report.getLinesCovered());
            model.setLinesTotal(report.getLinesValid());
            model.setLinesPassedThreshold(model.getLineCoverage().compareTo(config.getCoberturaLineThreshold()) >= 0);
            model.setBranchCoverage(report.getBranchRate().multiply(MULTIPLIER).setScale(2, HALF_UP).stripTrailingZeros());
            model.setBranchesCovered(report.getBranchesCovered());
            model.setBranchesTotal(report.getBrannchesValid());
            model.setBranchesPassedThreshold(model.getBranchCoverage().compareTo(config.getCoberturaBranchThreshold()) >= 0);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        model.setResultsPresent(true);
        model.setDetailedReportURL(config.getCoberturaDetailedReportURL());
        return model;
    }

    /**
     * Defines a Cobertura report.
     */
    public static class CoberturaReport
    {
        private @JacksonXmlProperty(localName = "line-rate") BigDecimal lineRate = BigDecimal.ZERO;
        private @JacksonXmlProperty(localName = "lines-covered") int linesCovered;
        private @JacksonXmlProperty(localName = "lines-valid") int linesValid;
        private @JacksonXmlProperty(localName = "branch-rate") BigDecimal branchRate = BigDecimal.ZERO;
        private @JacksonXmlProperty(localName = "branches-covered") int branchesCovered;
        private @JacksonXmlProperty(localName = "branches-valid") int brannchesValid;

        public BigDecimal getLineRate()
        {
            return lineRate;
        }

        public void setLineRate(BigDecimal lineRate)
        {
            this.lineRate = lineRate;
        }

        public int getLinesCovered()
        {
            return linesCovered;
        }

        public void setLinesCovered(int linesCovered)
        {
            this.linesCovered = linesCovered;
        }

        public int getLinesValid()
        {
            return linesValid;
        }

        public void setLinesValid(int linesValid)
        {
            this.linesValid = linesValid;
        }

        public BigDecimal getBranchRate()
        {
            return branchRate;
        }

        public void setBranchRate(BigDecimal branchRate)
        {
            this.branchRate = branchRate;
        }

        public int getBranchesCovered()
        {
            return branchesCovered;
        }

        public void setBranchesCovered(int branchesCovered)
        {
            this.branchesCovered = branchesCovered;
        }

        public int getBrannchesValid()
        {
            return brannchesValid;
        }

        public void setBrannchesValid(int brannchesValid)
        {
            this.brannchesValid = brannchesValid;
        }
    }
}
