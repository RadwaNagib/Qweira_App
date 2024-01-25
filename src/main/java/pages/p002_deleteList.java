package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class p002_deleteList {
    WebDriver driver;

    public p002_deleteList(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement alocatesearch_field() {
        By search = By.id("search");
        WebElement searchEle = driver.findElement(search);
        return searchEle;
    }

    //enter on search filed,the name of list(addedListName)
    public void edit_on_search_field(String Listname) throws InterruptedException {
       // Thread.sleep(1000);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(d -> alocatesearch_field().isDisplayed());
         alocatesearch_field().sendKeys(Listname);
        alocatesearch_field().sendKeys(Keys.ENTER);
      //  Thread.sleep(2000);
    }
public void reload()
{
driver.navigate().to("http://173.212.222.155:4200/contacts?tab=lists");
}
    //the name of list(addedListName)

    //allocate select check box
    public WebElement sel_checkbox()
    {

            By selcheckbox =By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div/section/table/tbody/tr/td[1]/mat-checkbox/div/div/input");
            WebElement checkbox_Ele = driver.findElement(selcheckbox);
            return checkbox_Ele;
    }
    public void sel_click_checkbox() throws InterruptedException
    {
        sel_checkbox().click();
    }

    public WebElement bttn_delete_list()
    {
        //Thread.sleep(1000);
        By delete_bttn =By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/div/div[2]/us-button/button");
        WebElement deletebttnEle=driver.findElement(delete_bttn);
        //Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      //  wait.until(d -> deletebttnEle.isDisplayed());
        return deletebttnEle;
    }
    //to click on delete bttn to delete list
    public void deletebttn_click()
    {
        bttn_delete_list().click();
    }
    //locate confirm bttn of delete list
    public WebElement confirm_bttn()
    {
        By bttnconf=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-delete-modal/section/div[2]/us-button[1]/button/div/span");
        WebElement bttnconfEle=driver.findElement(bttnconf);
       // Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        //wait.until(d -> bttnconfEle.isDisplayed());
        return bttnconfEle;
    }
    //click on confirm bttn of delete list
    public void cofirm_bttn_click()
    {
        confirm_bttn().click();
        //Thread.sleep(2000);
    }
    //assert that list deleted


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
}
