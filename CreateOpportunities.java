package selBootCamp.week1.day1.copy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunities extends BaseClass {
	
	@Test(priority=1,invocationCount=2)

	public void createOpportunities() throws InterruptedException {
		// TODO Auto-generated method stub
		
		
driver.findElement(By.xpath("//div[@class='slds-r2']")).click();


driver.findElement(By.xpath("//button[text()='View All']")).click();

Thread.sleep(4000);

driver.findElement(By.xpath("//p[text()='Sales']")).click();

Thread.sleep(10000);

 WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));

JavascriptExecutor js = (JavascriptExecutor)driver;

js.executeScript("arguments[0].click();", oppo);


driver.findElement(By.xpath("//div[text()='New']")).click();

Thread.sleep(10000);


driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Salesforce Automation by Praveen");

String expectedText = "Salesforce Automation by Praveen";

driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("11/13/2021");

 WebElement dropDown = driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[3]"));
 
 js.executeScript("arguments[0].click();", dropDown);
 
driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();

driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

WebDriverWait wait = new WebDriverWait(driver,30);

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='\"Salesforce Automation by Praveen\"']")));

String actualText = driver.findElement(By.xpath("//div[@title='\"Salesforce Automation by Praveen\"']")).getText();



System.out.println(expectedText);

System.out.println(actualText);



if((actualText).equalsIgnoreCase(expectedText)){
	
	
	System.out.println("TestCase is Successfully Completed");
}else {
	
	System.out.println("TestCase Failed");
}

	}
}
