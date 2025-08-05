# Quick Reference Guide - Salesforce Automation Framework

## Essential Components

### BaseClass - Core Setup
```java
public class BaseClass {
    public ChromeDriver driver;  // Main WebDriver instance
    
    @BeforeMethod
    public void preCondition() {
        // Sets up Chrome, logs into Salesforce
        // URL: https://login.salesforce.com/
        // Credentials: makaia@testleaf.com / SelBootcamp$1234
        // Implicit wait: 30 seconds
    }
}
```

## Test Classes Quick Lookup

| Class | Purpose | Key Method | Test Data |
|-------|---------|------------|-----------|
| `CreateLegalEntity` | Creates legal entities | `createLegal()` | Name: "Testleaf", Desc: "Salesforce" |
| `CreateOpportunities` | Creates opportunities | `createOpportunities()` | Name: "Salesforce Automation by Praveen" |
| `EditOpportunity` | Edits opportunities | `editOpp()` | Stage: "Perception Analysis" |
| `DeleteOpportunity` | Deletes opportunities | `deleteOpp()` | Searches and deletes by name |
| `VerifySortClone` | Verifies sorting | `verifySort()` | Compares manual vs UI sorting |

## Common Navigation Pattern

```java
// Universal Salesforce navigation pattern used in all tests
driver.findElement(By.xpath("//div[@class='slds-r2']")).click();           // App Launcher
driver.findElement(By.xpath("//button[text()='View All']")).click();      // View All
driver.findElement(By.xpath("//p[text()='Sales']")).click();              // Sales module
Thread.sleep(10000);                                                       // Wait for loading
```

## Essential XPath Locators

### Navigation Elements
```java
String APP_LAUNCHER = "//div[@class='slds-r2']";
String VIEW_ALL_BUTTON = "//button[text()='View All']";
String SALES_MODULE = "//p[text()='Sales']";
String OPPORTUNITIES_TAB = "//span[text()='Opportunities']";
String ACCOUNTS_TAB = "//span[text()='Accounts']";
String LEGAL_ENTITIES = "//p[text()='Legal Entities']";
```

### Opportunity Management
```java
String NEW_OPPORTUNITY = "//div[text()='New']";
String OPP_NAME_FIELD = "//input[@name='Name']";
String OPP_CLOSE_DATE = "//input[@name='CloseDate']";
String OPP_STAGE_DROPDOWN = "(//input[@class='slds-input slds-combobox__input'])[3]";
String SAVE_BUTTON = "//button[@name='SaveEdit']";
String OPP_SEARCH = "//input[@name='Opportunity-search-input']";
String ACTION_DROPDOWN = "//div[@class='forceVirtualActionMarker forceVirtualAction']";
String EDIT_LINK = "//a[@title='Edit']";
String DELETE_LINK = "//a[@title='Delete']";
```

### Legal Entity Elements
```java
String NEW_LEGAL_ENTITY = "//span[text()='New Legal Entity']";
String ENTITY_NAME_INPUT = "(//input[@class=' input'][@type='text'])[2]";
String ENTITY_DESCRIPTION = "//textarea[@class=' textarea']";
String STATUS_DROPDOWN = "//a[@class='select'][@aria-disabled='false']";
String ACTIVE_STATUS = "//a[text()='Active']";
String SAVE_LEGAL_ENTITY = "(//span[text()='Save'])[2]";
```

## JavaScript Execution Patterns

### Click Element (Most Common)
```java
WebElement element = driver.findElement(locator);
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", element);
```

### Scroll Into View
```java
driver.executeScript("arguments[0].scrollIntoView()", element);
```

### Combined Scroll and Click
```java
WebElement element = driver.findElement(locator);
driver.executeScript("arguments[0].scrollIntoView()", element);
Thread.sleep(2000);  // Brief pause
driver.executeScript("arguments[0].click();", element);
```

## Wait Strategies

### Implicit Wait (Set in BaseClass)
```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

### Explicit Wait
```java
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
wait.until(ExpectedConditions.elementToBeClickable(locator));
```

### Static Wait (Use sparingly)
```java
Thread.sleep(3000);   // 3 seconds
Thread.sleep(10000);  // 10 seconds (common for page loads)
```

## Common Test Patterns

### Basic Test Structure
```java
@Test
public void testMethod() throws InterruptedException {
    // 1. Navigate to module
    navigateToModule();
    
    // 2. Perform actions
    performTestActions();
    
    // 3. Validate results
    validateResults();
    
    // 4. Output results
    System.out.println("Test completed");
}
```

### Data Input Pattern
```java
// Clear and enter new data
driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL,"a"), "New Value");

// Simple data entry
driver.findElement(locator).sendKeys("Test Data");
```

### Dropdown Selection Pattern
```java
// Click dropdown
WebElement dropdown = driver.findElement(dropdownLocator);
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", dropdown);

// Select option
driver.findElement(By.xpath("//span[@title='Option Text']")).click();
```

### Search Pattern
```java
driver.findElement(By.xpath("//input[@name='Search-input']"))
    .sendKeys("Search Term", Keys.ENTER);
Thread.sleep(4000);  // Wait for results
```

## TestNG Annotations Quick Reference

```java
@BeforeMethod                                    // Runs before each test
@Test                                           // Basic test method
@Test(priority=1)                              // Execution order
@Test(invocationCount=2)                       // Run multiple times
@Test(dependsOnMethods = "otherMethod")        // Method dependency
@Test(priority=1, invocationCount=2)          // Combined annotations
```

## Validation Patterns

### String Comparison
```java
String expected = "Expected Text";
String actual = driver.findElement(locator).getText();

