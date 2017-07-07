package com.antonjohansson.brmg.jenkins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jenkinsci.plugins.workflow.actions.LogAction;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.graph.FlowGraphWalker;
import org.jenkinsci.plugins.workflow.graph.FlowNode;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.BuildWatcher;
import org.jvnet.hudson.test.JenkinsRule;

/**
 * Unit tests of {@link GenerateBuildMarkdownStep}.
 */
public class GenerateBuildMarkdownStepTest
{
    @ClassRule
    public static BuildWatcher buildWatcher = new BuildWatcher();

    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void test() throws Exception
    {
        WorkflowJob p = r.jenkins.createProject(WorkflowJob.class, "p");
        p.setDefinition(new CpsFlowDefinition("generateBuildMarkdown 'hello there'", true));
        WorkflowRun b = r.assertBuildStatusSuccess(p.scheduleBuild2(0));
        List<LogAction> logActions = new ArrayList<>();
        for (FlowNode n : new FlowGraphWalker(b.getExecution()))
        {
            LogAction la = n.getAction(LogAction.class);
            if (la != null)
            {
                logActions.add(la);
            }
        }
        assertEquals(1, logActions.size());
        StringWriter w = new StringWriter();
        logActions.get(0).getLogText().writeLogTo(0, w);
        assertEquals("hello there", w.toString().trim());
        Matcher m = Pattern.compile("hello there").matcher(JenkinsRule.getLog(b));
        assertTrue("message printed once", m.find());
        assertFalse("message not printed twice", m.find());
    }
}
