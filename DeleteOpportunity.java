package selBootCamp.week1.day1.copy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOpportunity extends BaseClass {
	
	@Test(priority=2)

		public void deleteOpp() throws InterruptedException {
			// TODO Auto-generated method stub
			
				//2. Click on toggle menu button from the left corner

	driver.findElement(By.xpath("//div[@class='slds-r2']")).click();
	
	//3. Click view All and click Sales from App Launcher


	driver.findElement(By.xpath("//button[text()='View All']")).click();

	driver.findElement(By.xpath("//p[text()='Sales']")).click();

	Thread.sleep(10000);
	
	//4. Click on Opportunity tab 

	 WebElement oppo = driver.findElement(By.xpath("//span[text()='Opportunities']"));

	JavascriptExecutor js = (JavascriptExecutor)driver;

	js.executeScript("arguments[0].click();", oppo);
	
	//5. Search the Opportunity 'Salesforce Automation by Your Name'

	driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys("Salesforce Automation by Praveen",Keys.ENTER);

	Thread.sleep(4000);
	
	//6. Click on  the Dropdown icon and Select Delete

	driver.findElement(By.xpath("//div[@class='slds-media slds-no-space slds-grow']")).click();

	Thread.sleep(4000);

	driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']")).click();

	//7. Verify Whether Opportunity is Deleted using Opportunity Name	
		
	driver.findElement(By.xpath("//a[@title='Delete']")).click();
	
	driver.findElement(By.xpath("//span[text()='Delete']")).click();
	
	Thread.sleep(7000);
	
	driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys("Salesforce Automation by Praveen",Keys.ENTER);

		
			
	}

}
