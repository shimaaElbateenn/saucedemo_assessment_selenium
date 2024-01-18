**Testing Framework for Swag Labs Application**

Contains code for testing the functionalities of the website "https://www.saucedemo.com/ " using Selenium WebDriver

- Used Java as a programming language and TestNG as a testing framework
- Used Maven for managing all dependencies needed for the project and used GitHub for version control to check-in the code files.
- The framework is built with Data-Driven approach with a combination of Page Object Model and used Apache POI to read test data from an Excel file.
- The framework is very modular and I have created page classes for all common components, not just for a particular page. if there is something common that occurs in different pages , then we create a page object for that also so the different test classes can make use of it. Avoiding all the redundancy by following DRY code principle.
- Created Utilities class that perform generic actions across the automation execution, for example, sleep(), getScreenshotName(), and verifyTextContains().
- Created CudtomDriver class that implements the WebDriver interface and overrides its methods to make the code more reusable and efficient.


**Requirements:**
- Java JDK
- Chrome Browser
- IDE

