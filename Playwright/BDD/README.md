### _BDD._

-Install Playwright browsers
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

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

- Cucumber reports are automatically generated at /target/cucumber-reports.
- Change tags in MainRunnerTest to run scenarios with tags.

- Allure reports are generated at allure-results folder. Run the command below to see them. (Allure is a command line tool that need to be installed)
```
allure serve
```