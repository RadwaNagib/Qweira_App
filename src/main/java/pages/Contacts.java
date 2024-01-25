package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Contacts {
    WebDriver driver;
   public Contacts(WebDriver driver)
   {
       this.driver=driver;
   }
public WebElement loc_add_contact()
{
    By locaddcontact=By.xpath("//button//div[@class=\"content d-flex flex-wrap align-items-center justify-content-center\"]");
    WebElement locaddcontact_Ele=driver.findElement(locaddcontact);
    //Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    //wait.until(d ->locaddcontact_Ele.isDisplayed());
    return locaddcontact_Ele;
}

    public WebElement managecontact_label()
    {
        By managelbl=By.xpath("//div//span[@class=\"txt-lg main-heading\"]");
        WebElement managelbe_Ele=driver.findElement(managelbl);
        return managelbe_Ele;
    }
    public WebElement loc_addcontact_bttn()
    {
        By addcontactbttn=By.xpath("//button//div//span[@class=\"btn-label mx-1\"]");
        WebElement addcontact_Ele=driver.findElement(addcontactbttn);
        return addcontact_Ele;
    }
    public WebElement addcontact_popscreen()
    {
        By popscreen_addcontact=By.xpath("//section//div//div[@class=\"txt-lg ng-star-inserted\"]");
        WebElement popscreen_addcontactEle=driver.findElement(popscreen_addcontact);
        return popscreen_addcontactEle;
    }
    //locate name on add contact pop screen
    public WebElement text_name_addcontact_popscreen()
    {
        By name_field=By.xpath("//section//div//div//input[@placeholder=\"Name\"]");
        WebElement name_field_Ele=driver.findElement(name_field);
        return name_field_Ele;
    }
    //locate mobile number of add contacts
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
    //locate manage contact label to a assert that navigate to manage contact page
    public WebElement allocat_managecontact_label()
    {
        By managecontactlbl=By.xpath("//section//div//div//span[@class=\"txt-lg main-heading\"]");
        WebElement managecontactEle=driver.findElement(managecontactlbl);
        return managecontactEle;
    }
}
