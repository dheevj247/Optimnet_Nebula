package com.nebula.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter 
{
	public static ExtentReports generateExtendReport()
	{
		ExtentReports er = new ExtentReports();
		File erFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(erFile);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Optimnet Nebula Results");
		spark.config().setDocumentTitle("Nebula Smoke Results");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		er.attachReporter(spark); //all the configuration will be applied
		
		String browser = new Utilities().readPropertyValue("browser");
		er.setSystemInfo("Browser Name",browser);
		er.setSystemInfo("Operating System",System.getProperty("os.name"));
		er.setSystemInfo("Login User Name",System.getProperty("user.name"));
		er.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return er;
	}
}
