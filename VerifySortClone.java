	package selBootCamp.week1.day1.copy;

	import java.util.ArrayList;
import java.util.Collections;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class VerifySortClone  extends BaseClass{	
		@Test

		public void verifySort() throws InterruptedException {
			// TODO Auto-generated method stub
			
	//2. Click on toggle menu button from the left corner

	driver.findElement(By.xpath("//div[@class='slds-r2']")).click();

	//3. Click view All and click Sales from App Launcher


	driver.findElement(By.xpath("//button[text()='View All']")).click();

	Thread.sleep(5000);

	driver.findElement(By.xpath("//p[text()='Sales']")).click();

	Thread.sleep(10000);

	//4. Click on Accounts tab  

	 WebElement account = driver.findElement(By.xpath("//span[text()='Accounts']"));
	 
	 JavascriptExecutor js = (JavascriptExecutor)driver;

	js.executeScript("arguments[0].click();", account);
	
	boolean nextElement = true;
	
	int i=0;
	
	ArrayList<String> accountNames = new ArrayList<String>();
	
	while (nextElement) {
		List<WebElement> list = driver.findElements(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a"));
		System.out.println(i+":"+list.size());
		if(list.size()==0) {
			nextElement = false;
		}
		if (list.size()>0) {
		driver.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a")));
		accountNames.add(i,driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(i+1)+"]/th//a")).getText().toLowerCase());
		i=i+1;
	}
	}
	
	System.out.println(accountNames.size());
	
	Collections.sort(accountNames);
	
	System.out.println(accountNames);
	
	
	//
	
	driver.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//span[text()='Sort']")));
	
	
	WebElement sortButton = driver.findElement(By.xpath("//span[text()='Sort']"));
	
	js.executeScript("arguments[0].click();", sortButton);
	
	Thread.sleep(10000);
	
boolean nextElement1 = true;
	
	int j=0;
	
ArrayList<String> accountNames1 = new ArrayList<String>();
	
	while (nextElement1) {
		List<WebElement> list1 = driver.findElements(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a"));
		System.out.println(j+":"+list1.size());
		if(list1.size()==0) {
			nextElement1 = false;
		}
		if (list1.size()>0) {
		driver.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a")));
		accountNames1.add(j,driver.findElement(By.xpath("//span[text()='Account Name']/ancestor::table/tbody/tr["+(j+1)+"]/th//a")).getText().toLowerCase());
		j=j+1;
	}
	}
	
	System.out.println("Sorted List after clicking on AccountNames Sort button"+accountNames1);
	
if(accountNames.equals(accountNames1)==true) {
	
	System.out.println("Sorted Lists Match");
}

else {
	
	System.out.println("Sorted Lists Don't Match");
}
	
	}

}
