# User Guide - Salesforce Automation Framework

## Table of Contents
1. [Getting Started](#getting-started)
2. [Prerequisites](#prerequisites)
3. [Installation & Setup](#installation--setup)
4. [Running Tests](#running-tests)
5. [Test Scenarios](#test-scenarios)
6. [Common Use Cases](#common-use-cases)
7. [Customization Guide](#customization-guide)
8. [Troubleshooting](#troubleshooting)
9. [Best Practices](#best-practices)

---

## Getting Started

This Salesforce automation framework provides ready-to-use test automation for common Salesforce CRM operations. The framework is designed for QA engineers, automation testers, and developers who need to automate Salesforce testing workflows.

### What You Can Do
- ✅ Create Legal Entity records
- ✅ Create, Edit, and Delete Opportunities
- ✅ Verify Account sorting functionality
- ✅ Validate data integrity across operations
- ✅ Run regression tests for Salesforce workflows

---

## Prerequisites

### System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Java**: Version 8 or higher
- **Chrome Browser**: Latest version recommended
- **Internet Connection**: Required for Salesforce access

### Required Knowledge
- Basic understanding of Java programming
- Familiarity with TestNG framework
- Understanding of Selenium WebDriver concepts
- Basic knowledge of Salesforce CRM interface

### Salesforce Environment
- Access to a Salesforce org with the provided credentials
- Permissions to create/edit/delete:
  - Legal Entities
  - Opportunities
  - Accounts

---

## Installation & Setup

### Step 1: Clone or Download the Framework
```bash
# If using Git
git clone <repository-url>

# Or download and extract the files to your workspace
```

### Step 2: Set Up Dependencies
Create a `dependencies.txt` file or ensure your IDE has the following libraries:

```xml
<!-- Maven dependencies (if using Maven) -->
<dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.15.0</version>
    </dependency>
    
    <!-- WebDriverManager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.5.3</version>
    </dependency>
    
    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.8.0</version>
    </dependency>
</dependencies>
```

### Step 3: Verify Chrome Installation
Ensure Google Chrome is installed and accessible from your system PATH.

### Step 4: Configure IDE
1. Import the project into your preferred Java IDE (Eclipse, IntelliJ IDEA, etc.)
2. Ensure all dependencies are resolved
3. Verify Java build path is correctly configured

---

## Running Tests

### Option 1: Run Individual Test Classes

#### Running Legal Entity Creation Test
```java
// Right-click on CreateLegalEntity.java and select "Run as TestNG Test"
// Or use command line:
java -cp "classpath" org.testng.TestNG CreateLegalEntity.java
```

#### Running Opportunity Tests
```java
// For creating opportunities
java -cp "classpath" org.testng.TestNG CreateOpportunities.java

// For editing opportunities (depends on CreateOpportunities)
java -cp "classpath" org.testng.TestNG EditOpportunity.java

// For deleting opportunities
java -cp "classpath" org.testng.TestNG DeleteOpportunity.java
```

#### Running Sort Verification Test
```java
java -cp "classpath" org.testng.TestNG VerifySortClone.java
```

### Option 2: Run Complete Test Suite

Create a TestNG XML configuration file:

```xml
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="SalesforceAutomationSuite">
    <test name="SalesforceTests">
        <classes>
            <class name="selBootCamp.week1.day1.copy.CreateOpportunities"/>
            <class name="selBootCamp.week1.day1.copy.EditOpportunity"/>
            <class name="selBootCamp.week1.day1.copy.DeleteOpportunity"/>
            <class name="selBootCamp.week1.day1.copy.CreateLegalEntity"/>
            <class name="selBootCamp.week1.day1.copy.VerifySortClone"/>
        </classes>
    </test>
</suite>
```

### Option 3: IDE Integration
Most Java IDEs provide built-in TestNG support:
1. Right-click on the project/class
2. Select "Run As" → "TestNG Test"
3. View results in the TestNG Results panel

---

## Test Scenarios

### Scenario 1: Basic Opportunity Management
```java
// This scenario demonstrates the complete lifecycle of an opportunity

// Step 1: Create Opportunity (runs twice for reliability)
@Test(priority=1, invocationCount=2)
public void createOpportunities() {
    // Creates "Salesforce Automation by Praveen" opportunity
    // Sets close date to 11/13/2021
    // Sets stage to "Needs Analysis"
    // Validates successful creation
}

// Step 2: Edit the Opportunity
@Test(dependsOnMethods = "CreateOpportunities")
public void editOpp() {
    // Updates close date to 11/16/2021
    // Changes stage to "Perception Analysis"
    // Sets delivery status to "In progress"
    // Updates description to "Salesforce"
}

// Step 3: Delete the Opportunity
@Test(priority=2)
public void deleteOpp() {
    // Searches for the opportunity
    // Deletes it through the UI
    // Verifies deletion by searching again
}
```

### Scenario 2: Legal Entity Creation
```java
// Creates a legal entity with error handling
@Test
public void createLegal() {
    // Navigate to Legal Entities module
    // Create new entity named "Testleaf"
    // Set description to "Salesforce"
    // Set status to "Active"
    // Capture and display any validation errors
}
```

### Scenario 3: Data Verification
```java
// Verifies that Salesforce sorting works correctly
@Test
public void verifySort() {
    // Extract all account names from UI
    // Create manually sorted copy
    // Click sort button in UI
    // Compare results
    // Report whether sorting is working correctly
}
```

---

## Common Use Cases

### Use Case 1: Regression Testing
**Purpose**: Ensure core Salesforce functionality works after updates

**How to Execute**:
1. Run the complete test suite before any Salesforce org changes
2. Run again after changes to verify no regression
3. Compare results to identify any broken functionality

**Example Execution**:
```bash
# Run before changes
mvn test -Dsuite=regression-suite.xml > before-results.txt

# Apply changes to Salesforce org

# Run after changes
mvn test -Dsuite=regression-suite.xml > after-results.txt

# Compare results
diff before-results.txt after-results.txt
```

### Use Case 2: Data Validation Testing
**Purpose**: Verify data integrity across create/edit/delete operations

**Test Flow**:
1. Create test data (opportunities, legal entities)
2. Perform modifications
3. Validate changes are reflected correctly
4. Clean up test data

**Example**:
```java
// Custom validation test
@Test
public void dataIntegrityTest() {
    // Create opportunity with specific data
    createOpportunities();
    
    // Verify data is saved correctly
    String actualName = getOpportunityName();
    Assert.assertEquals(actualName, "Salesforce Automation by Praveen");
    
    // Edit opportunity
    editOpp();
    
    // Verify changes are saved
    String actualStage = getOpportunityStage();
    Assert.assertEquals(actualStage, "Perception Analysis");
    
    // Clean up
    deleteOpp();
}
```

### Use Case 3: Performance Testing
**Purpose**: Identify slow operations in Salesforce UI

**Implementation**:
```java
@Test
public void performanceTest() {
    long startTime = System.currentTimeMillis();
    
    // Execute test operation
    createOpportunities();
    
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    
    System.out.println("Operation took: " + duration + " milliseconds");
    
    // Assert reasonable performance
    Assert.assertTrue(duration < 60000, "Operation took too long: " + duration + "ms");
}
```

### Use Case 4: Cross-Browser Testing
**Purpose**: Verify functionality across different browsers

**Setup**:
Modify BaseClass to support multiple browsers:
```java
@BeforeMethod
@Parameters("browser")
public void preCondition(String browser) {
    if(browser.equalsIgnoreCase("chrome")) {
        // Chrome setup (existing code)
    } else if(browser.equalsIgnoreCase("firefox")) {
        // Firefox setup
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
    // Continue with common setup
}
```

---

## Customization Guide

### Adding New Test Cases

#### Example: Creating a New Account Test
```java
public class CreateAccount extends BaseClass {
    
    @Test
    public void createAccount() throws InterruptedException {
        // Navigate to Accounts
        driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();
        driver.findElement(By.xpath("//p[text()='Sales']")).click();
        Thread.sleep(5000);
        
        WebElement account = driver.findElement(By.xpath("//span[text()='Accounts']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", account);
        
        // Click New button
        driver.findElement(By.xpath("//div[text()='New']")).click();
        Thread.sleep(3000);
        
        // Fill account details
        driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Test Account");
        driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
        
        // Validate creation
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(text(),'Test Account')]")));
        
        System.out.println("Account created successfully");
    }
}
```

### Modifying Test Data

#### Changing Opportunity Data
```java
// In CreateOpportunities.java, modify these values:
String opportunityName = "Your Custom Opportunity Name";
String closeDate = "12/31/2024";  // Your desired date
String stage = "Prospecting";     // Your desired stage
```

#### Changing Legal Entity Data
```java
// In CreateLegalEntity.java, modify these values:
String entityName = "Your Entity Name";
String description = "Your Description";
String status = "Inactive";  // Or any valid status
```

### Adding Wait Strategies

#### Custom Explicit Wait
```java
public void waitForElement(By locator, int timeoutSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
    wait.until(ExpectedConditions.elementToBeClickable(locator));
}

// Usage
waitForElement(By.xpath("//button[text()='Save']"), 30);
```

#### Fluent Wait Implementation
```java
public void fluentWaitForElement(By locator) {
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(2))
        .ignoring(NoSuchElementException.class);
    
    wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}
```

### Adding Reporting

#### Simple Console Reporting
```java
@Test
public void testWithReporting() {
    try {
        createOpportunities();
        System.out.println("✅ PASS: Opportunity created successfully");
    } catch (Exception e) {
        System.out.println("❌ FAIL: " + e.getMessage());
        throw e;
    }
}
```

#### ExtentReports Integration
```java
// Add ExtentReports dependency and create custom reporting
public class ReportUtils {
    private static ExtentReports extent;
    private static ExtentTest test;
    
    public static void initReport() {
        extent = new ExtentReports();
        // Configure reporter
    }
    
    public static void logStep(String message) {
        test.log(Status.INFO, message);
    }
    
    public static void logPass(String message) {
        test.log(Status.PASS, message);
    }
    
    public static void logFail(String message) {
        test.log(Status.FAIL, message);
    }
}
```

---

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: Element Not Found
**Symptoms**: `NoSuchElementException` or `TimeoutException`

**Solutions**:
```java
// Increase wait time
driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

// Add explicit wait
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.elementToBeClickable(locator));

// Use JavaScript execution
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].scrollIntoView();", element);
js.executeScript("arguments[0].click();", element);
```

#### Issue 2: Login Failures
**Symptoms**: Cannot access Salesforce after login

**Solutions**:
```java
// Verify credentials in BaseClass
driver.findElement(By.id("username")).sendKeys("correct-username");
driver.findElement(By.id("password")).sendKeys("correct-password");

// Add verification after login
try {
    driver.findElement(By.xpath("//div[@class='slds-r2']"));
    System.out.println("Login successful");
} catch (NoSuchElementException e) {
    System.out.println("Login failed - check credentials");
    throw e;
}
```

#### Issue 3: Slow Performance
**Symptoms**: Tests running very slowly

**Solutions**:
```java
// Optimize waits - reduce unnecessary Thread.sleep()
// Replace with explicit waits
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.elementToBeClickable(locator));

// Use faster locators
// Prefer ID > Name > CSS > XPath
driver.findElement(By.id("elementId"));  // Fastest
driver.findElement(By.name("elementName"));  // Fast
driver.findElement(By.cssSelector(".className"));  // Medium
driver.findElement(By.xpath("//complex/xpath"));  // Slowest
```

#### Issue 4: Test Dependencies
**Symptoms**: Tests fail when run individually but pass in suite

**Solutions**:
```java
// Make tests independent by adding setup in each test
@Test
public void independentTest() {
    // Ensure required data exists
    ensureTestDataExists();
    
    // Run test logic
    performTestActions();
    
    // Clean up if needed
    cleanupTestData();
}

// Or use TestNG groups
@Test(groups = "setup")
public void setupData() { }

@Test(groups = "test", dependsOnGroups = "setup")
public void runTest() { }
```

### Debug Mode

#### Enable Verbose Logging
```java
// Add to BaseClass for detailed logging
public void logAction(String action) {
    System.out.println(LocalDateTime.now() + " - " + action);
}

// Usage in tests
logAction("Clicking on App Launcher");
driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
logAction("App Launcher clicked successfully");
```

#### Screenshot on Failure
```java
public void takeScreenshot(String testName) {
    try {
        TakesScreenshot ts = (TakesScreenshot)driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        
        // Save screenshot
        FileOutputStream fos = new FileOutputStream("screenshots/" + testName + ".png");
        fos.write(screenshot);
        fos.close();
        
        System.out.println("Screenshot saved: " + testName + ".png");
    } catch (Exception e) {
        System.out.println("Failed to take screenshot: " + e.getMessage());
    }
}
```

---

## Best Practices

### Test Design
1. **Keep tests independent**: Each test should be able to run standalone
2. **Use meaningful test data**: Avoid hardcoded values when possible
3. **Implement proper cleanup**: Remove test data after execution
4. **Add assertions**: Validate expected outcomes explicitly

### Code Organization
1. **Follow naming conventions**: Use descriptive method and variable names
2. **Add comments**: Explain complex logic and business rules
3. **Use page object model**: For larger frameworks, consider POM pattern
4. **Centralize configurations**: Keep URLs, credentials, and timeouts in config files

### Execution Strategy
1. **Run tests in appropriate order**: Use TestNG dependencies and priorities
2. **Group related tests**: Use TestNG groups for better organization
3. **Implement retry logic**: For flaky tests due to network issues
4. **Monitor execution time**: Track performance trends

### Maintenance
1. **Regular updates**: Keep locators updated with UI changes
2. **Refactor common code**: Extract reusable methods to base classes
3. **Update dependencies**: Keep Selenium and other libraries current
4. **Document changes**: Maintain clear documentation for modifications

---

## Sample Test Execution Output

```
[INFO] Starting Salesforce Automation Tests
[INFO] Browser: Chrome v120.0.6099.129
[INFO] Salesforce Org: https://login.salesforce.com/

=== Legal Entity Creation Test ===
✅ PASS: Navigated to Legal Entities module
✅ PASS: Created legal entity 'Testleaf'
⚠️  WARN: Validation error captured: Required field missing
❌ FAIL: Entity creation completed with errors

=== Opportunity Management Tests ===
✅ PASS: Created opportunity 'Salesforce Automation by Praveen'
✅ PASS: Updated opportunity stage to 'Perception Analysis'
✅ PASS: Deleted opportunity successfully

=== Account Sorting Verification ===
✅ PASS: Extracted 25 account names
✅ PASS: Manual sort completed
✅ PASS: UI sort completed
✅ PASS: Sorted lists match - sorting functionality working correctly

=== Test Summary ===
Total Tests: 5
Passed: 4
Failed: 1
Warnings: 1
Execution Time: 4 minutes 32 seconds
```

---

**Need Help?**
- Check the Technical Reference for detailed implementation
- Review the API Documentation for method specifications
- Ensure all prerequisites are met
- Verify Salesforce org access and permissions
- Contact your test automation team for framework-specific questions