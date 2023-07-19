package com.nebula.qa.POM;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.nebula.utilities.WebActionUtils;

public class HomePage extends BasePage
{
	public HomePage(WebDriver driver, WebActionUtils webActionUtils)
	{
		super(driver, webActionUtils);
	}
	
	/*------------------------ Basic Home Page Operation ------------------------*/
	
	@FindBy(id="ctl00_btnLogout1")
	private WebElement logoutBTN;
	
	@FindBy(id = "homeiconMainSub")
	private WebElement homeBTN;
	
	public void logoutApp()
	{
		webActionUtils.elementClick(logoutBTN);
		webActionUtils.acceptAlert(driver);
	}
	
	public boolean logoutIsDisplayed()
	{
		return logoutBTN.isDisplayed();
	}
	
	public void myHome()
	{
		webActionUtils.elementClick(homeBTN);
	}
	
	/*------------------- navigationBar, Menu & child Menu -------------------*/ 
	
	@FindBy(xpath = "//*[@id='menubar']/div/div/div")
	private List<WebElement> navigationBar;
	
	@FindBy(xpath = "//*[@id='menubar']/div/div/div/div/div")
	private List<WebElement> menuList;
	
	@FindBy(xpath = "//*[@id='menubar']/div/div/div/div/div/div/div")
	private List<WebElement> subMenuList;

	public void selectFromList(String navigation, String menuName)
	{
		try 
		{
			for (WebElement targetNavigation : navigationBar) 
			{
				if(targetNavigation.getText().equals(navigation))
				{
					webActionUtils.moveToElement(targetNavigation);
					for (WebElement menu : menuList) 
					{
						if(menu.getText().contains(menuName))
						{
							webActionUtils.elementClick(menu);
							Reporter.log("Navigated To " + menuName, true);
							break;
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
	public void selectFromList(String navigation, String menuName, String subMenuName)
	{
		try 
		{
			for (WebElement targetNavigation : navigationBar) 
			{
				if(targetNavigation.getText().equals(navigation))
				{
					webActionUtils.moveToElement(targetNavigation);
					for (WebElement menu : menuList) 
					{
						if(menu.getText().contains(menuName))
						{
							webActionUtils.moveToElement(menu);
							for(WebElement subMenu : subMenuList)
							{
								if(subMenu.getText().contains(subMenuName))
								{
									webActionUtils.elementClick(subMenu);
									Reporter.log("Navigated To " + subMenuName, true);
									break;	
								}			
							}
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
	public void contextSelectFromList(String navigation, String menuName)
	{
		try 
		{
			for (WebElement targetNavigation : navigationBar) 
			{
				if(targetNavigation.getText().equals(navigation))
				{
					webActionUtils.moveToElement(targetNavigation);
					for (WebElement menu : menuList) 
					{
						if(menu.getText().contains(menuName))
						{
							webActionUtils.newTab(menu);
							Reporter.log("Navigated To " + menuName, true);
							break;
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
	
	public void contextSelectFromList(String navigation, String menuName, String subMenuName)
	{
		try 
		{
			for (WebElement targetNavigation : navigationBar) 
			{
				if(targetNavigation.getText().equals(navigation))
				{
					webActionUtils.moveToElement(targetNavigation);
					for (WebElement menu : menuList) 
					{
						if(menu.getText().contains(menuName))
						{
							webActionUtils.moveToElement(menu);
							for(WebElement subMenu : subMenuList)
							{
								if(subMenu.getText().contains(subMenuName))
								{
									webActionUtils.newTab(subMenu);
									Reporter.log("Navigated To " + subMenuName, true);
									break;	
								}			
							}
						}
					}
				}
			}
		} catch (Exception e) 
		{

		}
	}
}

	

