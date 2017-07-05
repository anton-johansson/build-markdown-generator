## Build report

A more detailed report can be found [here](https://my-jenkins-instance/job/build/130/).

### JUnit

:cloud: 3/5 tests passed, over 0.01 seconds. A more detailed report can be found [here](https://my-jenkins-instance/job/build/130/testReport/).

| Test | Message | Time |
| ---- | ------- | ---- |
| [`com.some.test.MyClassTest.test_something3`](https://my-jenkins-instance/job/build/130/testReport/com.some.test/MyClassTest/test_something3/) | FAIL! | 0.003 |
| [`com.some.test.MyClassTest.test_something4`](https://my-jenkins-instance/job/build/130/testReport/com.some.test/MyClassTest/test_something4/) | ERROR! | 0.002 |

### Checkstyle

:warning: 2 violations found. A more detailed report can be found [here](https://my-jenkins-instance/job/build/130/checkstyleResult/).

| Class | Line | Message |
| ----- | ---- | ------- |
| MyClass.java | 3 | Missing JavaDoc. |
| SomeOtherClass.java | 15 | Redundant newline(s). |
