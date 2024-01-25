import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import resources.BaseTest;
import resources.ConfigReader;
public class updatecontact_StepDef  extends BaseTest {

    String addedcontactname;
    String editedcontactname;
    String phoneNumber;

    public updatecontact_StepDef() {

            addedcontactname = UUID.randomUUID().toString();
            // phoneNumber=(UUID.randomUUID().toString());
            Random random = new Random();

            // Generate a random 8-digit number
            int randomPart = random.nextInt(10000000);

            // Format the number to have 8 digits
            String formattedNumber = String.format("%08d", randomPart);

            // Concatenate "012" and the random number
            phoneNumber = "012" + formattedNumber;
        }


    @BeforeClass
    public void OpenBrowser() throws InterruptedException
    {
        p001_login p001Login = new p001_login(driver);
        driver.navigate().to(ConfigReader.getBaseUrl() + "login");
        new WebDriverWait(driver, Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());
        p001Login.login_with_valid_data(driver, ConfigReader.Email(), ConfigReader.PassWord());

        // driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void contact_manage() throws InterruptedException {
      //  new WebDriverWait(driver, Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        p002_createlist p002Lists = new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
         wait.until(d -> p002Lists.locate_managecontact_link().isDisplayed());
        p002Lists.locate_managecontact_link().click();

        //  this.editedListName=this.addedlistname+"_edited";
    }

    //assert manage contact label appear
    @Test(priority = 2)
    public void assert_managelabe_appear() throws InterruptedException {
        Contacts contacts=new Contacts(driver);
        contacts.managecontact_label();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> contacts.managecontact_label().isDisplayed());
      //  new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(2000);
        Assert.assertTrue(contacts.allocat_managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
    }

    @Test(priority = 3)
    public void addcontact() throws InterruptedException {
        Contacts contacts = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> contacts.loc_addcontact_bttn().isDisplayed());
        contacts.loc_addcontact_bttn().click();
      //  Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void assert_nav_addcontact_popscreen() throws InterruptedException {
       // new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(2000);
        Contacts contacts = new Contacts(driver);
        update_contact updateContact=new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> updateContact.addcontact_popscreen().isDisplayed());

        if (updateContact.addcontact_popscreen().isDisplayed()) {
            System.out.println("add Contact pop screen appear successfully");
            contacts.text_name_addcontact_popscreen().sendKeys(this.addedcontactname);
            // contacts.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber+11);
            updateContact.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
            updateContact.addbttn_addcontact_popscreen().click();
            //Thread.sleep(2000);
        } else {
            System.out.println("add Contact pop screen not appear successfully");
        }
    }

    @Test(priority = 5)
    public void inseartcontactname_on_searchfield() throws InterruptedException {
        //p002_deleteList p002DeleteList = new p002_deleteList(driver);
        update_contact updateContact=new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->  updateContact.alocatesearch_field().isDisplayed());
        //p002DeleteList.edit_on_search_field(this.addedcontactname);
        updateContact.alocatesearch_field().sendKeys(addedcontactname);
    }
//assert contact name on inserted on the table
    @Test(priority = 6)
    public void assert_contactname_onsearchfield() throws InterruptedException {
        Thread.sleep(3000);
        WebElement table = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-contacts/section/div/div[2]/section/table"));
        // Find all rows within the table
       Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
       wait.until(d -> table.isDisplayed());
        //new WebDriverWait(driver, Duration.ofSeconds(9)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
// Iterate through the rows and search for list name
        for (WebElement row : rows) {
            Thread.sleep(2000);
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

    @Test(priority = 7)
    public void click_updatebttn() {
        update_contact update_contact = new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_edit_contact().isDisplayed());
        update_contact.locate_edit_contact().click();
    }

    @Test(priority = 8)
    public void assert_updatecontact_popscrn_appear() throws InterruptedException {
        update_contact update_contact = new update_contact(driver);
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(2000);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_edit_contact().isDisplayed());
        Assert.assertTrue(update_contact.locate_edit_contact().isDisplayed(), "UpdateContact is not displayed on the page");
    }

    @Test(priority = 9)
    public void update_name_phone() {
       // new WebDriverWait(driver, Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        update_contact update_contact = new update_contact(driver);
        Contacts con = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.text_name_addcontact_popscreen().isDisplayed());
        update_contact.text_name_addcontact_popscreen().clear();
        this.editedcontactname = this.addedcontactname + "_edited";
       update_contact.text_name_addcontact_popscreen().sendKeys(this.editedcontactname);
        update_contact.phoneno_addcontact_popscreen().clear();
        //con.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
       update_contact.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
    }//text_name_addcontact_popscreen

    //click on save bttn after update contact
    @Test(priority = 10)
    public void clicksavebttn() throws InterruptedException {
        update_contact update_contact = new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_save_btton().isDisplayed());
        update_contact.locate_save_btton().click();
       // Thread.sleep(2000);
    }
@Test(priority = 11)
    public void assert_update_contactname_done_success() throws InterruptedException {
        Thread.sleep(3000);
        WebElement table = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/app-contacts/section/div/div[2]/section/table"));
        // Find all rows within the table
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> table.isDisplayed());
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        // Iterate through the rows and search for list name
        for (WebElement row : rows) {
            // Find all cells within the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Iterate through the cells and search for list name
            for (WebElement cell : cells) {
                String cellText = cell.getText();

                // Perform the search based on the cell content
                if (cellText.contains(editedcontactname))
                {
                    // Perform assertion
                    System.out.println("contact name after updated on table: " + cellText);
                    Assert.assertTrue(cellText.contains(editedcontactname),"updated contact name not added successfully");
                    break;
                }
            }
        }
    }

    @AfterTest
    public void closeDriver() throws InterruptedException {
        //Thread.sleep(1000);
        driver.close();
        driver.quit();
    }


}