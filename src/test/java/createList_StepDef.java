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
public void navigate_managecontacts()
{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
     wait.until(d ->p002Lists.managecontact_label().isDisplayed());
   p002Lists.locate_managecontact_link().click();
    Assert.assertTrue(p002Lists.managecontact_label().isDisplayed(), "Managecontact page is not displayed on the page");
}

@Test(priority = 3)
public void navigate_lists() {
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.lists_tab().isDisplayed());
    p002Lists.lists_tab().click();

}
//assert navigate to list tab by using addlist button
@Test(priority = 4)
public void assert_nav_to_liststap()
{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.addlist_button().isDisplayed());
    Assert.assertTrue(p002Lists.addlist_button().isDisplayed(), "Managecontact page is not displayed on the page");

}
@Test(priority =5)
public void navigate_addlists()
{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->p002Lists.addlist_button().isDisplayed());
   p002Lists.addlist_button().click();
}

@Test(priority = 6)
public void assert_appear_namelbel_popscren()
{
    p002_createlist p002Lists =new p002_createlist(driver);
   Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
   wait.until(d -> p002Lists.loc_popscrn_addlist_title().isDisplayed());
    Assert.assertTrue(p002Lists.loc_popscrn_addlist_title().isDisplayed(), "not found popscreen of add lists");

}
@Test(priority = 7)
public void addlistname()
{

    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d ->  p002Lists.locatlist_name().isDisplayed());
    p002Lists.locatlist_name().sendKeys(this.addedlistname);
    System.out.println("Name of list name is :" + this.addedlistname);

}
@Test(priority = 8)
public void addbutton_click()
{
    p002_createlist p002Lists =new p002_createlist(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.timeOut()));
    wait.until(d -> p002Lists.addbutton().isDisplayed());
    p002Lists.addbutton().click();
}
@Test(priority = 9)
public void assert_addlist_ontable() throws InterruptedException {
    Thread.sleep(2000);
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


}