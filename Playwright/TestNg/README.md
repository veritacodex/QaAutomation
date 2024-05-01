### _Basic Playwright with TestNG project._

- Run all tests.
```
mvn clean verify
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

- Run a group of tests as a suite. More information at https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
```
mvn clean verify -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

- Reports are automatically generated under the folder
```
target/surefire-reports
```

