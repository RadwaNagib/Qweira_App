package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    public WebDriver driver;

    public WebDriver getDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return new ChromeDriver(options);
    }

    @BeforeClass
    public void initializeSelenium(){
        driver = getDriver();
    }

    @AfterClass
    public void stopDriver()
    {

        driver.close();
        driver.quit();
    }


}
