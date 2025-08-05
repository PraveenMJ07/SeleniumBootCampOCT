# Salesforce Automation Testing Framework - API Documentation

## Overview

This is a comprehensive Selenium WebDriver-based automation testing framework designed for Salesforce CRM operations. The framework provides automated testing capabilities for creating, editing, deleting, and verifying various Salesforce entities including Legal Entities, Opportunities, and Accounts.

## Architecture

The framework follows a page-object model with a base class that handles common setup and teardown operations, while specific test classes extend this base to implement particular business functionalities.

## Dependencies

- **Selenium WebDriver**: For browser automation
- **TestNG**: For test execution and annotations
- **WebDriverManager**: For automatic driver management
- **ChromeDriver**: For Chrome browser automation

## Core Components

---

## BaseClass

**Package**: `selBootCamp.week1.day1.copy`

**Description**: The foundation class that provides common setup and configuration for all test classes in the framework.

### Public Fields

#### `public ChromeDriver driver`
- **Type**: ChromeDriver
- **Description**: The main WebDriver instance used for browser automation
- **Scope**: Available to all extending test classes
- **Usage**: Used for all browser interactions and element operations

### Public Methods

#### `@BeforeMethod public void preCondition()`
- **Description**: Sets up the browser environment and logs into Salesforce before each test method
- **Parameters**: None
- **Returns**: void
- **Throws**: None explicitly declared
- **Annotations**: `@BeforeMethod` - Executes before each test method

**Functionality**:
1. Configures Chrome browser with disabled notifications
2. Sets up WebDriverManager for Chrome
3. Initializes ChromeDriver instance
4. Navigates to Salesforce login page
5. Maximizes browser window
6. Sets implicit wait timeout to 30 seconds
7. Performs automatic login with predefined credentials

**Example Usage**:
```java
public class MyTest extends BaseClass {
    @Test
    public void myTestMethod() {
        // driver is already initialized and logged in
        // Browser is ready for test operations
    }
}
```

**Configuration Details**:
- **URL**: https://login.salesforce.com/
- **Username**: makaia@testleaf.com
- **Password**: SelBootcamp$1234
- **Implicit Wait**: 30 seconds
- **Browser Options**: Notifications disabled

---

## CreateLegalEntity

**Package**: `selBootCamp.week1.day1.copy`

**Description**: Automated test class for creating Legal Entity records in Salesforce CRM.

### Public Methods

#### `@Test public void createLegal() throws InterruptedException`
- **Description**: Creates a new Legal Entity record in Salesforce with predefined data
- **Parameters**: None
- **Returns**: void
- **Throws**: InterruptedException
- **Annotations**: `@Test` - Marks this as a TestNG test method

**Test Steps**:
1. Navigate to App Launcher
2. Access Legal Entities module
3. Create new Legal Entity with test data
4. Validate creation process and capture error messages

**Test Data**:
- **Name**: "Testleaf"
- **Description**: "Salesforce"
- **Status**: "Active"

**Example Usage**:
```java
CreateLegalEntity legalTest = new CreateLegalEntity();
legalTest.createLegal(); // Executes the complete legal entity creation flow
```

**Expected Behavior**:
- Navigates through Salesforce UI to Legal Entities section
- Fills in required fields for new Legal Entity
- Attempts to save the record
- Captures and displays any error messages or notifications

**Output**:
- Prints error notifications to console
- Displays validation messages from Salesforce

---

## CreateOpportunities

**Package**: `selBootCamp.week1.day1.copy`

**Description**: Automated test class for creating Opportunity records in Salesforce Sales module.

### Public Methods

#### `@Test(priority=1,invocationCount=2) public void createOpportunities() throws InterruptedException`
- **Description**: Creates new Opportunity records in Salesforce with validation
- **Parameters**: None
- **Returns**: void
- **Throws**: InterruptedException
- **Annotations**: 
  - `@Test` - TestNG test method
  - `priority=1` - Execution priority
  - `invocationCount=2` - Runs the test twice

**Test Steps**:
1. Navigate to Sales module via App Launcher
2. Access Opportunities section
3. Create new Opportunity with specified details
4. Validate successful creation

**Test Data**:
- **Opportunity Name**: "Salesforce Automation by Praveen"
- **Close Date**: "11/13/2021"
- **Stage**: "Needs Analysis"

**Example Usage**:
```java
CreateOpportunities oppTest = new CreateOpportunities();
oppTest.createOpportunities(); // Creates opportunity and validates
```

