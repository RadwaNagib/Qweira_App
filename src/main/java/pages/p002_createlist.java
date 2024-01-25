package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class p002_createlist  {
    WebDriver driver;
    String listAdded;

    public p002_createlist(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement managecontact_label()
    {
        By managelbl=By.xpath("//div//span[@class=\"txt-lg main-heading\"]");
        WebElement managelbe_Ele=driver.findElement(managelbl);
        return managelbe_Ele;
    }
public WebElement locate_managecontact_link()
{
    By managecontab=By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-two/div/app-side-bar/div/div[2]/ul/li[4]/a/span");
    WebElement managecontab_Ele=driver.findElement(managecontab);
    return managecontab_Ele;
}
    public WebElement lists_tab() {

        By lists = By.id("mat-tab-label-0-1");
        WebElement listsEle = driver.findElement(lists);
        return listsEle;
    }

    public WebElement addlist_button() {
        By list_bttn = By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/div/div[2]/us-button/button/div/span");
        WebElement list_bttnEle = driver.findElement(list_bttn);
        return list_bttnEle;
    }

    public WebElement locatlist_name() {
        By entrlistname = By.xpath("//div//div//input[@placeholder=\"Name\"]");
        WebElement listnameEle = driver.findElement(entrlistname);
        return listnameEle;
    }
public WebElement loc_popscrn_addlist_title()
{
    By locname=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-addlist/section/div[1]/div[1]");
    WebElement locnameEle=driver.findElement(locname);
    return locnameEle;
}
    public WebElement addbutton() {
        By bttnadd = By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-addlist/section/div[3]/us-button[1]/button");
        WebElement bttnaddEle = driver.findElement(bttnadd);
        return bttnaddEle;
    }

    public void assrt_manage_tap() throws InterruptedException  {
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(2000);
        String actualResult = driver.getCurrentUrl();
        System.out.println("contacts link is :"+actualResult);
        String expectedResult = "http://173.212.222.155:4200/contacts?tab=contacts";
        Assert.assertEquals(actualResult,expectedResult, "Error Message: contacts link is wrong");
    }



    public void assert_lists_tab() {
        String actualResult = driver.getCurrentUrl();
        System.out.println("lisys link is "+actualResult);
        String expectedResult = "http://173.212.222.155:4200/contacts?tab=lists";
        // String expectedResult=" https://portal.qweira.com/contacts?tab=lists";
        Assert.assertTrue(actualResult.contains(expectedResult), "Error Message: lists link is wrong");
    }
    public void entr_listname(WebDriver driver, String listname) throws InterruptedException {
       // Thread.sleep(1000);
        locatlist_name().clear();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> locatlist_name().isDisplayed());
        locatlist_name().sendKeys(listname);
        // Set the list name
        this.listAdded = listname;
        System.out.println("Name of list name is :" + listAdded);
    }
}

