package com.nebula.qa.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.nebula.utilities.WebActionUtils;

public class BasePage 
{
	public WebDriver driver;
	public WebActionUtils webActionUtils;
	
	public BasePage(WebDriver driver,WebActionUtils webActionUtils) 
	{
		this.driver=driver;
		this.webActionUtils=webActionUtils;
		PageFactory.initElements(driver, this);
	}
}
