package com.nebula.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nebula.BaseTestHo.BaseTest;
import com.nebula.qa.POM.HomePage;
import com.nebula.qa.POM.LoginPage;
import com.nebula.qa.POM.PrmHOPage;
import com.nebula.qa.POM.StockINPage;
import com.nebula.utilities.Utilities;
import com.nebula.utilities.WebActionUtils;	

public class StockInTest extends BaseTest
{
	public WebDriver driver;
	LoginPage loginpage;
	WebActionUtils webActionUtils;
	HomePage hp;
	StockINPage stp;
	PrmHOPage prm;
	
	@BeforeMethod
	public void setUp()
	{
		driver = initializeBrowserAndOpenApplication(readPropertyValue("browser"));
		webActionUtils = new WebActionUtils(driver);
		loginpage = new LoginPage(driver, webActionUtils);
		loginpage.login(DEFAULT_HO_USERNAME, DEFAULT_HO_PASSWORD);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	@Test(description = "Test New General Stock Entry", dataProviderClass = Utilities.class, dataProvider = DATA_PROVIDER_NAME)
	public void newStockGeneral(String supplier, String Category, String Product, String Model, String Color,
			String Material, String design, String Qty, String rate, String sprice)
	{
		hp = new HomePage(driver, webActionUtils);
		stp = new StockINPage(driver, webActionUtils);
		prm = new PrmHOPage(driver, webActionUtils);
		
		/*------------------- Navigating to StockIn -------------------*/
		
		hp.selectFromList(HO_STOCKS, STOCKIN_MENU);
		
		/*------------------- I N F O -------------------*/
		stp.clickNewBTN();
		stp.selectSupplier(supplier);
		
		/*------------------- D E T A I L S -------------------*/
		stp.selectCategory(Category);
		stp.selectProduct(Product);
		stp.selectModel(Model);
		stp.selectColor(Color);
		stp.selectMaterial(Material);
		stp.selectDesign(design);
		stp.clickOK();
		stp.enterQuantity(Qty);
		stp.enterRate(rate);
		stp.enterSPrice(sprice);
		String barcode = stp.clickAddBTN();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.checkInventory(barcode);
		
		/*------------------- S A V E -------------------*/
		String savedBarcode = stp.save();
		
		/*------------------- Inventory Validation before Save------------------- */

		stp.Inventory(savedBarcode);
		
	}
}
