\# Hudl Login Automation



This project contains a Selenium TestNG automation script for testing the Hudl login functionality. It allows you to test login with different user credentials by passing them as system properties.



---



\## Prerequisites



\- Java JDK 8 or higher installed

\- Maven installed

\- Chrome browser installed

\- ChromeDriver matching your Chrome version (included in `drivers/chromedriver-win64/`)



---



\## Running the Tests



You can run the login test using Maven and pass your \*\*Hudl credentials\*\* (email and password) as system properties.



\### Example Command:



```bash

mvn clean test -DHUDL\_EMAIL="your\_email@example.com" -DHUDL\_PASSWORD=YourPassword123



Replace your\_email@example.com and YourPassword123 with your own Hudl credentials.



\*\*Notes\*\*



If you do not provide credentials, the test will use default dummy credentials and is expected to fail (for testing invalid login).



The test will automatically verify successful login by checking for the presence of the user avatar after logging in.



\*\*Project Structure\*\*



hudl-login-automation/

├── drivers/                   # Contains ChromeDriver executable

├── src/

│   └── test/

│       └── java/

│           └── com/hudl/tests/LoginTest.java

├── pom.xml                    # Maven project file

└── README.md



