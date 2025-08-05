# Technical Reference Guide - Salesforce Automation Framework

## Quick Reference

### Class Hierarchy
```
BaseClass
├── CreateLegalEntity
├── CreateOpportunities  
├── DeleteOpportunity
├── EditOpportunity
└── VerifySortClone
```

### Package Structure
```
selBootCamp.week1.day1.copy/
├── BaseClass.java           - Base setup and configuration
├── CreateLegalEntity.java   - Legal entity creation tests
├── CreateOpportunities.java - Opportunity creation tests
├── DeleteOpportunity.java   - Opportunity deletion tests
├── EditOpportunity.java     - Opportunity editing tests
└── VerifySortClone.java     - Account sorting verification
```

---

## Method Signatures and Implementation Details

### BaseClass

```java
public class BaseClass {
    // Public Fields
    public ChromeDriver driver;
    
    // Public Methods
    @BeforeMethod
    public void preCondition()
}
```

#### `preCondition()` Implementation
```java
@BeforeMethod
public void preCondition() {
    // Chrome options configuration
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    
    // WebDriver setup
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver(options);
    
    // Navigation and configuration
    driver.get("https://login.salesforce.com/");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    // Automated login
    driver.findElement(By.id("username")).sendKeys("makaia@testleaf.com");
    driver.findElement(By.id("password")).sendKeys("SelBootcamp$1234");
    driver.findElement(By.id("Login")).click();
}
```

---

### CreateLegalEntity

```java
public class CreateLegalEntity extends BaseClass {
    @Test
    public void createLegal() throws InterruptedException
}
```

#### Complete Implementation
```java
@Test
public void createLegal() throws InterruptedException {
    // Navigate to App Launcher
    driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
    driver.findElement(By.xpath("//button[text()='View All']")).click();
    
    Thread.sleep(10000);
    
    // Navigate to Legal Entities
    driver.executeScript("arguments[0].scrollIntoView()", 
        driver.findElement(By.xpath("//p[text()='Legal Entities']")));
    driver.findElement(By.xpath("//p[text()='Legal Entities']")).click();
    driver.findElement(By.xpath("//a[@title='Legal Entities']/following::a")).click();
    
    // Create new Legal Entity
    driver.executeScript("arguments[0].click();", 
        driver.findElement(By.xpath("//span[text()='New Legal Entity']")));
    
    // Fill form data
    driver.findElement(By.xpath("(//input[@class=' input'][@type='text'])[2]"))
        .sendKeys("Testleaf");
    driver.findElement(By.xpath("//textarea[@class=' textarea']"))
        .sendKeys("Salesforce");
    
    // Set status to Active
    driver.findElement(By.xpath("//a[@class='select'][@aria-disabled='false']")).click();
    driver.findElement(By.xpath("//a[text()='Active']")).click();
    
    // Save and capture results
    driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
    Thread.sleep(3000);
    
    // Error handling and output
    String errorText = driver.findElement(By.xpath("//div[@class='genericNotification']")).getText();
    String errorMessage = driver.findElement(By.xpath("//ul[@class='errorsList']")).getText();
    
    System.out.println(errorText);
    System.out.println(errorMessage);
}
```

---

### CreateOpportunities

```java
public class CreateOpportunities extends BaseClass {
    @Test(priority=1, invocationCount=2)
    public void createOpportunities() throws InterruptedException
}
```

