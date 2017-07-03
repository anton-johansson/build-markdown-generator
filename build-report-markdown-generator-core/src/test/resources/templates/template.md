## Build report

A more detailed report can be found [here](${detailedReportURL}).

### Checkstyle

:warning: ${checkstyle.numberOfViolations} violations found. A more detailed report can be found [here](${checkstyle.detailedReportURL}).

| Class | Line | Message |
|-----|-----|-----|
<#list checkstyle.violations as violation>
| ${violation.className} | ${violation.line} | ${violation.message} |
</#list>