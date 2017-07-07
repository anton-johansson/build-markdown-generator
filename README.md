# Build Markdown Generator

[![Build Status](https://img.shields.io/travis/anton-johansson/build-report-markdown-generator/master.svg)](https://travis-ci.org/anton-johansson/build-report-markdown-generator)
[![License](https://img.shields.io/hexpm/l/plug.svg?maxAge=2592000)](https://raw.githubusercontent.com/anton-johansson/build-report-markdown-generator/master/LICENSE)

Generates markdown for build reports, containing for example JUnit report, code coverage report, etc. A success example result can be found [here](./result-success.md) and a failure example result can be found [here](./result-failure.md).


## Supported reports

  * Checkstyle
  * JUnit


## Generations

### Maven

```
$ mvn com.anton-johansson:build-markdown-generator-maven-plugin:1.0.0:generate \
      -DbuildMarkdownGenerator.detailedReportURL=https://my-jenkins-instance/job/build/130/
      -DbuildMarkdownGenerator.checkstyleReportPatterns=**/target/checkstyle-result.xml \
      -DbuildMarkdownGenerator.checkstyleDetailedReportURL=https://my-jenkins-instance/job/build/130/checkstyleResult/
      -DbuildMarkdownGenerator.junitReportPatterns=**/target/surefire-reports/TEST-*.xml \
      -DbuildMarkdownGenerator.junitDetailedReportURL=https://my-jenkins-instance/job/build/130/testReport/
      -DbuildMarkdownGenerator.junitDetailedReportForTestURL=https://my-jenkins-instance/job/build/130/testReport/[packageName]/[simpleClassName]/[testName]
```


## License

Apache License © [Anton Johansson](https://github.com/anton-johansson)
