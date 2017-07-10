## Build report
<#if detailedReportURL?has_content>

A more detailed report can be found [here](${detailedReportURL}).
</#if>
<#if junit.resultsPresent>

### JUnit

<#if junit.numberOfFailures + junit.numberOfErrors gt 0>:cloud:<#else>:sunny:</#if> ${junit.numberOfTests - junit.numberOfFailures - junit.numberOfErrors}/${junit.numberOfTests} tests passed, over ${junit.executionTime} seconds.<#if junit.detailedReportURL?has_content> A more detailed report can be found [here](${junit.detailedReportURL}).</#if>
<#if junit.numberOfFailures + junit.numberOfErrors gt 0>

| Test | Message | Time |
| ---- | ------- | ---- |
<#list junit.failures as failure>
| <#if failure.error>:small_red_triangle:<#else>:small_blue_diamond:</#if> <#if failure.detailedReportURL?has_content>[</#if>`${failure.className}.${failure.testName}`<#if failure.detailedReportURL?has_content>](${failure.detailedReportURL})</#if> | ${failure.message} | ${failure.executionTime} |
</#list>
</#if>
</#if>
<#if checkstyle.resultsPresent>

### Checkstyle

<#if checkstyle.numberOfViolations gt 0>:warning:<#else>:white_check_mark:</#if> ${checkstyle.numberOfViolations} violations found.<#if checkstyle.detailedReportURL?has_content> A more detailed report can be found [here](${checkstyle.detailedReportURL}).</#if>
<#if checkstyle.numberOfViolations gt 0>

| Class | Line | Message |
| ----- | ---- | ------- |
<#list checkstyle.violations as violation>
| ${violation.className} | ${violation.line} | ${violation.message} |
</#list>
</#if>
</#if>
<#if !resultsPresent>

No generated reports were found!
</#if>