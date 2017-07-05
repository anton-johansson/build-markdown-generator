## Build report

A more detailed report can be found [here](${detailedReportURL}).

### JUnit

:cloud: ${junit.numberOfTests - junit.numberOfFailures - junit.numberOfErrors}/${junit.numberOfTests} tests passed, over ${junit.executionTime} seconds. A more detailed report can be found [here](${junit.detailedReportURL}).

| Test | Message | Time |
| ---- | ------- | ---- |
<#list junit.failures as failure>
| [`${failure.className}.${failure.testName}`](${failure.detailedReportURL}) | ${failure.message} | ${failure.executionTime} |
</#list>

### Checkstyle

:warning: ${checkstyle.numberOfViolations} violations found. A more detailed report can be found [here](${checkstyle.detailedReportURL}).

| Class | Line | Message |
| ----- | ---- | ------- |
<#list checkstyle.violations as violation>
| ${violation.className} | ${violation.line} | ${violation.message} |
</#list>