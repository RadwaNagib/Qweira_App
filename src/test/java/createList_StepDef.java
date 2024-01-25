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
import pages.p001_login;
import pages.p002_createlist;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import resources.BaseTest;
import resources.ConfigReader;

public class createList_StepDef extends BaseTest {


    String addedlistname;
    //String editedListName;
    public createList_StepDef()
    {

        addedlistname = UUID.randomUUID().toString();
    }

  //  public static void main(String[] args) throws InterruptedException {}

@BeforeClass
    public void OpenBrowser () throws InterruptedException
{
    p001_login p001Login=new p001_login(driver);

  //  new WebDriverWait(driver, Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    driver.navigate().to(ConfigReader.getBaseUrl()+"login");
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p001Login.email().isDisplayed());
    Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait1.until(d ->p001Login.password().isDisplayed());
   // driver.manage().window().maximize();
    p001Login.login_with_valid_data(driver, ConfigReader.Email(),ConfigReader.PassWord());
}

@Test(priority = 1)
public void navigate_managecontacts() throws InterruptedException{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
     wait.until(d ->p002Lists.managecontact_label().isDisplayed());
   // new WebDriverWait(driver,Duration.ofSeconds(6)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
   p002Lists.locate_managecontact_link().click();
   // Thread.sleep(2000);
    Assert.assertTrue(p002Lists.managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
    //Thread.sleep(2000);
}

@Test(priority = 2)
public void navigate_lists() throws InterruptedException {
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.lists_tab().isDisplayed());
    p002Lists.lists_tab().click();

}
//assert navigate to list tab by using addlist button
@Test(priority = 3)
public void assert_nav_to_liststap()
{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.addlist_button().isDisplayed());
   // new WebDriverWait(driver,Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    Assert.assertTrue(p002Lists.addlist_button().isDisplayed(), "Managecontact page is not displayed on the page");

}
@Test(priority = 4)
public void navigate_addlists() throws InterruptedException {
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.addlist_button().isDisplayed());
   p002Lists.addlist_button().click();
}

@Test(priority = 5)
public void assert_appear_namelbel_popscren() {
   // new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    // WebElement name_label = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-addlist/section/div[2]/form/div[1]/us-input/section/div[1]/label/span[1]"));
    p002_createlist p002Lists =new p002_createlist(driver);
   Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
   wait.until(d -> p002Lists.loc_popscrn_addlist_title().isDisplayed());
    Assert.assertTrue(p002Lists.loc_popscrn_addlist_title().isDisplayed(), "not found popscreen of add lists");

}
@Test(priority = 6)
public void addlistname() throws InterruptedException {

    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->  p002Lists.locatlist_name().isDisplayed());
    p002Lists.locatlist_name().sendKeys(this.addedlistname);
    System.out.println("Name of list name is :" + this.addedlistname);

}
@Test(priority = 7)
public void addbutton_click() throws InterruptedException {
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d -> p002Lists.addbutton().isDisplayed());
    p002Lists.addbutton().click();
}
@Test(priority = 8)
public void assert_addlist_ontable() throws InterruptedException {
    Thread.sleep(2000);
 //   new WebDriverWait(driver,Duration.ofSeconds(8)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
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
                //Thread.sleep(3000);
                break;
            }
        }
    }
}
@AfterTest
    public void closeDriver() throws InterruptedException
{
   // Thread.sleep(1000);
    driver.close();
    driver.quit();
}
}