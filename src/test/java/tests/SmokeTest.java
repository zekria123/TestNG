package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import core.Base;
import pageObj.CreateAccountPageObj;
import pageObj.HotelLoginPageObj;
import utilities.ExcelUtility;

@Listeners(utilities.TestNGListeners.class)
public class SmokeTest extends Base {
	
	HotelLoginPageObj hotelpageObj;
	CreateAccountPageObj createAccountPageObj;

	@BeforeMethod
	public void beforeMethod() {
		Base.initializeDriver();
		logger.info("Browser opened successfully");

	}

	@Test(dataProvider = "getInfoData")
	public void createAccount(String email, String title, String firstname, String lastname, String password,
			String day, String month, String year) {
		hotelpageObj = new HotelLoginPageObj();
		createAccountPageObj = new CreateAccountPageObj();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(hotelpageObj.createSignInisPresent());
		hotelpageObj.clickonSignIn();
		logger.info("User clicked on Sign in");
		softAssert.assertTrue(createAccountPageObj.createEmailAddressisPresent());
		softAssert.assertTrue(createAccountPageObj.createAnAccountisPresent());
		createAccountPageObj.enterCreateEmail(email);
		logger.info("User entered: " + email);
		createAccountPageObj.clickOnCreateAnAccount();
		logger.info("User clicked on Create Account");
		softAssert.assertTrue(createAccountPageObj.mrsTitleisPresent());
		softAssert.assertTrue(createAccountPageObj.mrTitleisPresent());
		softAssert.assertTrue(createAccountPageObj.firstNameisPresent());
		softAssert.assertTrue(createAccountPageObj.lastNameisPresent());
		softAssert.assertTrue(createAccountPageObj.emailisPresent());
		softAssert.assertTrue(createAccountPageObj.passwordisPresent());
		softAssert.assertTrue(createAccountPageObj.daysisPresent());
		softAssert.assertTrue(createAccountPageObj.monthisPresent());
		softAssert.assertTrue(createAccountPageObj.yearisPresent());
		softAssert.assertTrue(createAccountPageObj.signUpforNewsisPresent());
		softAssert.assertTrue(createAccountPageObj.receiveSpecialOffersisPresent());
		softAssert.assertTrue(createAccountPageObj.registerButtonisPresent());
		createAccountPageObj.selectTitle(title);
		logger.info("User selected Title: " + title);
		createAccountPageObj.fillPersonalInformation(firstname, lastname, password, email);
		logger.info("User filled personal information");
		createAccountPageObj.dateOFBirthInfo(day.substring(0, 1), month.substring(0, 1), year.substring(0, 4));
		logger.info("User filled DOB information ");
		createAccountPageObj.registerAccount();
		logger.info("User clicked on Register Account");
		softAssert.assertAll();

	}

	@DataProvider()
	public Object[][] getInfoData() {
		Object data[][] = ExcelUtility.getExcelData("info");
		return data;
	}

	@AfterMethod
	public void afterMethod() {
		Base.tearDown();
		logger.info("Browser closed");

	}


}
