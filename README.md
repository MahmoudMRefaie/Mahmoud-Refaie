# QA-Project

## First Part:
Testing Yalla Kora application
##### Deliverables:
[Manual-Document.xlsx](Manual-Document.xlsx) file or visit this URL [Manual Document](https://docs.google.com/spreadsheets/d/1gQblACTu6xv4WRJfd8lmyVmeO565SKkSTP15lHZghSI/edit?usp=sharing). This file contains the following documents:
* Test Plan sheet: It contains the test plan for Yalla kora application with the following sections:
   * Overview
   * Test Objectives
   * Test Scope
   * High level Test Cases
   * Test Environment
   * Test Deliverables
   * Risks
   * Budget	Schedule
* Test Cases sheet: It contains detailed test case for Yalla kora application with the following sections:
   * Test Case ID
   * Title
   * Prerequisites
   * Test Steps
   * Expected Result
   * Component
   * Prioritization
   * Test Environment
   * Actual Result: This field is blank until executing the test case
   * Status: This field is blank until executing the test case

## Second Part:
In previous file it contains a sheet called [YallaKora Defects Report] that has the following:
* Reporting 7 bugs
* Detailed bugs with sections:
   * Bug ID
   * Title
   * Steps to reproduce
   * Expected Result
   * Actual Result
   * Affected Devices
   * Severity
   * Priority
   * Impact
   * Attachments
   * Defect type
   * Status

## Third Part:
I created test account to login with, since facebook temporarily removed the ability to create new test users.
* To run Facebook UI test cases, make sure that Google Chrome is updated with last version [115.0.5790.102]
* Using data-driven solution in registration and login test cases.
* In [Registration](src/test/java/UI/Registration.java) class, 3 random and unique emails are registered as new user to facebook in one test using Data Provider
* In [Login](src/test/java/UI/Login.java) class, 3 test users logged in to facebook and Assert that this user logged in successfully, then logged out from the system in one test using Data Provider

## Fourth Part:
Automate APIs through Best Buy API playground framework in local environment
* Test Cases are documented in the sheet called [Best Buy Test Cases] in mentioned file [Manual-Document.xlsx](Manual-Document.xlsx) or URL [Manual Document](https://docs.google.com/spreadsheets/d/1gQblACTu6xv4WRJfd8lmyVmeO565SKkSTP15lHZghSI/edit?usp=sharing)
* Automating proposed test cases in path [src/test/java/API] to run these test cases you will need to do the following:
* Clone and start local environment
```
git clone https://github.com/bestbuy/api-playground/
cd api-playground
npm install
npm start
# Best Buy API Playground started at http://localhost:3030
```
* Note that this project is outdated so you may face failures when start the project. following instructions may help:
   * If you face errors in npm install:
     Remove dependency “sqlite3”, then npm install again and start the server
   * If there are errors in npm start:
     Install manually sqlite3 npm i sqlite3
     Then run npm start, you will fine error this error
     ```
     BestBuyAPI/api-playground/src/db/index.js:36
     var model = sequelize['import'](path.join(__dirname, file));
     ```
     solution: Replace error line with line:
     ```
     var model = require(path.join(__dirname, file))(sequelize, Sequelize.DataTypes)
     ```
     Then run npm start;
   * If there are errors in running requests:
     Return sequelize package to origin version control at package.json file then install and start server again
   * If node versions are not compatible in Array, edit the following file:
      BestBuyAPI/api-playground/src/services/categories/hooks/index.js:50:39
      And add ? After array -> hook.result.data?
      or downgrade your node version
* Automating this website APIs through 4 main test classes:
   * [Category_Tests](src/test/java/API/Category_Tests.java): Automate category service functionalities.
   * [Product_Tests](src/test/java/API/Product_Tests.java): Automate product service functionalities.
   * [Service_Tests](src/test/java/API/Service_Tests.java): Automate service service functionalities.
   * [Store_Tests](src/test/java/API/Store_Tests.java): Automate store service functionalities.

#### Automation Results:
```
===============================================
Default Suite
Total tests run: 16, Failures: 0, Skips: 0
===============================================
```
