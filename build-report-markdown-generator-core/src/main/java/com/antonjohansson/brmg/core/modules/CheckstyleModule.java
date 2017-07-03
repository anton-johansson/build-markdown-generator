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

import com.antonjohansson.brmg.core.InputConfig;
import com.antonjohansson.brmg.core.model.CheckstyleModel;

/**
 * Processes checkstyle violations.
 */
public class CheckstyleModule
{
    /**
     * Processes checkstyle violations.
     *
     * @param config The configuration to use.
     * @return Returns the model.
     */
    public CheckstyleModel process(InputConfig config)
    {
        return new CheckstyleModel();
    }
}
