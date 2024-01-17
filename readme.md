**Testing Framework for Swag Labs Application**

Contains code for testing the functionalities of website "https://www.saucedemo.com/ " using Selenium WebDriver

- used Java as a programming language and TestNG as a testing framework
- used Maven for managing all dependencies needed for the project and used GitHub for version control to check-in the code files
- The framework is built with Data-Driven approach with a combination of Page Object Model, and used Apache POI to read test data from Excel file
- The framework is very modular and I have created page classes for all common components, not just for particular page. if there is something common which occurs in different pages , then we create page object for that also so the different test classes can make use of it. Avoiding all the redundancy as following DRY code principle


**Requirements:**
- Java JDK
- Chrome Browser
- IDE

