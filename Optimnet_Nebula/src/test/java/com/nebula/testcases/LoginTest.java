package com.nebula.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.nebula.BaseTestHo.BaseTest;
import com.nebula.qa.POM.LoginPage;
import com.nebula.utilities.WebActionUtils;

public class LoginTest extends BaseTest
{
	public WebDriver driver;
	LoginPage loginpage;
	WebActionUtils webActionUtils;
	
	@BeforeMethod
	public void setUp()
	{
		driver = initializeBrowserAndOpenApplication(readPropertyValue("browser"));
		webActionUtils = new WebActionUtils(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() 
	{
		loginpage = new LoginPage(driver,webActionUtils);
		loginpage.setUserType("SuperAdmin");
		loginpage.setUsername("admin");
		loginpage.setPassword("test");
		loginpage.clickReset();
		driver.switchTo().alert().accept();
		loginpage.setPassword("test");
		loginpage.clickLogin();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Welcome! admin']")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifyLoginWithNoCredentials() 
	{
		loginpage = new LoginPage(driver,webActionUtils);
		loginpage.setUsername("");
		loginpage.setPassword("");
		loginpage.clickReset();
		String actualWarningMSG = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		String expectedWarningMSG = "Please Enter the Username";
		Assert.assertTrue(actualWarningMSG.contains(expectedWarningMSG),"Please Enter the Username popup is not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidCredentials() 
	{
		loginpage = new LoginPage(driver,webActionUtils);
		loginpage.setUserType("SuperAdmin");
		loginpage.setUsername("admin123");
		loginpage.setPassword("test");
		loginpage.clickReset();
		Assert.assertTrue(driver.findElement(By.id("lblError")).isDisplayed(),"Check your UserName or Password is not dispalyed");
	}
}
