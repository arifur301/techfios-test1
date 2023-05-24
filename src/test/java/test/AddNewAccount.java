package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddNewAccount {

	WebDriver driver;

	@Test
	public void userShouldBeAbleToTransferMoney() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Bank & Cash')]")).click();
		driver.findElement(By.linkText("New Account")).click();
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(999);
		driver.findElement(By.name("account")).sendKeys("Bata" + rnd);
		driver.findElement(By.name("description")).sendKeys("Abul" + rnd);
		driver.findElement(By.id("balance")).sendKeys("$" + rnd);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		By ALLERT_MSG_LOCATOR = By.xpath("//div[@class='alert alert-success fade in']");
		waitForElement(driver, 10, ALLERT_MSG_LOCATOR);
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		//Adding a comment
	}
}