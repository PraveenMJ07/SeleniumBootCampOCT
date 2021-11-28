package selBootCamp.week1.day1.copy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public ChromeDriver driver;
	
	@BeforeMethod
	
	public void preCondition() {
		
		ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        
        WebDriverManager.chromedriver().setup();
        
         driver = new ChromeDriver(options);
	
	
driver.get("https://login.salesforce.com/");

driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

driver.findElement(By.id("username")).sendKeys("makaia@testleaf.com");

driver.findElement(By.id("password")).sendKeys("SelBootcamp$1234");

driver.findElement(By.id("Login")).click();
		
	}

}
