import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;

public class TestCase {
	WebDriver driver;
	Eyes eyes;
	static Properties config;
	
	@BeforeAll
	public static void beforeAll() {
		config = new Properties();
		String driverName = "";

		// Get the properties file
		try {
			config.load(new FileInputStream("src/test/resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeEach
	public void beforeEach(TestInfo testInfo) {

		eyes = new Eyes();
		eyes.setApiKey(config.getProperty("API_KEY"));
		driver = new ChromeDriver();
		
		eyes.open(
			driver,
			"My First Tests",
			testInfo.getTestMethod().get().getName(),
			new RectangleSize(1000, 600)
		);
	}

	@Test
    public void testApplitoolsWebWindow() {
        // driver.get("https://applitools.com/helloworld/");
        driver.get("https://applitools.com/helloworld/?diff1");			// Changing the baseline
        
        // Checking whether the window is there or not
        eyes.check(Target.window());
    }

	@AfterEach
	public void afterEach() {
		eyes.closeAsync();;
		driver.close();
	}
}