if(actual.equalsIgnoreCase(expected)) {
    System.out.println("✅ PASS: Text matches");
} else {
    System.out.println("❌ FAIL: Text doesn't match");
}
```

### List Comparison (VerifySortClone)
```java
ArrayList<String> list1 = new ArrayList<>();
ArrayList<String> list2 = new ArrayList<>();

// Populate lists...

if(list1.equals(list2)) {
    System.out.println("✅ PASS: Lists match");
} else {
    System.out.println("❌ FAIL: Lists don't match");
}
```

### Element Presence Check
```java
try {
    driver.findElement(locator);
    System.out.println("✅ Element found");
} catch (NoSuchElementException e) {
    System.out.println("❌ Element not found");
}
```

## Error Handling Patterns

### Basic Exception Handling
```java
try {
    // Test actions
    performAction();
} catch (InterruptedException e) {
    System.out.println("Thread interrupted: " + e.getMessage());
    throw e;
} catch (Exception e) {
    System.out.println("Unexpected error: " + e.getMessage());
    throw e;
}
```

### Error Message Capture
```java
String errorText = driver.findElement(By.xpath("//div[@class='genericNotification']")).getText();
String errorList = driver.findElement(By.xpath("//ul[@class='errorsList']")).getText();
System.out.println("Error: " + errorText);
System.out.println("Details: " + errorList);
```

## Dynamic Element Handling

### Loop Through Table Rows
```java
boolean hasMoreElements = true;
int index = 0;
ArrayList<String> dataList = new ArrayList<>();

while (hasMoreElements) {
    List<WebElement> elements = driver.findElements(
        By.xpath("//table/tbody/tr[" + (index + 1) + "]/td"));
    
    if(elements.size() == 0) {
        hasMoreElements = false;
    } else {
        String data = elements.get(0).getText().toLowerCase();
        dataList.add(data);
        index++;
    }
}
```

### Dynamic Scroll and Extract
```java
for(int i = 0; i < elementCount; i++) {
    WebElement element = driver.findElement(
        By.xpath("//table/tbody/tr[" + (i + 1) + "]/td"));
    
    // Scroll to element
    driver.executeScript("arguments[0].scrollIntoView()", element);
    
    // Extract data
    String data = element.getText();
    processData(data);
}
```

## Common Test Data

### Opportunity Data
```java
String OPPORTUNITY_NAME = "Salesforce Automation by Praveen";
String CLOSE_DATE_CREATE = "11/13/2021";
String CLOSE_DATE_EDIT = "11/16/2021";
String STAGE_CREATE = "Needs Analysis";
String STAGE_EDIT = "Perception Analysis";
String DELIVERY_STATUS = "In progress";
String DESCRIPTION = "Salesforce";
```

### Legal Entity Data
```java
String ENTITY_NAME = "Testleaf";
String ENTITY_DESCRIPTION = "Salesforce";
String ENTITY_STATUS = "Active";
```

## Performance Tips

### Faster Locators (Preference Order)
```java
driver.findElement(By.id("id"));                    // Fastest
driver.findElement(By.name("name"));                // Fast
driver.findElement(By.className("class"));          // Medium
driver.findElement(By.cssSelector(".class"));       // Medium
driver.findElement(By.xpath("//complex/path"));     // Slowest
```

### Reduce Wait Times
```java
// Instead of long Thread.sleep()
Thread.sleep(10000);

// Use explicit waits
WebDriverWait wait = new WebDriverWait(driver, 10);
wait.until(ExpectedConditions.elementToBeClickable(locator));
```

### Efficient Element Interaction
```java
// Store element reference
WebElement element = driver.findElement(locator);

// Reuse for multiple actions
element.clear();
element.sendKeys("data");
element.click();
```

## Debugging Helpers

### Add Logging
```java
System.out.println("Step: Clicking on element");
driver.findElement(locator).click();
System.out.println("Step: Element clicked successfully");
```

### Capture Element Text
```java
String text = driver.findElement(locator).getText();
System.out.println("Element text: " + text);
```

### Verify Element State
```java
WebElement element = driver.findElement(locator);
System.out.println("Element displayed: " + element.isDisplayed());
System.out.println("Element enabled: " + element.isEnabled());
```

## Test Execution Order

When running the complete suite, tests execute in this order:
1. `CreateOpportunities` (priority=1, runs twice)
2. `EditOpportunity` (depends on CreateOpportunities)
3. `DeleteOpportunity` (priority=2)
4. `CreateLegalEntity` (independent)
5. `VerifySortClone` (independent)

## Quick Troubleshooting

| Issue | Quick Fix |
|-------|-----------|
| Element not found | Add `Thread.sleep(3000)` before interaction |
| Click not working | Use JavaScript: `js.executeScript("arguments[0].click();", element)` |
| Element not visible | Add scroll: `js.executeScript("arguments[0].scrollIntoView()", element)` |
| Login failed | Check credentials in BaseClass.preCondition() |
| Test too slow | Replace Thread.sleep() with WebDriverWait |
| Stale element | Re-find element: `element = driver.findElement(locator)` |

## Essential Imports

```java
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
```

---

**💡 Pro Tips:**
- Always use JavaScript execution for dynamic Salesforce elements
- Prefer explicit waits over Thread.sleep() when possible
- Use descriptive variable names for better code readability
- Add console output for debugging and progress tracking
- Test with different data sets to ensure robustness