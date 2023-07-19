package com.nebula.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nebula.utilities.ExtentReporter;
import com.nebula.utilities.Utilities;

public class MyListners implements ITestListener
{
	ExtentReports extentReporter;
	ExtentTest extentTest;
	WebDriver driver;
	String testName;
	
	@Override
	public void onStart(ITestContext context)
	{
		extentReporter = ExtentReporter.generateExtendReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		testName = result.getName();
		extentTest = extentReporter.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.INFO, testName + " Got Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		try
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver, testName));
		extentTest.log(Status.INFO, testName + " Failed!");
		extentTest.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.INFO, testName + " Got Skipped.");
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extentReporter.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
