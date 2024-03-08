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
            Random random = new Random();
            int randomPart = random.nextInt(10000000);
            String formattedNumber = String.format("%08d", randomPart);
            phoneNumber = "012" + formattedNumber;
        }


    @BeforeClass
    public void OpenBrowser () throws InterruptedException
    {
        p001_login p001Login=new p001_login(driver);
        driver.navigate().to(ConfigReader.getBaseUrl()+"login");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());

        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());

        p001Login.email().sendKeys(ConfigReader.Email());
        p001Login.password().sendKeys(ConfigReader.PassWord());
        p001Login.login_button().click();


    }
    @Test(priority = 1)
    public void assertnavtologinpage() throws InterruptedException
    {
        Thread.sleep(2000);
        String actualResult= driver.getCurrentUrl();
        System.out.println("Device link is: "+actualResult);
        String expectedResult="http://173.212.222.155:4200/devices";
        Assert.assertEquals(actualResult,expectedResult,"error");
    }


    @Test(priority = 2)
    public void contact_manage()  {
        p002_createlist p002Lists = new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
         wait.until(d -> p002Lists.locate_managecontact_link().isDisplayed());
        p002Lists.locate_managecontact_link().click();
    }

    //assert manage contact label appear
    @Test(priority = 3)
    public void assert_managelabe_appear() throws InterruptedException {
        Contacts contacts=new Contacts(driver);
        contacts.managecontact_label();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> contacts.managecontact_label().isDisplayed());
        Assert.assertTrue(contacts.allocat_managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
    }

    @Test(priority = 4)
    public void addcontact()
    {
        Contacts contacts = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> contacts.loc_addcontact_bttn().isDisplayed());
        contacts.loc_addcontact_bttn().click();
    }

    @Test(priority =5)
    public void assert_nav_addcontact_popscreen()
    {
        Contacts contacts = new Contacts(driver);
        update_contact updateContact=new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> updateContact.addcontact_popscreen().isDisplayed());

        if (updateContact.addcontact_popscreen().isDisplayed()) {
            System.out.println("add Contact pop screen appear successfully");
            contacts.text_name_addcontact_popscreen().sendKeys(this.addedcontactname);
            updateContact.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
            updateContact.addbttn_addcontact_popscreen().click();
        }
        else
        {
            System.out.println("add Contact pop screen not appear successfully");
        }
    }

    @Test(priority = 6)
    public void inseartcontactname_on_searchfield()
    {
        update_contact updateContact=new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->  updateContact.alocatesearch_field().isDisplayed());
        updateContact.alocatesearch_field().sendKeys(addedcontactname);
    }
//assert contact name is inserted on the table
    @Test(priority = 7)
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

    @Test(priority = 8)
    public void click_updatebttn() {
        update_contact update_contact = new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_edit_contact().isDisplayed());
        update_contact.locate_edit_contact().click();
    }

    @Test(priority = 9)
    public void assert_updatecontact_popscrn_appear()  {
        update_contact update_contact = new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_edit_contact().isDisplayed());
        Assert.assertTrue(update_contact.locate_edit_contact().isDisplayed(), "UpdateContact is not displayed on the page");
    }

    @Test(priority = 10)
    public void update_name_phone()
    {
        update_contact update_contact = new update_contact(driver);
        Contacts con = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.text_name_addcontact_popscreen().isDisplayed());
        update_contact.text_name_addcontact_popscreen().clear();
        this.editedcontactname = this.addedcontactname + "_edited";
       update_contact.text_name_addcontact_popscreen().sendKeys(this.editedcontactname);
        update_contact.phoneno_addcontact_popscreen().clear();
       update_contact.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
    }

    //click on save bttn after update contact
    @Test(priority = 11)
    public void clicksavebttn()
    {
        update_contact update_contact = new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> update_contact.locate_save_btton().isDisplayed());
        update_contact.locate_save_btton().click();

    }
@Test(priority = 12)
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




}