package TestModules;

import UtilityClasses.RandomNumberGenerator;
import UtilityClasses.TakeScreenShots;
import UtilityClasses.XcelFunctions;
import UtilityClasses.HighlightOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Code_Module {
	
	
	static WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	RandomNumberGenerator R= new RandomNumberGenerator();
	TakeScreenShots TS = new TakeScreenShots();
	HighlightOptions HO = new HighlightOptions();
	
	// Declare all locators at one place...
	By ProfileBtn_1            ,  ProfileUserName_1 ,  ProfileUserMail_1        , parentElement_1;
	By beCognizantIcon_1       ,  SearchBox_1       ,  LiveSupportGsdBtn_1      , frameElement_1;
	By WelcomeText_1           ,  LanguageBtnTxt_1  ,  CountryBtnText_1         , IT_Infra_1;
	By Human_Resource_1        ,  It_App_1          ,  Policy_Support_1         , IT_Option_1;
	By Human_Resource_Option_1 ,  It_App_Option_1   ,  Policy_Support_Option_1  , CountCountries_1;
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void getLocatersFromConfig() throws FileNotFoundException, IOException
	{	
			// getting all locator values from the properties file...
			ProfileBtn_1            = By . id(getDataFromConfig("ProfileBtn_1x"));
			ProfileUserName_1       = By . id(getDataFromConfig("ProfileUserName_1x"));
			ProfileUserMail_1       = By . id(getDataFromConfig("ProfileUserMail_1x"));
			parentElement_1         = By . xpath(getDataFromConfig("parentElement_1x"));
			beCognizantIcon_1       = By . xpath(getDataFromConfig("beCognizantIcon_1x"));
			SearchBox_1             = By . id(getDataFromConfig("SearchBox_1x"));
			LiveSupportGsdBtn_1     = By . className(getDataFromConfig("LiveSupportGsdBtn_1x"));
			frameElement_1          = By . xpath(getDataFromConfig("frameElement_1x"));
			WelcomeText_1           = By . className(getDataFromConfig("WelcomeText_1x"));
			LanguageBtnTxt_1        = By . cssSelector(getDataFromConfig("LanguageBtnTxt_1x"));
			CountryBtnText_1        = By . cssSelector(getDataFromConfig("CountryBtnText_1x"));
			IT_Infra_1 			    = By . xpath(getDataFromConfig("IT_Infra_1x"));
			Human_Resource_1 	    = By . xpath(getDataFromConfig("Human_Resource_1x"));
			It_App_1 			    = By . xpath(getDataFromConfig("It_App_1x"));
			Policy_Support_1 	    = By . xpath(getDataFromConfig("Policy_Support_1x"));
			IT_Option_1 		    = By . xpath(getDataFromConfig("IT_Option_1x"));
			Human_Resource_Option_1 = By . xpath(getDataFromConfig("Human_Resource_Option_1x"));
			It_App_Option_1 		= By . xpath(getDataFromConfig("It_App_Option_1x"));
			Policy_Support_Option_1 = By . xpath(getDataFromConfig("Policy_Support_Option_1x"));
			CountCountries_1 		= By . xpath(getDataFromConfig("CountCountries_1x"));
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public Code_Module(WebDriver driver) 
	{
		Code_Module.driver = driver;
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void OpenHomePage() throws FileNotFoundException, IOException
	{
		// Fetching URL data from config.properties file...
		String baseUrl = getDataFromConfig("baseUrl");
		driver.get(baseUrl);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void UserVerification() 
	{
		WebDriverWait wait_0 = new WebDriverWait(driver, Duration.ofSeconds(120));

		// this is to verify user and wait till the user profile icon appears on the top right corner of the home page...
		wait_0.until(ExpectedConditions.elementToBeClickable(ProfileBtn_1));
	}

	//---------------------------------------------------------------------------------------------------------------------
	
	public void ShowUserProfileData() throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebDriverWait wait_0 = new WebDriverWait(driver, Duration.ofSeconds(120));
		
		// this is to click on profile icon on the top right corner...
		wait_0.until(ExpectedConditions.elementToBeClickable(ProfileBtn_1));
		WebElement ProfileBtn = driver.findElement(ProfileBtn_1);
		
		//Highlight the profile Icon
		HO.flash(ProfileBtn, driver);
		
		try {
			ProfileBtn.click();
			TS.Screenshoot("Profile data", driver);
		} catch (Exception e)
		{
			// If opening profile 
			TS.takeScreenShotOnFailure("Profile not opened", driver);
		}

		// to fetch UserName
		wait.until(ExpectedConditions.elementToBeClickable(ProfileUserName_1));
		WebElement ProfileUserName = driver.findElement(ProfileUserName_1);
		
		//Highlight the User name
		HO.flash(ProfileUserName, driver);
				
		String UserName = ProfileUserName.getText();
		System.out.println("Username is " + UserName);

		// to fetch User Mail
		wait.until(ExpectedConditions.elementToBeClickable(ProfileUserMail_1));
		WebElement ProfileUserMail = driver.findElement(ProfileUserMail_1);
		
		//Highlight the User main
		HO.flash(ProfileUserMail, driver);
				
		String UserMail = ProfileUserMail.getText();
		System.out.println("User mail is " + UserMail);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void MoveAndClickOneCognizant() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// To click on the internal scroll div...
		WebElement parentElement = driver
				.findElement(parentElement_1);
		parentElement.click();

		wait.until(ExpectedConditions.elementToBeClickable(beCognizantIcon_1));
		WebElement beCognizantIcon = driver.findElement(beCognizantIcon_1);
		
		//Highlight the OneCognizant Icon
		HO.flash(beCognizantIcon, driver);
		beCognizantIcon.click();
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void changeWindowHandle()
	{
		// To change window
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> i = handles.iterator();
		i.next();// String parent window = i.next();
		String childwindow = i.next();
		driver.switchTo().window(childwindow);
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void searchAndClickGSD() throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Click on the search button
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox_1));
		WebElement SearchBox = driver.findElement(SearchBox_1);
		
		//Highlight the SearchBox
	    HO.flash(SearchBox, driver);
		SearchBox.click();

		// Send Values to the SearchBar...
		SearchBox.sendKeys("gsd");

		// Click on Live Support GSD...
		wait.until(ExpectedConditions.elementToBeClickable(LiveSupportGsdBtn_1));
		WebElement LiveSupportGsdBtn = driver.findElement(LiveSupportGsdBtn_1);
		
		//Highlight the User name
		HO.flash(LiveSupportGsdBtn, driver);
		
		try {
			TS.Screenshoot("Searching GSD", driver);
			LiveSupportGsdBtn.click();
		} catch (Exception e)
		{
			// If opening profile 
			TS.takeScreenShotOnFailure("Searching GSD failed", driver);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void findAndAccessIframe() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Find the frame...
		wait.until(ExpectedConditions.elementToBeClickable(frameElement_1));
		WebElement frameElement = driver.findElement(frameElement_1);
		Thread.sleep(4000);

		// Click on the frame...
		try {
			driver.switchTo().frame(frameElement);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//---------------------------------------------------------------------------------------------------------------------
	
	public void verifyWelcomeText() throws InterruptedException
	{
		// To fetch the welcome text and verify it...
		Thread.sleep(2000);
		WebElement WelcomeText = driver.findElement(WelcomeText_1);
		
		//Highlight the WelcomeText
		HO.flash(WelcomeText, driver);
		try {
			Assert.assertEquals(WelcomeText.getText(), "Welcome to the all-in-one Live Support!");
			System.out.println("Welcome Text verified :" + WelcomeText.getText());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void verifyLanguageAndCountry() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// This is to fetch and verify the selected the language...
		wait.until(ExpectedConditions.visibilityOfElementLocated(LanguageBtnTxt_1));
		WebElement LanguageBtnTxt = driver.findElement(LanguageBtnTxt_1);
		
		//Highlight the LanguageBtnText
		HO.flash(LanguageBtnTxt, driver);
		
		try {
			Assert.assertEquals(LanguageBtnTxt.getText(), "  English  ");
			System.out.println("Language verified :" + LanguageBtnTxt.getText());
		} catch (Exception e) {
			System.out.println(e);
		}

		// This is to fetch and verify the selected country...
		wait.until(ExpectedConditions.visibilityOfElementLocated(CountryBtnText_1));
		WebElement CountryBtnText = driver.findElement(CountryBtnText_1);
		
		//Highlight the CountryBtnText
		HO.flash(CountryBtnText, driver);
		
		try {
			Assert.assertEquals(CountryBtnText.getText(), "  India  ");
			System.out.println("Country verified :" + CountryBtnText.getText());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public void showAndExportCountryData() throws InterruptedException, IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		// This is to fetch the selected country...
		wait.until(ExpectedConditions.visibilityOfElementLocated(CountryBtnText_1));
		WebElement CountryBtnText = driver.findElement(CountryBtnText_1);
		
		System.out.println("\n---------------------------------------------------------------------------------------------------------");																						   
		System.out.println("                                   " + CountryBtnText.getText() + "                                      ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
		
		// Call the ShowCountryData function to show data of the selected country (India here)...
		ShowCountryData("INDIA");
		
		// This loop will help to select 2 random countries and show their data...
		for(int x=1; x<=2; x++)
		{
			// Call the selectRandomCountry function to get a random country selected...
			selectRandomCountry(CountryBtnText);
			
			System.out.println("\n---------------------------------------------------------------------------------------------------------");
			System.out.println("                                   " + CountryBtnText.getText() + "                                      ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			
			// Call the ShowCountryData function to show data of the randomly selected country...
			String Sheetname = "COUNTRY" + (x+1);
			ShowCountryData(Sheetname);
		}
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	// Call this function to show data of the any selected country...
	public void ShowCountryData(String sheetName) throws IOException
	{

		// This is to fetch All the heading titles on for the services provided...
		WebElement IT_Infra = driver.findElement(IT_Infra_1);
		WebElement Human_Resource = driver.findElement(Human_Resource_1);
		WebElement It_App = driver.findElement(It_App_1);
		WebElement Policy_Support = driver.findElement(Policy_Support_1);

		// This is to fetch All the services provided...
		List<WebElement> IT_Option = driver.findElements(IT_Option_1);
		List<WebElement> Human_Resource_Option = driver.findElements(Human_Resource_Option_1);
		List<WebElement> It_App_Option = driver.findElements(It_App_Option_1);
		List<WebElement> Policy_Support_Option = driver.findElements(Policy_Support_Option_1);

		// Taking sizes of all the list...
		int IT = IT_Option.size();
		//int HR = Human_Resource_Option.size();
		//int APP = It_App_Option.size();
		int SUPP = Policy_Support_Option.size();

		// For testing purpose only...
		//System.out.println(IT + " " + HR);
		//System.out.println(APP + " " + SUPP);

		int row = IT + 1;
		int column = 4;

		String facilities[][] = new String[row][column];

		// Storing all data into facilities 2d string...
		for (int k = 0; k < row; k++) {
			if (k == 0) {
				facilities[0][0] = IT_Infra.getText();
				facilities[0][1] = Human_Resource.getText();
				facilities[0][2] = It_App.getText();
				facilities[0][3] = Policy_Support.getText();
			} 
			else 
			{
				facilities[k][0] = IT_Option.get(k - 1).getText();
				facilities[k][1] = Human_Resource_Option.get(k - 1).getText();
				facilities[k][2] = It_App_Option.get(k - 1).getText();
				
				
				if(k > SUPP)
				{
					facilities[k][3] = " ";
				}
				else
				{
					facilities[k][3] = Policy_Support_Option.get(k - 1).getText();
				}
			}
		}

		// Printing from the facilities 2d string...
		for (int k = 0; k < row; k++) {
			System.out.println(facilities[k][0] + "____" + facilities[k][1] + "____" + facilities[k][2] + "____"
					+ facilities[k][3]);
		}
		
		// Sending languages data to the excel file...
		XcelFunctions XF = new XcelFunctions(".//ExcelFiles//POI_DATA.xlsx");
		XF.writeData(sheetName, row, column, facilities);
		
		System.out.println("\n");

	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	// Call this function to select a random country...
	public void selectRandomCountry(WebElement CountryBtnText) throws InterruptedException, IOException
	{
		// Highlight the countries button...
		HO.flash(CountryBtnText, driver);
		
		// Click on the countries button...
		CountryBtnText.click();

		// Get list of all the countries...
		List<WebElement> CountCountries = driver.findElements(CountCountries_1);

		// Call the Randomizer function to select a random country from the list...
		int RandomCountryNumber = R.Randomizer(CountCountries.size());

		// Scroll to the country before selecting it...
		Actions act = new Actions(driver);
		act.scrollToElement(CountCountries.get(RandomCountryNumber)).perform();

		// Highlight the country to be selected...
		HO.flash(CountCountries.get(RandomCountryNumber), driver);

		// Click on the country to select it...
		try 
		{
			CountCountries.get(RandomCountryNumber).click();
			TS.Screenshoot("Selected country screenshot", driver);
		}
		catch(Exception e)
		{
			TS.takeScreenShotOnFailure("Country search failed", driver);
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	//Call this function to get data from config file...
	public static String getDataFromConfig(String requestedElement) throws FileNotFoundException, IOException
	{
		// Fetching URL data from config.properties file...
		Properties props = new Properties();
		props.load(new FileInputStream("src/main/java/Config/Config.properties")); // Loading the properties
		String ElementToBeReturned = props.getProperty(requestedElement);
		
		return ElementToBeReturned;
	}
}