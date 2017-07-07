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

import java.io.StringWriter;

import com.antonjohansson.bmg.core.model.Model;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Generates markdown for a given model.
 */
class Generator
{
    /**
     * Generates markdown.
     *
     * @param model The model to generate for.
     * @param templateCode The template to use.
     * @return Returns the generated markdown.
     */
    String generate(Model model, String templateCode)
    {
        try
        {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            Template template = new Template("default", templateCode, cfg);
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
