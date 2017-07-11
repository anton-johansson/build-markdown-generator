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
package com.antonjohansson.bmg.core.model;

import static java.util.Arrays.asList;

import java.util.List;

/**
 * Defines severities.
 */
public enum CheckstyleSeverity
{
    WARNING("warning"),
    ERROR("error");

    private static final List<CheckstyleSeverity> ALL = asList(CheckstyleSeverity.values());

    private final String checkstyleName;

    CheckstyleSeverity(String checkstyleName)
    {
        this.checkstyleName = checkstyleName;
    }

    /**
     * Gets a checkstyle severity by its internal name.
     *
     * @param checkstyleName The name of the severity from Checkstyles point of view.
     * @return Returns the severity.
     */
    public static CheckstyleSeverity fromCheckstyleName(String checkstyleName)
    {
        return ALL.stream()
                .filter(severity -> severity.checkstyleName.equals(checkstyleName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("checkstyleName"));
    }
}
