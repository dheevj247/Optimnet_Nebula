package com.nebula.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

public class Utilities 
{
	public static final int IMPLICIT_WAIT = 10;
	public static final int PAGE_LOAD_TIME = 5;
	public static final int EXPLICIT_TIME = 5;
	public static final String DATA_PROVIDER_NAME = "smokeTest";
	
	/*-------------------- Login Credentials --------------------*/
	public static final String DEFAULT_HO_USERNAME = "admin";
	public static final String DEFAULT_HO_PASSWORD = "test";
	
	/*-------------------- Employee Credentials --------------------*/
	public static final String HO_EMP_UN = "emp";
	public static final String HO_EMP_PWD = "test";
	public static final String BO_EMP_UN = "empbo";
	public static final String BO_EMP_PWD = "test";
	
	/*-------------------- Head Office Navigation Bar ---------------------*/
	public static final String HO_MASTERS = "Masters";
	public static final String HO_STOCKS = "Stocks";

	/*-------------------- Head Office Menu ---------------------*/
	
	public static final String STOCKIN_MENU = "StockIn/Purchase Details";
	public static final String STOCK_TRANSFER_MENU = "Stock Transfer";
	public static final String PRM = "Product Registry Manager";
	
	/*-------------------- Head Office Sub - Menu ---------------------*/
	
	public static final String ST_NORMAL_MODE = "Stock Transfer Normal Mode";
	public static final String PRM_HO = "Product Registry - HO";
	
	/*-------------------- Branch Office Navigation Bar ---------------------*/
	
	public static final String BO_STOCKS = "Stocks";
	public static final String ORDERS = "Orders";
	public static final String BILLS = "Bills";
	
	/*-------------------- Branch Office Menu ---------------------*/
	
	public static final String PRM_BO = "Product Registry";
	public static final String ORDER_FORM = "Order Form";
	public static final String ORDER_Delivery = "Order Delivery";
	public static final String CASH_BILL = "Cash Bill";
	
	/*-------------------- Branch Office Sub - Menu ---------------------*/
	
	public static final String PRM_BO_Gen = "Product Registry Manager";
	
	/******/	
	
	public Properties prop;
	
	public static String getTimeStamp() 
	{
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
	public String readPropertyValue(String key)
	{
		try
		{
			String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\nebula\\qa\\testdata\\config.properties";
			FileReader reader = new FileReader(path);
			prop = new Properties();
			prop.load(reader);
			return prop.getProperty(key);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@DataProvider(name = DATA_PROVIDER_NAME)
	public static String[][] getAllData(Method m)
	{
		try
		{
			String sheetName = m.getName();
			String xlPath = System.getProperty("user.dir") +"\\src\\main\\java\\com\\nebula\\qa\\testdata\\SmokeTestData.xlsx";
			File f = new File(xlPath);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sname = wb.getSheet(sheetName);
			
			int totalRows = sname.getLastRowNum();
			Row rowCells = sname.getRow(0);
			int totCols = rowCells.getLastCellNum();
			
			DataFormatter format = new DataFormatter();
			String testData[][] = new String[totalRows][totCols];
			for(int i = 1; i <= totalRows; i++)
			{
				for(int j = 0; j <totCols; j++)
				{
					testData[i-1][j] = format.formatCellValue(sname.getRow(i).getCell(j));
				}
			}
			return testData;
		}
		catch (Exception e) 
		{
	
		}
		return null;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\" +testName+".png";
		try
		{
			FileHandler.copy(src, new File(destinationScreenshotPath));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
}
