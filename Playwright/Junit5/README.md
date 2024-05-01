### _Basic Playwright with JUnit5 project._

- Run all tests.
```
mvn clean verify
```

- Command to generate code against a specific website. Click on elements on the page to generate code.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://www.wikipedia.org"
```

- In case tracing is enabled, run the command below to see the trace.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
```

- Run a test class by name.
```
mvn verify -Dtest=FirstTest
```

- Run a single test in a class.
```
mvn verify -Dtest=FourthTest#shouldClickButton
```

- Run a group of tests as a suite. More information at https://howtodoinjava.com/junit5/junit5-test-suites-examples/
```
mvn verify -Dtest=SuiteA
```

- Run a html report with test results. More information at https://howtodoinjava.com/junit5/junit-html-report/
- The report will be in the folder /target/site
```
mvn clean surefire-report:report -Dtest=FailedTest
```