**Validation Logic**:
```java
String expectedText = "Salesforce Automation by Praveen";
String actualText = driver.findElement(locator).getText();

if(actualText.equalsIgnoreCase(expectedText)) {
    System.out.println("TestCase is Successfully Completed");
} else {
    System.out.println("TestCase Failed");
}
```

**Features**:
- **Explicit Wait**: Uses WebDriverWait for element visibility
- **JavaScript Execution**: Handles dynamic UI elements
- **Data Validation**: Compares expected vs actual results
- **Multiple Execution**: Runs twice for reliability testing

---

## DeleteOpportunity

**Package**: `selBootCamp.week1.day1.copy`

**Description**: Automated test class for deleting existing Opportunity records from Salesforce.

### Public Methods

#### `@Test(priority=2) public void deleteOpp() throws InterruptedException`
- **Description**: Searches for and deletes a specific Opportunity record
- **Parameters**: None
- **Returns**: void
- **Throws**: InterruptedException
- **Annotations**: 
  - `@Test` - TestNG test method
  - `priority=2` - Executes after CreateOpportunities (priority=1)

**Test Steps**:
1. Navigate to Sales module
2. Access Opportunities tab
3. Search for specific opportunity by name
4. Select and delete the opportunity
5. Verify deletion by searching again

**Search Criteria**:
- **Opportunity Name**: "Salesforce Automation by Praveen"

**Example Usage**:
```java
DeleteOpportunity deleteTest = new DeleteOpportunity();
deleteTest.deleteOpp(); // Deletes the specified opportunity
```

**Deletion Process**:
1. Uses search functionality to locate opportunity
2. Clicks on opportunity record
3. Opens action dropdown menu
4. Selects Delete option
5. Confirms deletion
6. Performs verification search

**Dependencies**:
- Requires existing opportunity created by CreateOpportunities test
- Should be executed after opportunity creation tests

---

## EditOpportunity

**Package**: `selBootCamp.week1.day1.copy`

**Description**: Automated test class for editing existing Opportunity records in Salesforce.

### Public Methods

#### `@Test(dependsOnMethods = "CreateOpportunities") public void editOpp() throws InterruptedException`
- **Description**: Searches for and edits an existing Opportunity record with new data
- **Parameters**: None
- **Returns**: void
- **Throws**: InterruptedException
- **Annotations**: 
  - `@Test` - TestNG test method
  - `dependsOnMethods = "CreateOpportunities"` - Ensures CreateOpportunities runs first

**Test Steps**:
1. Navigate to Opportunities section
2. Search for specific opportunity
3. Open opportunity for editing
4. Modify opportunity fields
5. Save changes and validate updates

**Updated Fields**:
- **Close Date**: "11/16/2021"
- **Stage**: "Perception Analysis"
- **Delivery Status**: "In progress"
- **Description**: "Salesforce"

**Example Usage**:
```java
EditOpportunity editTest = new EditOpportunity();
editTest.editOpp(); // Edits the existing opportunity
```

**Edit Process**:
1. Searches for opportunity by name
2. Opens edit form via action menu
3. Updates multiple fields using various input methods
4. Handles dropdown selections with JavaScript
5. Saves changes and waits for confirmation

**Validation Features**:
- Captures text during dropdown selection
- Validates popup messages after editing
- Verifies final field values after save
- Outputs validation results to console

**Advanced Features**:
- **Key Combinations**: Uses Ctrl+A for field clearing
- **JavaScript Execution**: Handles complex UI interactions
- **Explicit Waits**: Ensures proper element loading
- **Multi-field Updates**: Modifies several fields in single operation

---

## VerifySortClone

**Package**: `selBootCamp.week1.day1.copy`

**Description**: Automated test class for verifying the sorting functionality of Account records in Salesforce.

### Public Methods

#### `@Test public void verifySort() throws InterruptedException`
- **Description**: Verifies that the Account Name sorting functionality works correctly by comparing manually sorted data with UI sorted data
- **Parameters**: None
- **Returns**: void
- **Throws**: InterruptedException
- **Annotations**: `@Test` - TestNG test method

**Test Steps**:
1. Navigate to Accounts module
2. Extract all account names from the table
3. Create a manually sorted copy using Collections.sort()
4. Click the Sort button in the UI
5. Extract the sorted account names from UI
6. Compare manual sort with UI sort results

