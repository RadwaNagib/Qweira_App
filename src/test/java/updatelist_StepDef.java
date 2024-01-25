import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.p001_login;
import pages.p002_createlist;
import pages.p002_deleteList;
import pages.p002_updatelist;
import resources.BaseTest;
import resources.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class updatelist_StepDef extends BaseTest {
   // public WebDriver driver = new ChromeDriver();
    String addedlistname;
    String editedListName;

    public updatelist_StepDef() {
        addedlistname = UUID.randomUUID().toString();
    }

   // public static void main(String[] args) throws InterruptedException {}

    @BeforeClass
    public void OpenBrowser () throws InterruptedException
    {
        p001_login p001Login=new p001_login(driver);
        driver.navigate().to(ConfigReader.getBaseUrl()+"login");
        // driver.manage().window().maximize();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p001Login.email().isDisplayed());
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait1.until(d ->p001Login.password().isDisplayed());
        p001Login.login_with_valid_data(driver, ConfigReader.Email(),ConfigReader.PassWord());

    }

    @Test(priority = 1)
    public void navigate_managecontacts() throws InterruptedException{
       // p002_createlist p002Lists =new p002_createlist(driver);
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.managecontact_label().isDisplayed());
        //new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
       p002Updatelist.locate_managecontact_link().click();
       // Thread.sleep(2000);
        Assert.assertTrue(p002Updatelist.managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
        //Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void navigate_lists() throws InterruptedException {
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
       // p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.lists_tab().isDisplayed());
        p002Updatelist.lists_tab().click();

    }

    //assert navigate to list tab by using addlist button
    @Test(priority = 3)
    public void assert_nav_to_liststap()
    {
        //p002_createlist p002Lists =new p002_createlist(driver);
        //new WebDriverWait(driver,Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.lists_tab().isDisplayed());
        Assert.assertTrue(p002Updatelist.addlist_button().isDisplayed(), "Managecontact page is not displayed on the page");

    }
    @Test(priority = 4)
    public void navigate_addlists() throws InterruptedException {
        //p002_createlist p002Lists =new p002_createlist(driver);
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.addlist_button().isDisplayed());
        p002Updatelist.addlist_button().click();
    }
    @Test(priority = 5)
    public void assert_appear_namelbel_popscren() {
       // new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
       // p002_createlist p002Lists =new p002_createlist(driver);
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
      Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
     wait.until(d -> p002Updatelist.loc_popscrn_addlist_title().isDisplayed());
        Assert.assertTrue(p002Updatelist.loc_popscrn_addlist_title().isDisplayed(), "not found popscreen of add lists");

    }
    @Test(priority = 6)
    public void addlistname() throws InterruptedException {
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
        //p002_createlist p002Lists =new p002_createlist(driver);
        // p002Lists.entr_listname(driver,this.addedlistname);
        p002Updatelist.locatlist_name().sendKeys(this.addedlistname);
        System.out.println("Name of list name is :" + this.addedlistname);

    }
    @Test(priority = 7)
    public void addbutton_click() throws InterruptedException {
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
       // p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> p002Updatelist.addbutton().isDisplayed());
        p002Updatelist.addbutton().click();
    }

    @Test(priority = 8)
    public void assert_addlist_ontable() throws InterruptedException {
        Thread.sleep(2000);
        // Locate the table element
        WebElement table= driver.findElement(By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div[1]/section/table"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->table.isDisplayed());
        Thread.sleep(1000);
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
                    Thread.sleep(2000);
                    break;
                }
            }
        }
    }

    @Test(priority = 9)
    public void inserton_searchfield() throws InterruptedException
    {
        p002_updatelist p002Updatelist=new p002_updatelist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.alocatesearch_field().isDisplayed());
        p002Updatelist.alocatesearch_field().sendKeys(this.addedlistname);
    }

    @Test(priority = 10)
    public void editclick() {
        p002_updatelist p002Updatelist = new p002_updatelist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Updatelist.edit_ele().isDisplayed());
        p002Updatelist.edit_ele().click();
    }
@Test(priority = 11)
public void assert_editdata_popscrn_appear()
{
   // new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    p002_updatelist p002Updatelist = new p002_updatelist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Updatelist.loceditdata().isDisplayed());
    Assert.assertTrue(p002Updatelist.loceditdata().isDisplayed(),"edit data popscrn not appear");
}

    @Test(priority = 12)
    public void write_new_list_name() {
        //new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        p002_updatelist p002Updatelist = new p002_updatelist(driver);
        p002Updatelist.edittext().clear();
       // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       // wait.until(d -> edittext().isDisplayed());
        editedListName=addedlistname+"_Edite";
        p002Updatelist.edittext().sendKeys(this.editedListName);
    }
@Test(priority = 13)
public void click_on_updatebttn() {
  //  new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    p002_updatelist p002Updatelist = new p002_updatelist(driver);
    p002Updatelist.bttn_update().click();
}

@Test(priority = 14)
public void assert_update_done_success() throws InterruptedException {
    Thread.sleep(3000);
    WebElement table = driver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-1\"]/div/app-lists/section/div[1]/section/table"));
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
            if (cellText.contains(this.editedListName))
            {
                // Perform assertion
                System.out.println("list name after updated on table: " + cellText);
                Assert.assertTrue(cellText.contains(this.editedListName),"list not added successfully");

                break;
            }

        }
    }

}
    @AfterTest
    public void closeDriver() throws InterruptedException
    {
        //Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
