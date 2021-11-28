package selBootCamp.week1.day1.copy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOpportunity extends BaseClass {
	
	@Test(dependsOnMethods = "CreateOpportunities")

	public  void editOpp() throws InterruptedException {
		// TODO Auto-generated method stub
		

driver.findElement(By.xpath("//div[@class='slds-r2']")).click();


driver.findElement(By.xpath("//button[text()='View All']")).click();

driver.findElement(By.xpath("//p[text()='Sales']")).click();

Thread.sleep(10000);

 WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));

JavascriptExecutor js = (JavascriptExecutor)driver;

js.executeScript("arguments[0].click();", oppo);

driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys("Salesforce Automation by Praveen",Keys.ENTER);

Thread.sleep(4000);

driver.findElement(By.xpath("//div[@class='slds-media slds-no-space slds-grow']")).click();

Thread.sleep(4000);

driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']")).click();

//driver.executeScript("arguments[0].click())

//6. Click on the Dropdown icon and Select Edit


driver.findElement(By.xpath("//a[@title='Edit']")).click();

driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"11/16/2021");

WebElement dropDown = driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[4]"));

js.executeScript("arguments[0].click();", dropDown);

//WebElement dropDown = driver.findElement(By.xpath("//span[text()='Perception Analysis']"));

//driver.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@title='Perception Analysis']")));

//js.executeScript("arguments[0].click();", dropDown);

driver.findElement(By.xpath("//span[@title='Perception Analysis']")).click();

String stgActual = driver.findElement(By.xpath("//span[@title='Perception Analysis']")).getText();

WebElement deliveryDropDown = driver.findElement(By.xpath("//label[text()='Delivery/Installation Status']/following::input"));

js.executeScript("arguments[0].click();", deliveryDropDown);

driver.findElement(By.xpath("//span[@title='In progress']")).click();

driver.findElement(By.xpath("//textarea[@class='slds-textarea']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"Salesforce");

driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();

WebDriverWait wait = new WebDriverWait(driver,30);

wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));

String actualText = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();


String stgCheck = driver.findElement(By.xpath("//span[text()='Perception Analysis']")).getText();

System.out.println("Text Captured during the selection of dropdown" + stgActual);

System.out.println("Text Captured from the popup after editing" + actualText);

System.out.println("Text Captured after the editing" + stgCheck);


		

	}

}