**Example Usage**:
```java
VerifySortClone sortTest = new VerifySortClone();
sortTest.verifySort(); // Verifies account sorting functionality
```

**Sorting Algorithm**:
```java
// Extract account names before sorting
ArrayList<String> accountNames = new ArrayList<String>();
while (nextElement) {
    // Dynamic element detection and text extraction
    // Converts to lowercase for consistent comparison
}

// Manual sorting using Java Collections
Collections.sort(accountNames);

// Extract account names after UI sorting
ArrayList<String> accountNames1 = new ArrayList<String>();
// Similar extraction process after clicking sort button

// Validation
if(accountNames.equals(accountNames1)) {
    System.out.println("Sorted Lists Match");
} else {
    System.out.println("Sorted Lists Don't Match");
}
```

**Advanced Features**:
- **Dynamic Table Processing**: Handles tables with variable row counts
- **Scrolling Support**: Automatically scrolls to bring elements into view
- **Case-insensitive Comparison**: Converts all text to lowercase
- **List Comparison**: Uses ArrayList.equals() for comprehensive comparison
- **Robust Element Detection**: Uses while loops with size checking

**Data Processing**:
- Extracts account names from dynamic table structure
- Handles pagination and scrolling automatically
- Processes text data for consistent comparison
- Stores data in ArrayList for easy manipulation

---

## Common Patterns and Best Practices

### Navigation Pattern
All test classes follow a consistent navigation pattern:
```java
// Open App Launcher
driver.findElement(By.xpath("//div[@class='slds-r2']")).click();

// Click View All
driver.findElement(By.xpath("//button[text()='View All']")).click();

// Navigate to specific module (Sales, etc.)
driver.findElement(By.xpath("//p[text()='Sales']")).click();
```

### JavaScript Execution Pattern
For handling dynamic UI elements:
```java
WebElement element = driver.findElement(locator);
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", element);
```

### Wait Strategies
- **Implicit Wait**: 30 seconds set in BaseClass
- **Explicit Wait**: WebDriverWait used for specific elements
- **Thread.sleep()**: Used for static waits where necessary

### Error Handling
- InterruptedException declared for thread sleep operations
- Console output for validation results
- Error message extraction and display

## Usage Examples

### Complete Test Suite Execution
```java
// Setup (automatically handled by BaseClass)
@BeforeMethod
public void setup() {
    // Browser initialization and Salesforce login
}

// Test execution sequence
@Test(priority=1, invocationCount=2)
public void createOpportunities() {
    // Creates opportunities
}

@Test(dependsOnMethods = "CreateOpportunities")
public void editOpp() {
    // Edits created opportunities
}

@Test(priority=2)
public void deleteOpp() {
    // Deletes opportunities
}

// Independent tests
@Test
public void createLegal() {
    // Creates legal entities
}

@Test
public void verifySort() {
    // Verifies sorting functionality
}
```

### Custom Test Implementation
```java
public class MyCustomTest extends BaseClass {
    @Test
    public void myCustomTest() throws InterruptedException {
        // driver is available and logged in
        // Navigate to desired module
        driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
        driver.findElement(By.xpath("//button[text()='View All']")).click();
        
        // Implement your test logic
        // Use Thread.sleep() for waits
        // Use JavaScript executor for dynamic elements
        // Validate results with console output
    }
}
```

## Configuration Requirements

### Environment Setup
1. Java 8 or higher
2. Selenium WebDriver libraries
3. TestNG framework
4. WebDriverManager dependency
5. Chrome browser installed

### Salesforce Environment
- Valid Salesforce org access
- Username: makaia@testleaf.com
- Password: SelBootcamp$1234
- Appropriate permissions for CRUD operations

### Execution Environment
- Network access to Salesforce
- Chrome browser permissions
- Sufficient system resources for browser automation

## Troubleshooting

### Common Issues
1. **Element Not Found**: Increase wait times or check locators
2. **JavaScript Errors**: Ensure proper element loading before execution
3. **Login Failures**: Verify credentials and network connectivity
4. **Timeout Issues**: Adjust implicit wait times in BaseClass

### Debug Techniques
- Use console output for validation tracking
- Implement screenshots for failure analysis
- Add explicit waits for better synchronization
- Verify element locators in browser developer tools

---

**Last Updated**: Generated automatically from source code analysis
**Framework Version**: 1.0
**Selenium Version**: Compatible with WebDriver 4.x
**TestNG Version**: Compatible with 7.x series