#### Complete Implementation
```java
@Test(priority=1, invocationCount=2)
public void createOpportunities() throws InterruptedException {
    // Navigate to Sales module
    driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
    driver.findElement(By.xpath("//button[text()='View All']")).click();
    Thread.sleep(4000);
    
    driver.findElement(By.xpath("//p[text()='Sales']")).click();
    Thread.sleep(10000);
    
    // Navigate to Opportunities
    WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", oppo);
    
    // Create new opportunity
    driver.findElement(By.xpath("//div[text()='New']")).click();
    Thread.sleep(10000);
    
    // Fill opportunity data
    driver.findElement(By.xpath("//input[@name='Name']"))
        .sendKeys("Salesforce Automation by Praveen");
    String expectedText = "Salesforce Automation by Praveen";
    
    driver.findElement(By.xpath("//input[@name='CloseDate']"))
        .sendKeys("11/13/2021");
    
    // Select stage
    WebElement dropDown = driver.findElement(
        By.xpath("(//input[@class='slds-input slds-combobox__input'])[3]"));
    js.executeScript("arguments[0].click();", dropDown);
    driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
    
    // Save and validate
    driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//div[@title='\"Salesforce Automation by Praveen\"']")));
    
    String actualText = driver.findElement(
        By.xpath("//div[@title='\"Salesforce Automation by Praveen\"']")).getText();
    
    // Validation logic
    System.out.println(expectedText);
    System.out.println(actualText);
    
    if((actualText).equalsIgnoreCase(expectedText)) {
        System.out.println("TestCase is Successfully Completed");
    } else {
        System.out.println("TestCase Failed");
    }
}
```

---

### DeleteOpportunity

```java
public class DeleteOpportunity extends BaseClass {
    @Test(priority=2)
    public void deleteOpp() throws InterruptedException
}
```

#### Complete Implementation
```java
@Test(priority=2)
public void deleteOpp() throws InterruptedException {
    // Navigate to Sales and Opportunities
    driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
    driver.findElement(By.xpath("//button[text()='View All']")).click();
    driver.findElement(By.xpath("//p[text()='Sales']")).click();
    Thread.sleep(10000);
    
    WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", oppo);
    
    // Search for opportunity
    driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"))
        .sendKeys("Salesforce Automation by Praveen", Keys.ENTER);
    Thread.sleep(4000);
    
    // Open opportunity and action menu
    driver.findElement(By.xpath("//div[@class='slds-media slds-no-space slds-grow']")).click();
    Thread.sleep(4000);
    driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']")).click();
    
    // Delete opportunity
    driver.findElement(By.xpath("//a[@title='Delete']")).click();
    driver.findElement(By.xpath("//span[text()='Delete']")).click();
    Thread.sleep(7000);
    
    // Verify deletion
    driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"))
        .sendKeys("Salesforce Automation by Praveen", Keys.ENTER);
}
```

---

### EditOpportunity

```java
public class EditOpportunity extends BaseClass {
    @Test(dependsOnMethods = "CreateOpportunities")
    public void editOpp() throws InterruptedException
}
```

#### Complete Implementation
```java
@Test(dependsOnMethods = "CreateOpportunities")
public void editOpp() throws InterruptedException {
    // Navigate to opportunity
    driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
    driver.findElement(By.xpath("//button[text()='View All']")).click();
    driver.findElement(By.xpath("//p[text()='Sales']")).click();
    Thread.sleep(10000);
    
    WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", oppo);
    
    // Search and select opportunity
    driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"))
        .sendKeys("Salesforce Automation by Praveen", Keys.ENTER);
    Thread.sleep(4000);
    
    driver.findElement(By.xpath("//div[@class='slds-media slds-no-space slds-grow']")).click();
    Thread.sleep(4000);
    driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']")).click();
    
    // Open edit form
    driver.findElement(By.xpath("//a[@title='Edit']")).click();
    
    // Edit close date
    driver.findElement(By.xpath("//input[@name='CloseDate']"))
        .sendKeys(Keys.chord(Keys.CONTROL,"a"),"11/16/2021");
    
    // Change stage
    WebElement dropDown = driver.findElement(
        By.xpath("(//input[@class='slds-input slds-combobox__input'])[4]"));
    js.executeScript("arguments[0].click();", dropDown);
    driver.findElement(By.xpath("//span[@title='Perception Analysis']")).click();
    
    String stgActual = driver.findElement(By.xpath("//span[@title='Perception Analysis']")).getText();
    
    // Update delivery status
    WebElement deliveryDropDown = driver.findElement(
        By.xpath("//label[text()='Delivery/Installation Status']/following::input"));
    js.executeScript("arguments[0].click();", deliveryDropDown);
    driver.findElement(By.xpath("//span[@title='In progress']")).click();
    
    // Update description
    driver.findElement(By.xpath("//textarea[@class='slds-textarea']"))
        .sendKeys(Keys.chord(Keys.CONTROL,"a"),"Salesforce");
    
    // Save changes
    driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    
    // Validation
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//span[@data-aura-class='forceActionsText']")));
    
    String actualText = driver.findElement(
        By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
    String stgCheck = driver.findElement(
        By.xpath("//span[text()='Perception Analysis']")).getText();
    
    // Output validation results
    System.out.println("Text Captured during the selection of dropdown" + stgActual);
    System.out.println("Text Captured from the popup after editing" + actualText);
    System.out.println("Text Captured after the editing" + stgCheck);
}
```

