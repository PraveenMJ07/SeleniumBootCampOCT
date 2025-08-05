# Salesforce Automation Testing Framework

[![Java](https://img.shields.io/badge/Java-8%2B-orange)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.x-green)](https://selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.x-blue)](https://testng.org/)
[![WebDriverManager](https://img.shields.io/badge/WebDriverManager-5.x-purple)](https://github.com/bonigarcia/webdrivermanager)

A comprehensive Selenium WebDriver-based automation testing framework designed for Salesforce CRM operations. This framework provides automated testing capabilities for creating, editing, deleting, and verifying various Salesforce entities including Legal Entities, Opportunities, and Accounts.

## 🚀 Quick Start

```bash
# 1. Clone the repository
git clone <repository-url>

# 2. Open in your Java IDE
# Import as existing project

# 3. Run a simple test
# Right-click CreateOpportunities.java → Run as TestNG Test
```

## 📋 Features

- ✅ **Legal Entity Management**: Create and validate legal entity records
- ✅ **Opportunity Lifecycle**: Complete CRUD operations for opportunities
- ✅ **Account Verification**: Validate sorting and data integrity
- ✅ **Automated Login**: Seamless Salesforce authentication
- ✅ **Cross-platform**: Works on Windows, macOS, and Linux
- ✅ **Robust Wait Strategies**: Handles dynamic UI elements
- ✅ **JavaScript Execution**: Manages complex Salesforce interactions
- ✅ **Error Handling**: Comprehensive validation and reporting

## 🏗️ Framework Architecture

```
BaseClass (Foundation)
├── Browser setup & configuration
├── Salesforce login automation
├── Common utilities
└── WebDriver management

Test Classes (Business Logic)
├── CreateLegalEntity     - Legal entity creation
├── CreateOpportunities   - Opportunity creation
├── EditOpportunity      - Opportunity modification
├── DeleteOpportunity    - Opportunity deletion
└── VerifySortClone      - Account sorting verification
```

## 📚 Documentation

| Document | Purpose | Target Audience |
|----------|---------|-----------------|
| **[API Documentation](API_DOCUMENTATION.md)** | Complete API reference with examples | Developers, QA Engineers |
| **[Technical Reference](TECHNICAL_REFERENCE.md)** | Detailed implementation guide | Developers |
| **[User Guide](USER_GUIDE.md)** | Step-by-step usage instructions | QA Engineers, Testers |
| **[Quick Reference](QUICK_REFERENCE.md)** | Essential methods and patterns | All Users |

## 🛠️ Prerequisites

### System Requirements
- **Java**: Version 8 or higher
- **Chrome Browser**: Latest version
- **IDE**: Eclipse, IntelliJ IDEA, or similar
- **Internet**: For Salesforce access

### Dependencies
- Selenium WebDriver 4.x
- TestNG 7.x
- WebDriverManager 5.x
- Chrome WebDriver (managed automatically)

### Salesforce Access
- Valid Salesforce org credentials
- Permissions for CRUD operations on:
  - Legal Entities
  - Opportunities
  - Accounts

## 🏃‍♂️ Running Tests

### Individual Test Execution
```java
// Right-click any test class and select "Run as TestNG Test"
CreateOpportunities.java    // Creates opportunities
EditOpportunity.java       // Edits opportunities  
DeleteOpportunity.java     // Deletes opportunities
CreateLegalEntity.java     // Creates legal entities
VerifySortClone.java       // Verifies account sorting
```

### Complete Test Suite
Create `testng.xml`:
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

## 🎯 Test Scenarios

### Opportunity Management Workflow
```java
1. CreateOpportunities    → Creates "Salesforce Automation by Praveen"
2. EditOpportunity       → Updates stage to "Perception Analysis"  
3. DeleteOpportunity     → Removes the opportunity
```

### Legal Entity Creation
```java
CreateLegalEntity        → Creates "Testleaf" entity with validation
```

### Data Verification
```java
VerifySortClone         → Validates account sorting functionality
```

## 🔧 Configuration

### Default Salesforce Settings
```java
// Configured in BaseClass.java
URL: https://login.salesforce.com/
Username: makaia@testleaf.com
Password: SelBootcamp$1234
Implicit Wait: 30 seconds
Browser: Chrome with notifications disabled
```

### Customizing Test Data
```java
// In CreateOpportunities.java
String opportunityName = "Your Custom Name";
String closeDate = "12/31/2024";
String stage = "Your Stage";

// In CreateLegalEntity.java  
String entityName = "Your Entity";
String description = "Your Description";
```

## 📊 Sample Output

```
[INFO] Starting Salesforce Automation Tests
[INFO] Browser: Chrome v120.0.6099.129

=== Opportunity Management Tests ===
✅ PASS: Created opportunity 'Salesforce Automation by Praveen'
✅ PASS: Updated opportunity stage to 'Perception Analysis'  
✅ PASS: Deleted opportunity successfully

=== Legal Entity Creation Test ===
✅ PASS: Created legal entity 'Testleaf'
⚠️  WARN: Validation messages captured

=== Account Sorting Verification ===
✅ PASS: Extracted 25 account names
✅ PASS: Manual sort completed
✅ PASS: UI sort completed
✅ PASS: Sorted lists match

=== Test Summary ===
Total Tests: 5 | Passed: 5 | Failed: 0 | Execution Time: 4m 32s
```

## 🔍 Key Components

### BaseClass
- **Purpose**: Foundation for all test classes
- **Features**: Browser setup, Salesforce login, common utilities
- **Usage**: Extended by all test classes

### Test Classes
| Class | Functionality | Dependencies |
|-------|---------------|--------------|
| `CreateOpportunities` | Creates opportunities (runs 2x) | None |
| `EditOpportunity` | Edits existing opportunities | CreateOpportunities |
| `DeleteOpportunity` | Deletes opportunities | None |
| `CreateLegalEntity` | Creates legal entities | None |
| `VerifySortClone` | Verifies account sorting | None |

## 🧪 Common Patterns

### Navigation Pattern
```java
driver.findElement(By.xpath("//div[@class='slds-r2']")).click();      // App Launcher
driver.findElement(By.xpath("//button[text()='View All']")).click();  // View All
driver.findElement(By.xpath("//p[text()='Sales']")).click();          // Sales module
```

### JavaScript Execution
```java
WebElement element = driver.findElement(locator);
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click();", element);
```

### Wait Strategies
```java
// Implicit Wait (BaseClass)
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

// Explicit Wait  
WebDriverWait wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.elementToBeClickable(locator));
```

## 🐛 Troubleshooting

### Common Issues

| Issue | Solution |
|-------|----------|
| Element not found | Add `Thread.sleep(3000)` or use explicit wait |
| Click not working | Use JavaScript execution |
| Login failure | Verify credentials in BaseClass |
| Slow performance | Replace Thread.sleep() with WebDriverWait |

### Debug Mode
```java
// Add logging for debugging
System.out.println("Step: Performing action");
performAction();
System.out.println("Step: Action completed");
```

## 🔄 Test Execution Order

When running the complete suite:
1. **CreateOpportunities** (priority=1, invocationCount=2)
2. **EditOpportunity** (dependsOnMethods = "CreateOpportunities")
3. **DeleteOpportunity** (priority=2)
4. **CreateLegalEntity** (independent)
5. **VerifySortClone** (independent)

## 📈 Best Practices

### Code Quality
- Use descriptive variable names
- Add meaningful comments
- Implement proper error handling
- Follow consistent naming conventions

### Test Design  
- Keep tests independent when possible
- Use appropriate wait strategies
- Validate expected outcomes
- Clean up test data

### Performance
- Minimize Thread.sleep() usage
- Use efficient locators (ID > Name > CSS > XPath)
- Store element references for reuse
- Optimize wait times

## 🤝 Contributing

1. Follow existing code patterns and conventions
2. Add comprehensive comments for new functionality
3. Test thoroughly across different scenarios
4. Update documentation for any new features

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

### Documentation Quick Links
- **Getting Started**: [User Guide](USER_GUIDE.md)
- **API Reference**: [API Documentation](API_DOCUMENTATION.md)  
- **Implementation Details**: [Technical Reference](TECHNICAL_REFERENCE.md)
- **Quick Lookup**: [Quick Reference](QUICK_REFERENCE.md)

### Common Resources
- **TestNG Documentation**: https://testng.org/doc/
- **Selenium WebDriver Guide**: https://selenium.dev/documentation/
- **Salesforce Developer Docs**: https://developer.salesforce.com/

### Need Help?
1. Check the troubleshooting section in the User Guide
2. Review the Quick Reference for common patterns
3. Verify all prerequisites are met
4. Ensure Salesforce org access and permissions

---

**Framework Version**: 1.0  
**Last Updated**: December 2024  
**Compatible With**: Selenium 4.x, TestNG 7.x, Java 8+