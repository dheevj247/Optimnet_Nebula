package com.nebula.qa.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.nebula.utilities.WebActionUtils;

public class PrmHOPage extends BasePage
{

	public PrmHOPage(WebDriver driver, WebActionUtils webActionUtils) 
	{
		super(driver, webActionUtils);
	}
	
	/*-------------------------- Actions --------------------------*/
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_btnSearch']")
	private WebElement searchTB;
	
	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_gvwProdRegManager']/tbody/tr[1]/td[9]")
	private WebElement barcodeQty;
	
	@FindBy(xpath = "//input[@id='ctl00_ContentPlaceHolder1_txtProdCode1']")
	private WebElement barcodefield;
	
	public void getBarcodeQty(String barcode)
	{
		try
		{
			webActionUtils.elementClick(searchTB);
			webActionUtils.enterData(barcodefield, barcode);
			webActionUtils.elementClick(searchTB);
			//String qty =  webActionUtils.getText(barcodeQty);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	        String qty = (String) jsExecutor.executeScript("return arguments[0].textContent;", barcodeQty);
			Reporter.log("Barcode Qty is : " + qty, true);
		}
		catch (Exception e)
		{
			Reporter.log("No barcode as such", true);
		}
	}
	
}
