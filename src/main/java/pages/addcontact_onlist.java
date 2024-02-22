package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class addcontact_onlist {
    WebDriver driver;
    public addcontact_onlist(WebDriver driver)
    {
        this.driver=driver;
    }
    public WebElement loc_addtolist_bttn()
    {
        By addtolist=By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/div/div[2]/us-button[3]/button/div/span");
        WebElement addtolist_Ele=driver.findElement(addtolist);
        return addtolist_Ele;
    }
    public WebElement loc_addcontactstolist_popscrn()
    {
        By addcontacttolist_tap=By.xpath("//div[@class=\"mat-mdc-dialog-surface mdc-dialog__surface\"]//section[@class=\"d-flex flex-column p-5 gap-3 h-100\"]//div[@class=\"d-flex justify-content-between align-items-center mb-4\"]//div[@class=\"txt-lg\"]");
        WebElement addcontacttolist_Ele=driver.findElement(addcontacttolist_tap);
        return addcontacttolist_Ele;
    }
    public WebElement locatecheckbox()
    {
        By checkbox=By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[1]/div/app-contacts/section/div/div[2]/section/table/tbody/tr/td[1]/mat-checkbox/div/div/input");
        WebElement checkboxEle=driver.findElement(checkbox);
        return checkboxEle;
    }
    public WebElement addbutton() {
        By bttnadd = By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-addlist/section/div[3]/us-button[1]/button/div/span");
        WebElement bttnaddEle = driver.findElement(bttnadd);
        return bttnaddEle;
    }

    public WebElement locate_contacts_tap()
    {
        By managecontab=By.xpath("//*[@id=\"mat-tab-label-0-0\"]/span[2]");
        WebElement managecontab_Ele=driver.findElement(managecontab);
        return managecontab_Ele;
    }
    //locate search field of add contacts to lists pop screen
    public WebElement loc_search_addcontactstolist_popscren()
    {
        By addconttolstpopscrn=By.id("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-contactlists/section/div[2]/input");
        WebElement addconttolstpopscre_Ele=driver.findElement(addconttolstpopscrn);
        return addconttolstpopscre_Ele;
    }
    //allocate table related to add contacts to lists pop screen
    public WebElement allocate_tabel_on_popscreen()
    {
        By alloc_tabel_popscrn=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-contactlists/section/div[3]");
        WebElement alloc_tabel_popscrn_Ele=driver.findElement(alloc_tabel_popscrn);
        return alloc_tabel_popscrn_Ele;
    }

    //allocate check box of add contacts to list pop screen
    public WebElement loc_checkbox_popscren()
    {
        By locatecheckBox=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-contactlists/section/div[3]/div/table/tbody/tr/td[1]/mat-checkbox/div/div/input");
        WebElement locatecheckbox_Ele=driver.findElement(locatecheckBox);
        return locatecheckbox_Ele;
    }
    //locate save button on pop screen related to add contacts to list
    public WebElement loc_save_bttn_popscreen()
    {
        By locsavebtton_popscrn=By.xpath("/html/body/app-root/nb-layout/div[2]/div[2]/div/mat-dialog-container/div/div/app-contactlists/section/div[4]/us-button[2]/button/div");
        WebElement locsavebtton_popscrn_Ele=driver.findElement(locsavebtton_popscrn);
        return locsavebtton_popscrn_Ele;
    }
}
