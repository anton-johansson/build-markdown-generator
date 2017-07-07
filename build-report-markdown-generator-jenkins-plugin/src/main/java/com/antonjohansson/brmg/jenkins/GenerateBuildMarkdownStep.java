package com.antonjohansson.brmg.jenkins;

import static java.util.Collections.emptySet;

import java.util.Set;

import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.jenkinsci.plugins.workflow.steps.SynchronousStepExecution;
import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;

/**
 * Generates build markdown that can be used to send to different places, such as a GitLab merge request.
 */
public class GenerateBuildMarkdownStep extends Step
{
    private final String message;

    @DataBoundConstructor
    public GenerateBuildMarkdownStep(String message)
    {
        this.message = message;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception
    {
        return new Execution(context, message);
    }

    /**
     * Executes the step.
     */
    public static class Execution extends SynchronousStepExecution<String>
    {
        private static final long serialVersionUID = 1L;

        private final transient String message;

        Execution(StepContext context, String message)
        {
            super(context);
            this.message = message;
        }

        @Override
        protected String run() throws Exception
        {
            return message;
        }
    }

    /**
     * Describes the step.
     */
    @Extension
    public static class DescriptorImpl extends StepDescriptor
    {
        @Override
        public String getFunctionName()
        {
            return "generateBuildMarkdown";
        }

        @Override
        public String getDisplayName()
        {
            return "Generate build markdown";
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext()
        {
            return emptySet();
        }
    }
}
