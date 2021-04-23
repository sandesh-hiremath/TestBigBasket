package addProductToCart;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddRiceToCart {

	// Initialize all variables
	WebDriver driver;
	String driverName = "webdriver.chrome.driver";
	String chromeDriverPath = "\\drivers\\chromedriver.exe";
	String geckoDriverPath = "\\drivers\\geckodriver.exe";
	String url = "https://www.bigbasket.com/";
	String qtyexp = "3";
	String cartqtyexp = "1 items";

	WebElement searchField;
	WebElement searchSuggestions;
	WebElement quantityInsert;
	WebElement addToCart;
	WebElement quantityCheck;
	WebElement cartCount;

	@BeforeClass
	public void setUpClass() {

		System.setProperty(driverName, System.getProperty("user.dir") + "\\drivers\\chromedriver.exe"); // set path to chromedriver.exe file
		driver = new ChromeDriver(); // instantiate chromeDriver

		// Uncomment to run in Mozilla browser
		// System.setProperty(driverName,System.getProperty("user.dir") +"\\drivers\\geckodriver.exe"); //set path to geckodriver.exe file
		// driver = new FirefoxDriver(); //instantiate FirefoxDriver

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(url); // Load application

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Wait for page to load
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // Wait for all the elements in page to load

	}

	@Test(priority = 0) // Executing this Test method first
	public void searchRice() throws IOException {

		searchField = driver.findElement(By.id("input")); // Capture search field textbox xpath
		searchField.sendKeys("Rice"); // Search for rice in search field
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // Wait for search suggestions to load

		// Capture 3rd search suggestion xpath from list
		searchSuggestions = driver.findElement(By.xpath("//div[@id='search-found']//ul/li[3]/div/div[2]/a"));
		captureScreens("SearchRice.png");

		searchSuggestions.click(); // Click on 3rd search suggestion from list
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS); // Wait for page to navigate to product page
		
		captureScreens("ProductDetails.png");
	}

	@Test(priority = 1) // Executing this Test method after searchRice()
	public void addRiceToCart() throws IOException, InterruptedException {

		System.out.println(driver.getTitle()); // Print title of the page in console

		quantityInsert = driver.findElement(By.name("qty")); // Capture quantity text field xpath
		quantityInsert.clear(); // Clear the default value i.e, 1
		quantityInsert.sendKeys("3"); // Enter quantity 3

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		captureScreens("Add3Quantity.png");

		addToCart = driver.findElement(By.xpath("//div[contains(@class,'Cs6YO')]")); // Capture Add to Cart button xpath
		addToCart.click(); // Click on Add to Cart after updating quantity to 3

		Thread.sleep(5000);
		captureScreens("ItemsInCart.png");

		quantityCheck = driver.findElement(By.xpath("//div[@class='_2MEFP']")); // Capture quantity field xpath after click on add to cart
	
		String qtychk = quantityCheck.getText();
		System.out.println(qtychk);
		MainUtil.compareRiceQuantity(qtychk, qtyexp);

		cartCount = driver.findElement(By.xpath("//div[contains(@class,'1laAA')]/div/span[2]")); // Capture Cart information field xpath
		
		String cartqty = cartCount.getText();
		System.out.println(cartqty);
		MainUtil.compareCartQuantity(cartqty, cartqtyexp);

	}

	public void captureScreens(String imgName) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take screenshot using TakeScreenshot interface and store in src location
		File dest = new File(System.getProperty("user.dir") + "\\screenshot\\" + imgName); // Provide path to save the screenshot with name result.png
		FileHandler.copy(src, dest); // Copy screenshot from src to dest }
	}

	@AfterClass
	public void endExecution() {
		driver.close();
		driver.quit();
	}
}
