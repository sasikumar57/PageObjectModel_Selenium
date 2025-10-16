package pomTestFiles;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageFiles.pomLoginPage;

public class Logintest {
	
	// POM and Hybrid framework

	WebDriver driver;
	pomLoginPage loginP;

	@BeforeMethod
	void setup() {
		driver = new ChromeDriver();
		loginP = new pomLoginPage(driver);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	//POM
	@Test
	void ValidLogin() {
		loginP.login("standard_user", "secret_sauce");
		String backpack = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).getText();
		Assert.assertEquals(backpack, "Sauce Labs Backpack");
	}

//	POM
	@Test
	void invalidLoginwithoutUN() {
		loginP.enterPass("Shridhar");
		loginP.loginBtnClick();
		Assert.assertEquals(loginP.getError(), "Epic sadface: Username is required");
	}
	
//	@DataProvider(name = "data")
//	Object[][] fetchdata() throws IOException {
//		FileInputStream file = new FileInputStream("E:/BESENT TECHNOLOGIES/Selenium/SwagLabsCredentials.xlsx");
//		XSSFWorkbook book = new XSSFWorkbook(file);
//		XSSFSheet sheet = book.getSheetAt(0);
//		int rows = sheet.getLastRowNum();
//		short cell = sheet.getRow(0).getLastCellNum();
//		
//		Object[][] arr = new Object[rows] [cell]; //3 //2
//
//		for (int i = 1; i <= rows; i++) {
//			for (int j = 0; j < cell; j++) {
//				arr[i-1][j] = sheet.getRow(i).getCell(j).toString();
//			}			
//		}
//		return arr;
//	}
	
	// Hybrid framework using DataDriven, POM and TestNG
//	@Test(dataProvider = "data")
//	void loginTest(String user, String pass) {
//		loginP.login(user, pass);
//		String prdName = driver.findElement(By.xpath("//div[.='Sauce Labs Backpack']")).getText();
//		Assert.assertEquals(prdName, "Sauce Labs Backpack");
//	}

	@AfterMethod
	void tearDown() {
		driver.quit();
	}

}
