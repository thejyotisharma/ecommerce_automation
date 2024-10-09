# Ecommerce website Frontend Automation Test Suite

This repository contains the automated test suite for the QKart frontend application which is a dummy ecommerce website, specifically designed to use for automation testing. The test suite is designed to test key functionalities such as **order placement**, **UI tests**, **registration form test**,  **user login**. It is built using Selenium WebDriver for browser automation, JUnit for the test framework, and Gradle for build automation. The test reports are generated using **Allure Reports**.

**Website Under Test**: [QKart Frontend](https://crio-qkart-frontend-qa.vercel.app/)  
**Test Credentials**:
- Username: `jyotisharma1`
- Password: `7JeE3T@7m5MQhh`

**[🧪 Allure test report](index.html)**

## Table of Contents
- [Project Structure](#project-structure)
- [Pre-requisites](#pre-requisites)
- [Running the Tests](#running-the-tests)
- [Test Report Generation (Allure Reports)](#test-report-generation)
- [Test Scenarios](#test-scenarios)
    - [Login Test](#login-test)
    - [Order Placement Test](#order-placement-test)

## Project Structure

```
├── src
│   └── test
│       └── com
│           └── jyotisharma
│               ├── HomePageTest.java
│               ├── LoginTest.java
│               ├── OrderPlacementTest.java
│               ├── RegisterUserTest.java
│               └── helper
│                   └── helper classes for tests
├── build.gradle
└── README.md
```

- **`src/test/java`**: Contains test classes that define and execute the tests.
- **`build.gradle`**: Gradle build configuration file for managing dependencies and tasks.

## Pre-requisites

1. **Java 16+**: Make sure you have Java installed on your system.
2. **Gradle**: Install Gradle or use Gradle wrapper (`./gradlew` for Linux/macOS or `gradlew.bat` for Windows).

### Install Dependencies
To install dependencies, run:
```bash
./gradlew clean build
```

## Running the Tests

To run all the tests in the suite, use the following command:
```bash
./gradlew test
```

## Test Report Generation

### Allure Report Generation
After running the tests, the reports can be generated and viewed using **Allure** or can view the generated HTML report [from here](index.html).

#### Steps to Generate and View Allure Reports:

1. **Install Allure Command Line**:  
   You need Allure installed to generate and view reports. You can install it using:
   ```bash
   brew install allure   # macOS
   sudo apt-add-repository ppa:qameta/allure && sudo apt-get update && sudo apt-get install allure   # Ubuntu
   choco install allure   # Windows (using Chocolatey)
   ```

2. **Generate Allure Report**:  
   After running the tests, generate the Allure report using:
   ```bash
   ./gradlew allureReport
   ```

3. **Open Allure Report**:  
   To open the report in your default browser, run:
   ```bash
   allure serve build/allure-results
   ```

   This will launch a local server with the detailed test results including pass/fail status, error logs, and screenshots.

    **Or open the [generated html report from build folder](build/reports/allure-report/allureReport/index.html).**

## Test Scenarios

### Order Placement Test
The **OrderPlacementTest** verifies that a user can successfully add items to the cart and complete the checkout process.

#### Steps:
1. Log in using the test credentials.
2. Search for a product and add it to the cart.
3. Navigate to the cart and proceed to the checkout.
4. Confirm the order by providing the necessary details.
5. Verify the order confirmation message.

**Expected Outcome**: The user should be able to place an order successfully, and an order confirmation should be displayed.

### Login Test
The **LoginTest** verifies that users can log in successfully with valid credentials.

#### Steps:
1. Open the QKart application.
2. Navigate to the login page.
3. Enter the valid credentials.
4. Verify if the user is successfully logged in by checking the presence of the user's dashboard or profile icon.

**Expected Outcome**: The user should be able to log in and navigate to the homepage.

### Homepage UI Element Test
The **HomepageUIElementTest** verifies the presence and functionality of critical UI elements on the QKart homepage.

#### Steps:
1. Open the QKart homepage.
2. Verify the visibility of essential elements such as:
    - Search bar
    - Login Button
    - Register Button
    - Product listings
    - Terms of Use
    - Privacy Policy

**Expected Outcome**: All essential elements should be present, functional, and displayed correctly on the homepage.

### Registration Form Test
The **RegistrationFormTest** checks the functionality of the user registration form on the QKart website.

#### Steps:
1. Navigate to the **Register** page from the homepage.
2. Fill in the registration form fields with valid data:
    - Username
    - Password
    - Confirm Password
3. Submit the form and verify:
    - The user receives a success message after registration.
    - The user is redirected to the login page or automatically logged in after registration.
4. Test form validation by:
    - Submitting mismatched passwords and verifying the corresponding validation error.
    - Leaving any required field empty and verifying the error.

**Expected Outcome**: The form should validate user input correctly and allow successful registration with valid details.

---