---

### VerifySortClone

```java
public class VerifySortClone extends BaseClass {
    @Test
    public void verifySort() throws InterruptedException
}
```

#### Complete Implementation
```java
@Test
public void verifySort() throws InterruptedException {
    // Navigate to Accounts
    driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
    driver.findElement(By.xpath("//button[text()='View All']")).click();
    Thread.sleep(5000);
    
    driver.findElement(By.xpath("//p[text()='Sales']")).click();
    Thread.sleep(10000);
    
    WebElement account = driver.findElement(By.xpath("//span[text()='Accounts']"));
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click();", account);
    
    // Extract account names before sorting
    boolean nextElement = true;
    int i = 0;
    ArrayList<String> accountNames = new ArrayList<String>();
    
    while (nextElement) {
        List<WebElement> list = driver.findElements(
            By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a"));
        System.out.println(i+":"+list.size());
        
        if(list.size() == 0) {
            nextElement = false;
        }
        if (list.size() > 0) {
            driver.executeScript("arguments[0].scrollIntoView()", 
                driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a")));
            accountNames.add(i, driver.findElement(
                By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a"))
                .getText().toLowerCase());
            i = i + 1;
        }
    }
    
    System.out.println(accountNames.size());
    
    // Manual sorting
    Collections.sort(accountNames);
    System.out.println(accountNames);
    
    // Click sort button
    driver.executeScript("arguments[0].scrollIntoView()", 
        driver.findElement(By.xpath("//span[text()='Sort']")));
    WebElement sortButton = driver.findElement(By.xpath("//span[text()='Sort']"));
    js.executeScript("arguments[0].click();", sortButton);
    Thread.sleep(10000);
    
    // Extract account names after UI sorting
    boolean nextElement1 = true;
    int j = 0;
    ArrayList<String> accountNames1 = new ArrayList<String>();
    
    while (nextElement1) {
        List<WebElement> list1 = driver.findElements(
            By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a"));
        System.out.println(j+":"+list1.size());
        
        if(list1.size() == 0) {
            nextElement1 = false;
        }
        if (list1.size() > 0) {
            driver.executeScript("arguments[0].scrollIntoView()", 
                driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a")));
            accountNames1.add(j, driver.findElement(
                By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a"))
                .getText().toLowerCase());
            j = j + 1;
        }
    }
    
    System.out.println("Sorted List after clicking on AccountNames Sort button" + accountNames1);
    
    // Compare sorted lists
    if(accountNames.equals(accountNames1) == true) {
        System.out.println("Sorted Lists Match");
    } else {
        System.out.println("Sorted Lists Don't Match");
    }
}
```

---

## XPath Locator Reference

### Common Navigation Elements
```java
// App Launcher
"//div[@class='slds-r2']"

// View All Button
"//button[text()='View All']"

// Sales Module
"//p[text()='Sales']"

// Opportunities Tab
"//span[text()='Opportunities']"

// Accounts Tab
"//span[text()='Accounts']"

// Legal Entities
"//p[text()='Legal Entities']"
```

