package com.antonjohansson.brmg.core;

import com.antonjohansson.brmg.core.model.Model;
import com.antonjohansson.brmg.core.modules.CheckstyleModule;

/**
 * Processes reports and generates a {@link Model}.
 */
class Processor
{
    private CheckstyleModule checkstyle = new CheckstyleModule();

    void setCheckstyle(CheckstyleModule checkstyle)
    {
        this.checkstyle = checkstyle;
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
        model.setCheckstyle(checkstyle.process(config));
        return model;
    }
}
