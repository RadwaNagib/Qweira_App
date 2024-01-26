package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;

public class p001_login {
   WebDriver driver;
   public p001_login(WebDriver driver)
   {
       this.driver=driver;
   }
   public WebElement email()
   {
       By email=By.id("email");
       WebElement emailEle=driver.findElement(email);
       return emailEle;
   }
    public WebElement password() {
        By password = By.id("pass");
        WebElement passwordEle =driver.findElement(password);
        return passwordEle;
    }
    public WebElement login_button()
    {
        By loginbutton=By.xpath("/html/body/app-root/nb-layout/div/div/div/div/div/nb-layout-column/app-layout-one/app-login/div/div[2]/div[1]/div[1]/form/div[3]/us-button/button/div");
        WebElement loginbuttonEle=driver.findElement(loginbutton);
        return loginbuttonEle;
    }
    

}
