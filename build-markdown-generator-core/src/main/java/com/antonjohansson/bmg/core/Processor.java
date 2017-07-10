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

import com.antonjohansson.bmg.core.model.Model;
import com.antonjohansson.bmg.core.modules.CheckstyleModule;
import com.antonjohansson.bmg.core.modules.CoberturaModule;
import com.antonjohansson.bmg.core.modules.JUnitModule;

/**
 * Processes reports and generates a {@link Model}.
 */
class Processor
{
    private CheckstyleModule checkstyle = new CheckstyleModule();
    private JUnitModule junit = new JUnitModule();
    private CoberturaModule cobertura = new CoberturaModule();

    void setCheckstyle(CheckstyleModule checkstyle)
    {
        this.checkstyle = checkstyle;
    }

    void setJunit(JUnitModule junit)
    {
        this.junit = junit;
    }

    void setCobertura(CoberturaModule cobertura)
    {
        this.cobertura = cobertura;
    }

    /**
     * Processes reports using the given configuration.
     *
     * @param config The configuration to use.
     * @return Returns the generated model.
     */
    Model process(InputConfig config)
    {
        Model model = new Model();
        model.setDetailedReportURL(config.getDetailedReportURL());
        model.setCheckstyle(checkstyle.process(config));
        model.setJunit(junit.process(config));
        model.setCobertura(cobertura.process(config));
        return model;
    }
}
