package com.nebula.qa.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.nebula.utilities.WebActionUtils;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils);
	}
	
	@FindBy(id= "ddlstUsertype")
	private WebElement userDD;
	
	@FindBy(id = "txtUsername")
	private WebElement usernameTB;
	
	@FindBy(id = "txtpassword")
	private WebElement passwordTB;
	
	@FindBy(id = "btnClearLogin")
	private WebElement resetBTN;
	
	@FindBy(id = "btnLogin")
	private WebElement loginBTN;
	
	@FindBy(id="lblError")
	private WebElement loginError;
	
	public void setUserType(String userType)
	{
		webActionUtils.selectByText(userDD, userType);
	}
	
	public void setUsername(String username)
	{
		webActionUtils.enterData(usernameTB, username);
	}
	
	public void setPassword(String password)
	{
		webActionUtils.enterData(passwordTB, password);
	}
	
	public void clickReset()
	{
		webActionUtils.elementClick(resetBTN);
	}
	
	public void clickLogin()
	{
		webActionUtils.elementClick(loginBTN);
	}
	
	public WebElement errorMsg()
	{
		return loginError;
	}
	
	public void login(String username, String password)
	{
		webActionUtils.enterData(usernameTB, username);
		webActionUtils.enterData(passwordTB, password);
		webActionUtils.elementClick(resetBTN);
		webActionUtils.acceptAlert(driver);
		webActionUtils.enterData(passwordTB, password);
		webActionUtils.elementClick(loginBTN);
	}	
}
