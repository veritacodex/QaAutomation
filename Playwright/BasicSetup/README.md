### _Basic Playwright project._

- Run the BasicHeadlessSetup class with the command below. It should download libraries and drivers automatically.
- In case there is any need of an additional installation, follow the prompt for recommendations.
- Playwright will run headless tests by default.
```
mvn compile exec:java -D exec.mainClass="org.demo.BasicSetup"
```

- Command to generate code against a specific website. Click on elements on the page to generate code.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://www.wikipedia.org"
```

- Run the command below to see the trace.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
```
