import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class addContactOnList extends BaseTest
{
    String addedcontactname;
    String phoneNumber;
    String addedlistname;

    public addContactOnList()
    {
        addedlistname = UUID.randomUUID().toString();

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

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());

        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());

        p001Login.email().sendKeys(ConfigReader.Email());
        p001Login.password().sendKeys(ConfigReader.PassWord());
        p001Login.login_button().click();
    }
    //add list
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
    public void contact_manage()
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.locate_managecontact_link().isDisplayed());
        p002Lists.locate_managecontact_link().click();
    }
    //assert manage contact label appear
    @Test(priority =3)
    public void assert_managelabe_appear()
    {
        Contacts contacts=new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->contacts.allocat_managecontact_label().isDisplayed());
        Assert.assertTrue(contacts.allocat_managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
    }

    @Test(priority = 4)
    public void navigate_lists() {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.lists_tab().isDisplayed());
        p002Lists.lists_tab().click();

    }
    //assert navigate to list tab by using addlist button
    @Test(priority = 5)
    public void assert_nav_to_liststap()
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.addlist_button().isDisplayed());
        Assert.assertTrue(p002Lists.addlist_button().isDisplayed(), "Managecontact page is not displayed on the page");

    }
    @Test(priority =6)
    public void navigate_addlists()
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.addlist_button().isDisplayed());
        p002Lists.addlist_button().click();
    }
    @Test(priority = 7)
    public void assert_appear_namelbel_popscren()
    {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> p002Lists.loc_popscrn_addlist_title().isDisplayed());
        Assert.assertTrue(p002Lists.loc_popscrn_addlist_title().isDisplayed(), "not found popscreen of add lists");

    }
    @Test(priority = 8)
    public void addlistname()
    {

        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->  p002Lists.locatlist_name().isDisplayed());
        p002Lists.locatlist_name().sendKeys(this.addedlistname);
        System.out.println("Name of list name is :" + this.addedlistname);

    }
    @Test(priority = 9)
    public void addbutton_click()
    {
        addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> addcontactOnlist.addbutton().isDisplayed());
        addcontactOnlist.addbutton().click();
    }
    @Test(priority = 10)
    public void assert_addlist_ontable() throws InterruptedException {
        Thread.sleep(3000);
        // Locate the table element
        WebElement table= driver.findElement(By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div[1]/section/table"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->table.isDisplayed());
        // Find all rows within the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        // Iterate through the rows and search for list name
        for (WebElement row : rows) {
            // Find all cells within the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Iterate through the cells and search for list name
            for (WebElement cell : cells) {
                String cellText = cell.getText();

                // Perform the search based on the cell content
                if (cellText.contains(addedlistname)) {
                    // Perform assertion
                    System.out.println("list name added that on table is: " + cellText);
                    Assert.assertTrue(cellText.contains(addedlistname), "list not added successfully");
                    break;
                }
            }
        }
    }

   @Test(priority = 11)
    public void press_contacts_tap()
    {
       addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> addcontactOnlist.locate_contacts_tap().isDisplayed());
        addcontactOnlist.locate_contacts_tap().click();
    }
    @Test(priority = 12)
    public void addcontact()
    {
        Contacts contacts = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> contacts.loc_addcontact_bttn().isDisplayed());
        contacts.loc_addcontact_bttn().click();
    }

    @Test(priority = 13)
    public void assert_nav_addcontact_popscreen_filldata() throws InterruptedException {
        Contacts contacts = new Contacts(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->contacts.addcontact_popscreen().isDisplayed());

        if (contacts.addcontact_popscreen().isDisplayed()) {
            System.out.println("add Contact pop screen appear successfully");
            contacts.text_name_addcontact_popscreen().sendKeys(this.addedcontactname);
            contacts.phoneno_addcontact_popscreen().sendKeys(this.phoneNumber);
            contacts.addbttn_addcontact_popscreen().click();

        }
        else
        {
            System.out.println("add Contact pop screen not appear successfully");
        }
    }
    @Test(priority = 14)
    public void inseartcontactname_on_searchfield() throws InterruptedException
    {
        Thread.sleep(4000);
        update_contact updateContact=new update_contact(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->  updateContact.alocatesearch_field().isDisplayed());
        updateContact.alocatesearch_field().sendKeys(addedcontactname);
    }
    //assert contact name is inserted on the table
    @Test(priority = 15)
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
            Thread.sleep(3000);
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

    @Test(priority = 16)
    public void selclickcheckbox()
    {
        addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
        addcontactOnlist.locatecheckbox().click();
    }
@Test(priority = 17)
    public void clickaddtolist_bttn()
{
    addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d -> addcontactOnlist.loc_addtolist_bttn().isDisplayed());
    addcontactOnlist.loc_addtolist_bttn().click();
}
@Test(priority = 18)
    public void assert_addcontacttolist_popscrn_appear()
{
    addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d -> addcontactOnlist.loc_addcontactstolist_popscrn().isDisplayed());
    Assert.assertTrue(addcontactOnlist.loc_addcontactstolist_popscrn().isDisplayed(),"Add contact to list popscreen not appear");
}

//do select check box on add contacts on lists pop screen
    @Test(priority = 19)
    public void select_checkbox() throws InterruptedException {
        Thread.sleep(3000);
        addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
        // Find all rows within the table
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> addcontactOnlist.allocate_tabel_on_popscreen().isDisplayed());
        //new WebDriverWait(driver, Duration.ofSeconds(9)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        List<WebElement> rows = addcontactOnlist.allocate_tabel_on_popscreen().findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            Thread.sleep(3000);
            // Find all cells within the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Iterate through the cells and search for list name
            for (WebElement cell : cells) {
                String cellText = cell.getText();

                // Perform the search based on the cell content
                if (cellText.equals(this.addedlistname)) {
                    WebElement findmyele=cells.get(0);
                    //WebElement checkboxOf_finemyele=findmyele.findElement(By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-contactlists/section/div[3]/div/table/tbody/tr[1]/td[1]/mat-checkbox/div/div/input"));
                    System.out.println("my checkbox locate is:" +findmyele);
                    findmyele.click();
                }
            }
        }
    }
    //press save button on add contacts on lists pop screen
   @Test(priority = 21)
    public void click_on_save_bttn()
    {
        addcontact_onlist addcontactOnlist=new addcontact_onlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
       // wait.until(d -> addcontactOnlist.loc_save_bttn_popscreen().isDisplayed());
        addcontactOnlist.loc_save_bttn_popscreen().click();
    }
    @AfterTest
    public void closeDriver() throws InterruptedException
    {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}


