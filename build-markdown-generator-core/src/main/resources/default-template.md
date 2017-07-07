## Build report
<#if detailedReportURL?has_content>

A more detailed report can be found [here](${detailedReportURL}).
</#if>
<#if junit.resultsPresent>

### JUnit

:cloud: ${junit.numberOfTests - junit.numberOfFailures - junit.numberOfErrors}/${junit.numberOfTests} tests passed, over ${junit.executionTime} seconds.<#if junit.detailedReportURL?has_content> A more detailed report can be found [here](${junit.detailedReportURL}).</#if>

| Test | Message | Time |
| ---- | ------- | ---- |
<#list junit.failures as failure>
| <#if failure.detailedReportURL?has_content>[</#if>`${failure.className}.${failure.testName}`<#if failure.detailedReportURL?has_content>](${failure.detailedReportURL})</#if> | ${failure.message} | ${failure.executionTime} |
</#list>
</#if>
<#if checkstyle.resultsPresent>

### Checkstyle

:warning: ${checkstyle.numberOfViolations} violations found.<#if checkstyle.detailedReportURL?has_content> A more detailed report can be found [here](${checkstyle.detailedReportURL}).</#if>

| Class | Line | Message |
| ----- | ---- | ------- |
<#list checkstyle.violations as violation>
| ${violation.className} | ${violation.line} | ${violation.message} |
</#list>
</#if>
<#if !resultsPresent>

No generated reports were found!
</#if>