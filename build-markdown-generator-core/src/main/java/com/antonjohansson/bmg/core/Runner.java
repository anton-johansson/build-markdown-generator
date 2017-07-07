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

/**
 * Runs the report generator.
 */
public class Runner
{
    private Processor processor = new Processor();
    private Generator generator = new Generator();

    void setProcessor(Processor processor)
    {
        this.processor = processor;
    }

    void setGenerator(Generator generator)
    {
        this.generator = generator;
    }

    /**
     * Runs the report generator with the given configurations.
     *
     * @param inputConfig The input configurations to use.
     * @param outputConfig The output configurations to use.
     * @return Returns the generated markdown.
     */
    public String run(InputConfig inputConfig, OutputConfig outputConfig)
    {
        Model model = processor.process(inputConfig);
        String markdown = generator.generate(model, outputConfig.getTemplate());
        return markdown;
    }
}
