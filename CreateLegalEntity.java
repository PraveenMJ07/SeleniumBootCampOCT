package selBootCamp.week1.day1.copy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLegalEntity extends BaseClass {
	
	@Test

	public void createLegal() throws InterruptedException{
		// TODO Auto-generated method stub
	

driver.findElement(By.xpath("//div[@class='slds-r2']")).click();


driver.findElement(By.xpath("//button[text()='View All']")).click();

Thread.sleep(10000);


//driver.findElement(By.xpath("//button[text()='View All']")).click();

driver.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//p[text()='Legal Entities']")));

driver.findElement(By.xpath("//p[text()='Legal Entities']")).click();

driver.findElement(By.xpath("//a[@title='Legal Entities']/following::a")).click();


//driver.findElement(By.xpath("//span[text()='New Legal Entity']")).click();

driver.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='New Legal Entity']")));

driver.findElement(By.xpath("(//input[@class=' input'][@type='text'])[2]")).sendKeys("Testleaf");

driver.findElement(By.xpath("//textarea[@class=' textarea']")).sendKeys("Salesforce");

driver.findElement(By.xpath("//a[@class='select'][@aria-disabled='false']")).click();

driver.findElement(By.xpath("//a[text()='Active']")).click();

driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();

Thread.sleep(3000);


String errorText = driver.findElement(By.xpath("//div[@class='genericNotification']")).getText();

System.out.println(errorText);

String errorMessage = driver.findElement(By.xpath("//ul[@class='errorsList']")).getText();

System.out.println(errorMessage);











		
	}

}
