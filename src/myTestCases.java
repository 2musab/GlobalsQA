import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	int randomAmount = rand.nextInt(100);
	int newRandomAmount = rand.nextInt(100) * rand.nextInt(1000);

	@BeforeTest
	public void mySetup() {

		driver.get("https://globalsqa.com/angularJs-protractor/BankingProject/#/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(priority = 1)
	public void login() throws InterruptedException {

		WebElement loginButton = driver.findElement(By.cssSelector("button[ng-click='customer()']"));
		loginButton.click();

	}

	@Test(priority = 2)
	public void customerName() throws InterruptedException {

		WebElement userSelect = driver.findElement(By.id("userSelect"));
		userSelect.click();

		List<WebElement> userSelectList = userSelect.findElements(By.tagName("option"));

		int userSelectListSize = userSelectList.size();
		int randomIndex = rand.nextInt(userSelectListSize);

		if (randomIndex == 0) {

			randomIndex = randomIndex + 1;
			WebElement randomName = userSelectList.get(randomIndex);
			randomName.click();

		}

		else {

			WebElement randomName = userSelectList.get(randomIndex);
			randomName.click();
		}
		WebElement submit = driver.findElement(By.cssSelector(".btn.btn-default"));
		submit.click();

	}

	@Test(priority = 3)
	public void deposit() throws InterruptedException {

		WebElement deposit = driver.findElement(By.cssSelector(".btn.btn-lg.tab[ng-class='btnClass2']"));
		deposit.click();

		Thread.sleep(500);

		String strAmount = String.valueOf(randomAmount);

		System.out.println("deposit : " + strAmount);

		WebElement amountDeposit = driver.findElement(By.cssSelector("input[placeholder='amount']"));
		amountDeposit.sendKeys(strAmount);

		WebElement submitDeposit = driver.findElement(By.cssSelector("button[type='submit']"));
		submitDeposit.click();

		Thread.sleep(500);

	}

	@Test(priority = 4)
	public void withdraw() throws InterruptedException {

		WebElement withdraw = driver.findElement(By.cssSelector(".btn.btn-lg.tab[ng-class='btnClass3']"));
		withdraw.click();

		Thread.sleep(500);

		String strAmount = String.valueOf(randomAmount);

		System.out.println("withdraw : " + strAmount);

		WebElement amountWithdraw = driver.findElement(By.tagName("input"));
		amountWithdraw.sendKeys(strAmount);

		WebElement submitWithdraw = driver.findElement(By.cssSelector("button[type='submit']"));
		submitWithdraw.click();

	}
	
	@Test (priority = 5)
	public void balance() {
		
		String actualBalance = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > strong:nth-child(2)")).getText();
		String expectedBalance = "0";
		
		Assert.assertEquals(actualBalance, expectedBalance);
	}

}