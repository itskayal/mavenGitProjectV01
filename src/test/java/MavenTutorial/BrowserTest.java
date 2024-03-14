package MavenTutorial;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTest {
	
WebDriver driver;
	
	@BeforeTest
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void TestGoogle() throws Exception
	{
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("testNg");
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "testNg - Google Search";
		//CaptureScreenShots("google.png");
		System.out.println(actualTitle +"   " + expectedTitle);
		assertEquals(actualTitle, expectedTitle,"title maching");
		
	}
	@Test
	public void TestOrangeHRMLogin() throws Exception
	{
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
        //wait.until( (WebDriver d ) -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        
        System.out.println("page load completed");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
        
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		//input[@name='password']
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		
		//WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()=' Login ']")));
		
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";
		//CaptureScreenShots("orangehrmlive.png");
		System.out.println(actualTitle +"   " + expectedTitle);
		assertEquals(actualTitle, expectedTitle, "title Matching");
		
		
	}

	@AfterTest
	public void TearDown()
	{
		
		driver.quit();
	}


}
