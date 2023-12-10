# 'Stellar Burgers' web-ui-tests

WEB UI TESTING A TRAINING SERVICE
[**«STELLAR BURGERS»**](https://stellarburgers.nomoreparties.site)


## Description

**Project stack**
- Java 11
- JUnit 4.13.2
- Selenium 4.15.0

**Supported browsers**
- Google Chrome
- Yandex Browser
- Microsoft Edge
- Mozilla Firefox


## Repository cloning
```shell
git clone https://github.com/Sergey8193/Diplom_3.git
```


## Running auto tests

**Running tests and generating allure report** (```mvn clean test```)

Report folder: ```target/allure-results```

When using the "browser" system property in a non-parameterized test, 
the test will be executed for the specified browser.

List of supported "browser" values: ```'chrome', 'yandex', 'edge', 'firefox' ```. 

Value by default is ```'chrome'```  

For example (```mvn clean test -Dtest=OrderHistoryPageTest -Dbrowser=edge```)

**Launching web server with a report** (```mvn allure:serve```)



## Project Tree

```
pom.xml
README.md
.gitignore
src
|-- main
|   |-- java
|   |   |-- praktikum
|   |   |   |-- stellarburgers
|   |   |   |   |-- browser
|   |   |   |   |   |-- Browser.java
|   |   |   |   |   |-- BrowserType.java
|   |   |   |   |   |-- OsType.java
|   |   |   |   |-- constants
|   |   |   |   |   |-- RestClient.java
|   |   |   |   |   |-- UserState.java
|   |   |   |   |   |-- UserStatus.java
|   |   |   |   |   |-- WayToLoginPage.java
|   |   |   |   |-- ingredient
|   |   |   |   |   |-- IngredientClient.java
|   |   |   |   |   |-- IngredientData.java
|   |   |   |   |   |-- IngredientsOrderDataGenerator.java
|   |   |   |   |   |-- IngredientsSuccessInfo.java
|   |   |   |   |-- order
|   |   |   |   |   |-- CreateOrderOrderData.java
|   |   |   |   |   |-- GetOrdersOrderData.java
|   |   |   |   |   |-- GetOrdersSuccessInfo.java
|   |   |   |   |   |-- OrderClient.java
|   |   |   |   |   |-- OrderData.java
|   |   |   |   |   |-- OrderDataGenerator.java
|   |   |   |   |-- pom
|   |   |   |   |   |-- base
|   |   |   |   |   |   |-- AccountBasePage.java
|   |   |   |   |   |   |-- BasePage.java
|   |   |   |   |   |   |-- BaseWait.java
|   |   |   |   |   |-- page
|   |   |   |   |   |   |-- LoginPage.java
|   |   |   |   |   |   |-- MainPage.java
|   |   |   |   |   |   |-- OrderHistoryPage.java
|   |   |   |   |   |   |-- PasswordRecoveryPage.java
|   |   |   |   |   |   |-- ProfilePage.java
|   |   |   |   |   |   |-- RegisterPage.java
|   |   |   |   |-- user
|   |   |   |   |   |-- User
|   |   |   |   |   |-- UserClient.java
|   |   |   |   |   |-- UserContactInfo.java
|   |   |   |   |   |-- UserCredentials.java
|   |   |   |   |   |-- UserDataGenerator.java
|   |   |   |   |   |-- UserRegistrationData.java
|   |   |   |   |   |-- UserSuccessInfo.java
|   |   resources
|   |   |-- yandexdriver.exe
|   |   |-- yandexdriver-linux
|   |   |-- yandexdriver-mac
|-- test
|   |-- java
|   |   |-- praktikum
|   |   |   |-- stellarburgers
|   |   |   |   |-- pom
|   |   |   |   |   |-- base
|   |   |   |   |   |   |-- BaseTest.java
|   |   |   |   |   |   |-- BaseWeb.java
|   |   |   |   |   |-- page
|   |   |   |   |   |   |-- LoginPageParamTest.java
|   |   |   |   |   |   |-- LoginPageTest.java
|   |   |   |   |   |   |-- MainPageBurgerAssemblyParamTest.java
|   |   |   |   |   |   |-- MainPageBurgerAssemblyTest.java
|   |   |   |   |   |   |-- MainPageConstructorSectionParamTest.java
|   |   |   |   |   |   |-- MainPageConstructorSectionTest.java
|   |   |   |   |   |   |-- OrderHistoryPageParamTest.java
|   |   |   |   |   |   |-- OrderHistoryPageTest.java
|   |   |   |   |   |   |-- ProfilePageParamTest.java
|   |   |   |   |   |   |-- ProfilePageTest.java
|   |   |   |   |   |   |-- RegisterPageParamTest.java
|   |   |   |   |   |   |-- RegisterPageTest.java
|   |   |   |   |-- WebUiParamTestLauncher.java
|   |   |   |   |-- WebUiTestLauncher.java
```


## Completed tasks

**Created auto Web UI tests of basic functionality**
- user registration
- user authorization
- creating an order
- placing an order on the service
