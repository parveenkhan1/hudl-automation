\# Hudl Login Automation Tests



\## Description

This project contains Selenium + TestNG automation tests to validate the login functionality on \[Hudl.com](https://www.hudl.com/).  

It demonstrates proper Maven project structure, use of relative paths for drivers, and a clean test framework suitable for cross-functional testing.



\## Project Structure



hudl-automation/

├── drivers/ # ChromeDriver executable

├── java/

│ └── src/test/java/com/hudl/tests/LoginTest.java

├── pom.xml # Maven project file

└── testng.xml # TestNG suite configuration





\## Setup \& Execution Instructions



1\. \*\*Clone the repository\*\*:



```bash

git clone <repo-url>



2\. \*\*Navigate to project root\*\*:



cd hudl-automation



3\. \*\*Run test using maven\*\*:



mvn clean test



4\. \*\*Expected output\*\*:



Chrome opens and navigates to https://www.hudl.com/.



Console prints the page title.



Maven reports Tests run: 1, Failures: 0, Errors: 0



