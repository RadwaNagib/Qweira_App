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
import pages.p002_createlist;
import pages.p001_login;
import pages.p002_deleteList;
import pages.p002_updatelist;
import resources.BaseTest;
import resources.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Deletelist_StepDef extends BaseTest {
    //public  WebDriver driver=new ChromeDriver();
    //p002_updatelist p002Updatelist=new p002_updatelist(driver);
    String addedlistname;
    String editedListName;
    public Deletelist_StepDef() {
        addedlistname = UUID.randomUUID().toString();
    }
    //public static void main(String[] args) throws InterruptedException {}

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
    public void navigate_managecontacts() throws InterruptedException{
        p002_deleteList deleteList=new p002_deleteList(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
      wait.until(d ->deleteList.locate_managecontact_link().isDisplayed());
        //p002_createlist p002Lists =new p002_createlist(driver);
        //new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        deleteList.locate_managecontact_link().click();
       // Thread.sleep(2000);
        Assert.assertTrue(deleteList.managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
        //Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void navigate_lists() throws InterruptedException {
        p002_deleteList deleteList=new p002_deleteList(driver);
      //  p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->deleteList.lists_tab().isDisplayed());
        deleteList.lists_tab().click();

    }

    //assert navigate to list tab by using addlist button
    @Test(priority = 4)
    public void assert_nav_to_liststap()
    {
        p002_deleteList deleteList=new p002_deleteList(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->deleteList.addlist_button().isDisplayed());
        Assert.assertTrue(deleteList.addlist_button().isDisplayed(), "Managecontact page is not displayed on the page");

    }
    @Test(priority = 5)
    public void navigate_addlists() throws InterruptedException {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d ->p002Lists.addlist_button().isDisplayed());
        p002Lists.addlist_button().click();
    }
    @Test(priority = 6)
    public void assert_appear_namelbel_popscren() {
        //new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        // WebElement name_label = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-addlist/section/div[2]/form/div[1]/us-input/section/div[1]/label/span[1]"));
        p002_createlist p002Lists =new p002_createlist(driver);
       Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
  wait.until(d -> p002Lists.loc_popscrn_addlist_title().isDisplayed());
        Assert.assertTrue(p002Lists.loc_popscrn_addlist_title().isDisplayed(), "not found popscreen of add lists");

    }

    @Test(priority = 7)
    public void addlistname() throws InterruptedException {

        p002_createlist p002Lists =new p002_createlist(driver);
        // p002Lists.entr_listname(driver,this.addedlistname);
        p002Lists.locatlist_name().sendKeys(this.addedlistname);
        System.out.println("Name of list name is :" + this.addedlistname);

    }

    @Test(priority = 8)
    public void addbutton_click() throws InterruptedException {
        p002_createlist p002Lists =new p002_createlist(driver);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
        wait.until(d -> p002Lists.addbutton().isDisplayed());
        p002Lists.addbutton().click();
    }
    @Test(priority =9)
    public void assert_addlist_ontable() throws InterruptedException {
        Thread.sleep(2000);
        // Locate the table element
        new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
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
                    Thread.sleep(2000);
                    break;
                }
            }
        }
    }

@Test(priority = 10)
public void inserton_searchfield() throws InterruptedException
{
    p002_deleteList p002DeleteList=new p002_deleteList(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d -> p002DeleteList.alocatesearch_field().isDisplayed());
   // p002DeleteList.alocatesearch_field();
    p002DeleteList.edit_on_search_field(this.addedlistname);
}
@Test(priority =11)
public void assert_addedListName_onsearchtable() throws InterruptedException {
    new WebDriverWait(driver,Duration.ofSeconds(10)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    Thread.sleep(2000);
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
            if (cellText.contains(addedlistname))
            {
                // Perform assertion
                System.out.println("list name after added on table: " + cellText);
                Assert.assertTrue(cellText.contains(addedlistname),"list not added successfully");

                break;
            }

        }
    }

}
@Test(priority = 12)
public void selclickcheckbox() throws InterruptedException
{
    //new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    p002_deleteList p002DeleteList=new p002_deleteList(driver);
   // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
  //  wait.until(d -> p002DeleteList.sel_checkbox().isDisplayed());
    p002DeleteList.sel_checkbox().click(); ;
}
@Test(priority = 13)
public void deletebttnclick  ()
{
    //new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    p002_deleteList p002DeleteList=new p002_deleteList(driver);
    p002DeleteList.bttn_delete_list().click();
    p002DeleteList.confirm_bttn().click();
    //p002DeleteList.assert_Delete_done_success(this.addedlistname);
}
@Test(priority = 14)
public void assert_Delete_done_success()  throws InterruptedException{
    Thread.sleep(3000);
   // new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    WebElement table = driver.findElement(By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div/section/table"));
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
            if (!cellText.contains(addedlistname))
            {
                // Perform assertion
                break;
            }
            else{
                System.out.println("list not deleted successfully");}

        }
        System.out.println("list deleted Successfully");
    }

}


}
