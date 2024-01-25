package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
public class update_contact {
    WebDriver driver;
    public update_contact(WebDriver driver)
    {
        this.driver=driver;
    }
public WebElement locate_edit_contact()
{
    By editcontact=By.xpath("/html/body/app-root/nb-layout/div[1]/div/div/div/div/nb-layout-column/app-layout-two/div/div/div/app-manage-contacts/section/mat-tab-group/div/mat-tab-body[1]/div/app-contacts/section/div/div[2]/section/table/tbody/tr/td[7]/div/span");
    WebElement editcontactEle=driver.findElement(editcontact);
    return editcontactEle;
}
//locate save button after update contact
public WebElement locate_save_btton()
{
    By save_bttn=By.xpath("//button[@class=\"bg-primary-800 btn rounded-3 button-size-sm txt-xs es-button dark\"]");
    WebElement save_bttn_Ele=driver.findElement(save_bttn);
    return save_bttn_Ele;
}
    public WebElement alocatesearch_field() {
        By search = By.id("search");
        WebElement searchEle = driver.findElement(search);
        return searchEle;
    }
    public WebElement addcontact_popscreen()
    {
        By popscreen_addcontact=By.xpath("//section//div//div[@class=\"txt-lg ng-star-inserted\"]");
        WebElement popscreen_addcontactEle=driver.findElement(popscreen_addcontact);
        return popscreen_addcontactEle;
    }
    public WebElement phoneno_addcontact_popscreen()
    {
        By phoneno=By.id("phone");
        WebElement phoeno_Ele=driver.findElement(phoneno);
        return phoeno_Ele;
    }
    //locate add button of add contact on popscreen
    public WebElement addbttn_addcontact_popscreen()
    {
        By addbttn=By.xpath("//button[@class=\"bg-primary-800 btn rounded-3 button-size-sm txt-xs es-button dark\"]");
        WebElement addbtn_Ele=driver.findElement(addbttn);
        return addbtn_Ele;
    }
    //locate name on add contact pop screen
    public WebElement text_name_addcontact_popscreen()
    {
        By name_field=By.xpath("//section//div//div//input[@placeholder=\"Name\"]");
        WebElement name_field_Ele=driver.findElement(name_field);
        return name_field_Ele;
    }

}
