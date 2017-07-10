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

import java.io.File;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.antonjohansson.bmg.core.InputConfig;
import com.antonjohansson.bmg.core.model.CoberturaModel;

/**
 * Unit tests of {@link CoberturaModule}.
 */
public class CoberturaModuleTest extends Assert
{
    private final CoberturaModule cobertura = new CoberturaModule();

    @Test
    public void test()
    {
        InputConfig config = new InputConfig();
        config.setRoot(new File("src/test/resources/cobertura"));
        config.setCoberturaCoverageReport("target/site/cobertura/coverage.xml");
        config.setCoberturaLineThreshold(new BigDecimal(50));
        config.setCoberturaBranchThreshold(new BigDecimal(50));

        CoberturaModel actual = cobertura.process(config);
        CoberturaModel expected = new CoberturaModel();
        expected.setResultsPresent(true);
        expected.setLineCoverage(new BigDecimal("46.67"));
        expected.setLinesCovered(7);
        expected.setLinesTotal(15);
        expected.setBranchCoverage(new BigDecimal(50).stripTrailingZeros());
        expected.setBranchesCovered(1);
        expected.setBranchesTotal(2);
        expected.setBranchesPassedThreshold(true);

        System.out.println(actual);
        System.out.println(expected);

        assertEquals(expected, actual);
    }
}
