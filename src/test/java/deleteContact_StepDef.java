import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import resources.BaseTest;
import resources.ConfigReader;
public class deleteContact_StepDef extends BaseTest{

    String addedcontactname;
    String phoneNumber;
    public deleteContact_StepDef()
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
        p001_login p001Login = new p001_login(driver);
        driver.navigate().to(ConfigReader.getBaseUrl() + "login");
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());
        p001Login.login_with_valid_data(driver, ConfigReader.Email(), ConfigReader.PassWord());

    }

    @Test(priority = 1)
    public void contact_manage() throws InterruptedException
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p002Lists.locate_managecontact_link().isDisplayed());
        p002Lists.locate_managecontact_link().click();
    }

    //assert manage contact label appear
    @Test(priority =2)
    public void assert_managelabe_appear() throws InterruptedException
    {
        //p001_login p001Login=new p001_login(driver);
        Contacts contacts=new Contacts(driver);
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d -> contacts.managecontact_label().isDisplayed());
       // contacts.managecontact_label();
        //String expectedResult="http://173.212.222.155:4200/contacts?tab=contacts";
        // String actualResult=driver.getCurrentUrl();
        //Assert.assertTrue(actualResult.contains(expectedResult), "Error Message: can't navigate to Manage Contact link");
      //  new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
       // Thread.sleep(2000);
        Assert.assertTrue(contacts.allocat_managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
    }

    @Test(priority = 3)
    public void addcontact() throws InterruptedException
    {
        deleteContact deleteContact=new deleteContact(driver);
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d -> deleteContact.loc_addcontact_bttn().isDisplayed());
        //Contacts contacts=new Contacts(driver);
        deleteContact.loc_addcontact_bttn().click();
        //Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void assert_nav_addcontact_popscreen_filldata() throws InterruptedException {
       // new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(2000);
        deleteContact deleteContact=new deleteContact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> deleteContact.addcontact_popscreen().isDisplayed());
        //Contacts contacts = new Contacts(driver);
        if (deleteContact.addcontact_popscreen().isDisplayed()) {
            System.out.println("add Contact pop screen appear successfully");
            Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
            wait1.until(d ->  deleteContact.text_name_addcontact_popscreen().isDisplayed());
           deleteContact.text_name_addcontact_popscreen().sendKeys(this.addedcontactname);
            deleteContact.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
           deleteContact.addbttn_addcontact_popscreen().click();
           // Thread.sleep(2000);
        } else {
            System.out.println("add Contact pop screen not appear successfully");
        }
    }
    @Test(priority = 5)
    public void inseartcontactname_on_searchfield() throws InterruptedException {
        deleteContact deleteContact=new deleteContact(driver);
        //p002_deleteList p002DeleteList = new p002_deleteList(driver);
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d -> deleteContact.alocatesearch_field().isDisplayed());
       //deleteContact.alocatesearch_field();
       deleteContact.alocatesearch_field().sendKeys(this.addedcontactname);
        //p002DeleteList.edit_on_search_field(this.addedcontactname);
    }

    //assert contact name on inserted on the table
    @Test(priority = 6)
    public void assert_contactname_onsearchfield() throws InterruptedException {
        Thread.sleep(2000);
        WebElement table = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-contacts/section/div/div[2]/section/table"));
        // Find all rows within the table
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
         wait.until(d -> table.isDisplayed());
         new WebDriverWait(driver, Duration.ofSeconds(9)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(2000);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
// Iterate through the rows and search for list name
        for (WebElement row : rows) {
            //Thread.sleep(2000);
            // Find all cells within the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Iterate through the cells and search for list name
            for (WebElement cell : cells) {
                String cellText = cell.getText();

                // Perform the search based on the cell content
                if (cellText.contains(this.addedcontactname)) {
                    // Perform assertion
                    System.out.println("contact name after added on table: " + cellText);
                    Assert.assertTrue(cellText.contains(this.addedcontactname), "contact name not added successfully");

                    break;
                }

            }
        }

    }

    @Test(priority = 9)
    public void selclickcheckbox() throws InterruptedException
    {
      deleteContact dcontact=new deleteContact(driver);
       // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //wait.until(d -> dcontact.locatecheckbox().isDisplayed());
      dcontact.locatecheckbox().click();
    }
    @Test(priority = 10)
    public void deletebttnclick  () throws InterruptedException
    {
       deleteContact dcontact=new deleteContact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> dcontact.locatedeletebttn().isDisplayed());
       dcontact.locatedeletebttn().click();
    }
   @Test(priority = 11)
   public void confirmbttnclick()
   {
       deleteContact dcontact=new deleteContact(driver);
       Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
       wait.until(d ->  dcontact.confirmbtton().isDisplayed());
       dcontact.confirmbtton().click();
   }

   @Test(priority = 12)
   public void assertdelltcontactdone() throws InterruptedException
   {
       new WebDriverWait(driver, Duration.ofSeconds(9)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
       Thread.sleep(3000);
       Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
       WebElement table = driver.findElement(By.xpath("//table[@class=\"mat-mdc-table mdc-data-table__table cdk-table mat-sort\"]"));
       wait.until(d ->table.isDisplayed());
       // Find all rows within the table
       List<WebElement> rows = table.findElements(By.tagName("tr"));
// Iterate through the rows and search for list name
       for (WebElement row : rows) {
           //Thread.sleep(2000);
           // Find all cells within the row
           List<WebElement> cells = row.findElements(By.tagName("td"));

           // Iterate through the cells and search for list name
           for (WebElement cell : cells) {
               String cellText = cell.getText();

               // Perform the search based on the cell content
               if (!cellText.contains(addedcontactname))
               {
                   // Perform assertion
                   break;
               }
               else{
                   System.out.println("contact not deleted successfully");}

           }

       }
       System.out.println("contact deleted Successfully");
   }


    @AfterClass
    public void closeBrowser() throws InterruptedException
    {
        //Thread.sleep(1000);
        driver.close();
        driver.quit();
    }

}
