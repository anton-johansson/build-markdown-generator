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
