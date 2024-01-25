import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Contacts;
import pages.p001_login;
import pages.p002_createlist;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

import resources.BaseTest;
import resources.ConfigReader;

public class Contacts_StepDef extends BaseTest
{
    String addedcontactname;
    String phoneNumber;

    public Contacts_StepDef()
    {
        addedcontactname = UUID.randomUUID().toString();
        Random random = new Random();

        // Generate a random 8-digit number
        int randomPart = random.nextInt(10000000);

        // Format the number to have 8 digits
        String formattedNumber = String.format("%08d", randomPart);

        // Concatenate "012" and the random number
        phoneNumber = "012" + formattedNumber;
    }


    @BeforeClass
    public void OpenBrowser () throws InterruptedException
    {
        p001_login p001Login=new p001_login(driver);
        driver.navigate().to(ConfigReader.getBaseUrl()+"login");
        // new WebDriverWait(driver, Duration.ofSeconds(60)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());
        p001Login.login_with_valid_data(driver, ConfigReader.Email(),ConfigReader.PassWord());

    }

    @Test(priority = 1)
    public void contact_manage() throws InterruptedException
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        //new WebDriverWait(driver, Duration.ofSeconds(60)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.locate_managecontact_link().isDisplayed());
        p002Lists.locate_managecontact_link().click();
    }
    //assert manage contact label appear
@Test(priority =2)
    public void assert_managelabe_appear() throws InterruptedException
{

    //p001_login p001Login=new p001_login(driver);
    Contacts contacts=new Contacts(driver);
    //contacts.managecontact_label();
    //String expectedResult="http://173.212.222.155:4200/contacts?tab=contacts";
   // String actualResult=driver.getCurrentUrl();
    //Assert.assertTrue(actualResult.contains(expectedResult), "Error Message: can't navigate to Manage Contact link");
    //new WebDriverWait(driver, Duration.ofSeconds(60)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->contacts.allocat_managecontact_label().isDisplayed());
    Assert.assertTrue(contacts.allocat_managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
}

@Test(priority = 3)
public void addcontact() throws InterruptedException
{
    //new WebDriverWait(driver, Duration.ofSeconds(60)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    Contacts contacts=new Contacts(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->contacts.loc_addcontact_bttn().isDisplayed());
    contacts.loc_addcontact_bttn().click();


   // Thread.sleep(1000);
}
@Test(priority = 4)
public void assert_nav_addcontact_popscreen_filldata() throws InterruptedException {
    Contacts contacts = new Contacts(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->contacts.addcontact_popscreen().isDisplayed());
   // new WebDriverWait(driver, Duration.ofSeconds(60)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
  //  Thread.sleep(2000);

    if (contacts.addcontact_popscreen().isDisplayed()) {
        System.out.println("add Contact pop screen appear successfully");
        contacts.text_name_addcontact_popscreen().sendKeys(this.addedcontactname);
        contacts.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
        contacts.addbttn_addcontact_popscreen().click();
        //Thread.sleep(2000);
    } else {
        System.out.println("add Contact pop screen not appear successfully");
    }
}
   @AfterTest
    public void closeDriver() throws InterruptedException
    {
        driver.close();
        driver.quit();
    }

}
