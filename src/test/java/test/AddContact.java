package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddContact extends basePage {
	@Test
	public void userShouldBeAbleToAddContact() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[contains(text(),\'Sign\')]")).click();
// Library of Element
		By PAGE_TITLE_LOCATOR = By.xpath("//h2[contains(text(),'Dashboard')]");
		By CRM_SIDE_NAV_LOCATOR = By.xpath("//ul[@id = 'side-menu']/descendant::span[text()= 'CRM']");
		By ADD_CONTACT_SIDE_NAV_LOCATOR = By
				.xpath("//span[text()='CRM']/parent::*/following-sibling::*/descendant::a[contains(@href, 'add')]");
		By FULL_NAME_INPUT_FIELD_LOCATOR = By.id("account");
		By COMPANY_NAME_INPUT_FIELD_LOCATOR = By.id("company");
		By EMAIL_INPUT_FIELD_LOCATOR = By.id("email");
		By PHONE_INPUT_FIELD_LOCATOR = By.id("phone");
		By ADDRESS_INPUT_FIELD_LOCATOR = By.id("address");
		By CITY_INPUT_FIELD_LOCATOR = By.id("city");
		By STATE_REGION_INPUT_FIELD_LOCATOR = By.id("state");
		By ZIP_POSTALCODE_INPUT_FIELD_LOCATOR = By.id("zip");
		By SUBMIT_BUTTON_LOCATOR = By.xpath("//button[@id='submit']");
		By LIST_CONTACT_SIDE_NAV_LOCATOR = By.linkText("List Contacts");
		By NEWLY_ADDED_DISPLAYED_CONTACT_LOCATOR = By.xpath("//table/tbody/descendant::tr[1]/td[2]/a");

		Random rnd = new Random();
		int randomNumber = rnd.nextInt(999);
		String fullName = "Abul" + randomNumber;
		String companyName = "Bata Shoe" + randomNumber;
		String email = "techfio" + randomNumber + "@gmail.com";
		String phone = "4445556" + randomNumber;
		String address = randomNumber + "Street";
		String cityName = "Irving" + randomNumber;
		String stateName = "TX";
		String zip = "75062";
// Explicit wait given to the driver
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE_LOCATOR));
// Open CRM
		driver.findElement(CRM_SIDE_NAV_LOCATOR).click();
		waitForElement(driver, 10, ADD_CONTACT_SIDE_NAV_LOCATOR);
// Click on Add Contact
		driver.findElement(ADD_CONTACT_SIDE_NAV_LOCATOR).click();
// Fill out the form and submit		
//		waitForElement(driver,30,FULL_NAME_INPUT_FIELD_LOCATOR);
		waitForElement(driver, 10, FULL_NAME_INPUT_FIELD_LOCATOR);
				
		driver.findElement(FULL_NAME_INPUT_FIELD_LOCATOR).sendKeys(fullName);
		driver.findElement(COMPANY_NAME_INPUT_FIELD_LOCATOR).sendKeys(companyName);
		driver.findElement(EMAIL_INPUT_FIELD_LOCATOR).sendKeys(email);
		driver.findElement(PHONE_INPUT_FIELD_LOCATOR).sendKeys(phone);
		driver.findElement(ADDRESS_INPUT_FIELD_LOCATOR).sendKeys(address);
		driver.findElement(CITY_INPUT_FIELD_LOCATOR).sendKeys(cityName);
		driver.findElement(STATE_REGION_INPUT_FIELD_LOCATOR).sendKeys(stateName);
		driver.findElement(ZIP_POSTALCODE_INPUT_FIELD_LOCATOR).sendKeys(zip);
		driver.findElement(SUBMIT_BUTTON_LOCATOR).click();
// Click on List Contact	
		driver.findElement(LIST_CONTACT_SIDE_NAV_LOCATOR).click();
// Verify that contact added
		String displayContactName = driver.findElement(NEWLY_ADDED_DISPLAYED_CONTACT_LOCATOR).getText();
		waitForElement(driver, 10, NEWLY_ADDED_DISPLAYED_CONTACT_LOCATOR);
		Assert.assertEquals("Relevant contact name not found", fullName, displayContactName);

		
		driver.close();
		driver.quit();
		

		
	}

}
