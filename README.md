## Stellar Burgers — Selenium UI Automation Suite

This repository contains a **UI automation test framework** for the *Stellar Burgers* web application.  


## Tech Stack

- **Java 17**
- **Selenium WebDriver**
- **JUnit 4**
- **REST Assured** (API helpers to support UI flows)
- **Maven**
- **Page Object Model (POM)**
- **Faker** for dynamic test data
- **Allure** for local reporting

## Test Coverage

This suite validates core customer flows of the application:

### **Authentication**
- Registration with unique users  
- Login via multiple entry points  
- Logout behavior  
- Negative login scenarios  

### **Burger Constructor**
- Ingredient interactions  
- Drag & drop  
- Price updates  
- Adding/removing items  

### **Order Placement**
- Hybrid UI + API workflow (creating users, preparing test state)  
- Validating redirects and order completion  

### **Navigation & Profile**
- Profile access rules  
- Route guarding  
- Header navigation  


## Project Structure
```bash
src
 ├── main/java
 │    ├── Base/        # Driver factory, base test setup
 │    ├── Pages/       # Page Objects
 │    └── Utils/       # Helpers (API, waits, config)
 └── test/java
      ├── AuthenticationTests/
      ├── SignUpTests/
      ├── OrderTests/
      └── TestData/
```

## Running Tests Locally
Run the full suite:  
``` bash
mvn clean test
```

Run a specific test class:
``` bash
mvn -Dtest=AuthenticationTests test
```

Run tests headless:
``` bash
mvn clean test -Dheadless=true
```

Generate an Allure report locally:
``` bash
mvn allure:serve
```

## CI/CD Notes

This project is designed to integrate seamlessly into CI/CD systems such as GitHub Actions, GitLab CI, or Jenkins.  
It includes:

* Allure-ready test annotations and results
* Stable, deterministic test structure suitable for automated pipelines
* Clear separation of concerns (POM, BaseTest, TestData) for maintainability
* Configurable browser drivers for local or remote execution  

The CI/CD pipelines for the full Stellar Burgers testing ecosystem are implemented in the main project repository.

## Author
Regina Nikogosian — QA Engineer (Manual + Automation)