### Opportunity-Specific Elements
```java
// New Opportunity Button
"//div[text()='New']"

// Opportunity Name Field
"//input[@name='Name']"

// Close Date Field
"//input[@name='CloseDate']"

// Stage Dropdown
"(//input[@class='slds-input slds-combobox__input'])[3]"

// Save Button
"//button[@name='SaveEdit']"

// Search Input
"//input[@name='Opportunity-search-input']"

// Action Dropdown
"//div[@class='forceVirtualActionMarker forceVirtualAction']"

// Edit Link
"//a[@title='Edit']"

// Delete Link
"//a[@title='Delete']"
```

### Legal Entity Elements
```java
// New Legal Entity Button
"//span[text()='New Legal Entity']"

// Name Input
"(//input[@class=' input'][@type='text'])[2]"

// Description Textarea
"//textarea[@class=' textarea']"

// Status Dropdown
"//a[@class='select'][@aria-disabled='false']"

// Active Option
"//a[text()='Active']"

// Save Button
"(//span[text()='Save'])[2]"

// Error Notification
"//div[@class='genericNotification']"

// Error List
"//ul[@class='errorsList']"
```

### Account Sorting Elements
```java
// Account Name Column
"//span[text()='Account Name']"

// Table Row Pattern
"//span[text()='Account Name']/ancestor::table/tbody/tr[{index}]/th//a"

// Sort Button
"//span[text()='Sort']"
```

---

## Wait Strategy Implementation

### Implicit Wait (BaseClass)
```java
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
```

### Explicit Wait (CreateOpportunities, EditOpportunity)
```java
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
```

### Static Wait (All Classes)
```java
Thread.sleep(milliseconds); // Used for UI stabilization
```

---

## JavaScript Execution Patterns

### Element Click
```java
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", element);
```

### Scroll Into View
```java
driver.executeScript("arguments[0].scrollIntoView()", element);
```

### Combined Approach
```java
WebElement element = driver.findElement(locator);
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].scrollIntoView();", element);
js.executeScript("arguments[0].click();", element);
```

---

## Error Handling Patterns

### Exception Declaration
```java
public void methodName() throws InterruptedException {
    // Method implementation
}
```

### Error Message Capture
```java
try {
    String errorText = driver.findElement(errorLocator).getText();
    System.out.println(errorText);
} catch (Exception e) {
    // Handle gracefully
}
```

### Validation Output
```java
if (condition) {
    System.out.println("Success message");
} else {
    System.out.println("Failure message");
}
```

---

## TestNG Annotations Reference

### Method-Level Annotations
```java
@Test                           // Basic test method
@Test(priority=1)              // Execution priority
@Test(invocationCount=2)       // Run multiple times
@Test(dependsOnMethods="method") // Method dependency
@BeforeMethod                  // Setup before each test
```

### Annotation Combinations
```java
@Test(priority=1, invocationCount=2)
@Test(dependsOnMethods = "CreateOpportunities")
```

---

## Data Management Patterns

### Test Data Constants
```java
// Opportunity Data
String opportunityName = "Salesforce Automation by Praveen";
String closeDate = "11/13/2021";
String stage = "Needs Analysis";

// Legal Entity Data
String entityName = "Testleaf";
String description = "Salesforce";
String status = "Active";
```

### Dynamic Data Extraction
```java
// List processing
ArrayList<String> dataList = new ArrayList<String>();
while (hasMoreElements) {
    String data = element.getText().toLowerCase();
    dataList.add(data);
}
```

### Data Comparison
```java
// String comparison
if (actual.equalsIgnoreCase(expected)) {
    // Success logic
}

// List comparison
if (list1.equals(list2)) {
    // Lists match
}
```

---

**Note**: This technical reference provides complete implementation details for all public methods and components in the Salesforce automation framework. Use this guide for development, debugging, and extension of the test suite.