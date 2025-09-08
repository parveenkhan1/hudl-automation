
# Hudl Login Automation (Java + Selenium + TestNG)

## Overview
Automated tests for Hudl login using Java, Selenium WebDriver, TestNG, and ExtentReports. Follows best practices: Page Object Model, config-driven, secure credential handling, and advanced reporting.

## Prerequisites
- Java 17 or higher
- Maven
- Google Chrome browser
- ChromeDriver (already included under `drivers/chromedriver-win64/`)

## How to Run
1. Clone this repo
2. Run tests with your credentials:
	 ```
	 mvn clean test -DHUDL_EMAIL=your@email.com -DHUDL_PASSWORD=yourpassword
	 ```
	 - If no credentials are provided, dummy credentials are used and the test will fail (verifying error handling).

## Features
- Central config: `src/test/resources/config.properties`
- Thread-safe WebDriver management: `com.hudl.core.DriverFactory`
- Base test/page classes for reusability
- Page Object Model: `LoginPage`, `HomePage`
- ExtentReports HTML reporting: `target/ExtentReport.html`
- Valid and invalid login tests
- UI assertions for avatar and navigation bar after login

## Test Results
- Console output shows step-by-step logs
- Reports:
	- `target/ExtentReport.html` (advanced)
	- `target/surefire-reports/index.html` (TestNG)
	- `target/surefire-reports/emailable-report.html`

## Project Structure
```
hudl-automation/
├── drivers/                   # ChromeDriver executable
├── src/
│   └── test/
│       └── java/
│           ├── com/hudl/core/         # Core framework classes
│           ├── com/hudl/pages/        # Page Object Model classes
│           └── com/hudl/tests/        # Test classes
│       └── resources/
│           └── config.properties      # Central config
├── pom.xml                    # Maven project file
└── README.md
```

## Credentials
- **Do NOT commit real passwords.**
- Pass credentials via Maven command line as shown above.

## Extending
- Add more tests in `src/test/java/com/hudl/tests/`
- Add more page objects in `src/test/java/com/hudl/pages/`

## Best Practices Used
- Page Object Model
- Config-driven tests
- No hardcoded credentials
- Reporting
- Clean code structure

---
**Share your public GitHub repo link after pushing.**
