package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class p002_updatelist {
    WebDriver driver;
   p002_createlist p002createlist=new p002_createlist(driver);

    public p002_updatelist(WebDriver driver) {
        this.driver = driver;
    }

//allocate search field
    public WebElement alocatesearch_field() {

        By search = By.id("search");
        WebElement searchEle = driver.findElement(search);
        return searchEle;
    }

//enter on search filed,the name of list
    public void edit_on_search_field(String Listname) throws InterruptedException
    {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> alocatesearch_field().isDisplayed());
       alocatesearch_field().sendKeys(Listname);
    }

    public void assert_edittap_apear(String Listname) {
        WebElement table = driver.findElement(By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div[1]/section/table"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> table.isDisplayed());
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
                if (cellText.contains(Listname)) {
                    // Perform assertion
                    System.out.println("Found the search text: " + cellText);
                    Assert.assertTrue(cellText.contains(Listname), "No search Result");
                    break;
                }

            }
        }
    }

    public WebElement edit_ele() {
        By edite = By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[2]/div/app-lists/section/div[1]/section/table/tbody/tr[1]/td[5]/div/span");
        WebElement editEle = driver.findElement(edite);
        return editEle;
    }

    public void edit_click() {
        edit_ele().click();
        //Thread.sleep(2000);
    }
//alocate editdata pop screen to edit on list name
    public WebElement loceditdata()
    {
        By editdata=By.xpath("//section//div//div[@class=\"txt-lg ng-star-inserted\"]");
        WebElement editdateaEle=driver.findElement(editdata);
        return editdateaEle;
    }
    //locate text that responsable for writing the new list name
    public WebElement edittext() {
        By edit_txt = By.xpath("//div//div//input[@placeholder=\"Name\"]");
        WebElement edit_txt_Ele = driver.findElement(edit_txt);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(d -> edit_txt_Ele.isDisplayed());
        return edit_txt_Ele;
    }

    //insert list name after update


    //locate update bttn
    public WebElement bttn_update() {
        By bttnupdate = By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-addlist/section/div[3]/us-button[1]/button/div/span");
        WebElement bttnupdateEle = driver.findElement(bttnupdate);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> bttnupdateEle.isDisplayed());
        return bttnupdateEle;
    }
    //locate mobile number of add contacts
    public WebElement phoneno_addcontact_popscreen()
    {
        By phoneno=By.id("phone");
        WebElement phoeno_Ele=driver.findElement(phoneno);
        return phoeno_Ele;
    }
    //click on update bttn

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
    public WebElement managecontact_label()
    {
        By managelbl=By.xpath("//div//span[@class=\"txt-lg main-heading\"]");
        WebElement managelbe_Ele=driver.findElement(managelbl);
        return managelbe_Ele;
    }
    public WebElement loc_popscrn_addlist_title()
    {
        By locname=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-addlist/section/div[1]/div[1]");
        WebElement locnameEle=driver.findElement(locname);
        return locnameEle;
    }
    public WebElement locatlist_name() {
        By entrlistname = By.xpath("//div//div//input[@placeholder=\"Name\"]");
        WebElement listnameEle = driver.findElement(entrlistname);
        return listnameEle;
    }

    public WebElement addbutton() {
        By bttnadd = By.xpath("//*[@id=\"mat-mdc-dialog-0\"]/div/div/app-addlist/section/div[3]/us-button[1]/button");
        WebElement bttnaddEle = driver.findElement(bttnadd);
        return bttnaddEle;
    }

}
