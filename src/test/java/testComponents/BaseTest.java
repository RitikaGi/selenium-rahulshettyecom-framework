package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public void initializeDriver() throws IOException {
		
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/resources/GlobalProperties.properties");
		prop.load(fis);
		String browserName= prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
