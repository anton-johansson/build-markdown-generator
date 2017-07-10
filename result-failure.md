## [Build report](https://my-jenkins-instance/job/build/130/)

### [JUnit](https://my-jenkins-instance/job/build/130/testReport/)

:cloud: 3/5 tests passed, over 0.01 seconds.

| Test | Message | Time |
| ---- | ------- | ---- |
| [`com.some.test.MyClassTest.test_something3`](https://my-jenkins-instance/job/build/130/junitResult/com.some.test/MyTestClass/test_something3) | Failure! | 0.003 |
| [`com.some.test.MyClassTest.test_something4`](https://my-jenkins-instance/job/build/130/junitResult/com.some.test/MyTestClass/test_something4) | Error! | 0.002 |

### [Checkstyle](https://my-jenkins-instance/job/build/130/checkstyleResult/)

:warning: 2 violations found.

| Class | Line | Message |
| ----- | ---- | ------- |
| MyClass.java | 4 | Missing JavaDoc. |
| SomeOtherClass.java | 15 | Redundant newline(s). |

### [Coverage](https://my-jenkins-instance/job/build/130/cobertura/)

* :boom: `79.69%` of all lines covered (`51/64`)
* :boom: `75%` of all branches covered (`12/16`)
