package pages;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.Test;

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

    public void login_with_valid_data( WebDriver driver, String email, String paaword) throws InterruptedException {
        email().clear();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d ->email().isDisplayed());
        email().sendKeys(email);
        Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(d ->password().isDisplayed());
        password().sendKeys(paaword);
        Wait<WebDriver> wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(d ->login_button().isDisplayed());
        login_button().click();
       Thread.sleep(3000);
        new WebDriverWait(driver,Duration.ofSeconds(4)).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String actualResult= driver.getCurrentUrl();
        System.out.println("Device link is: "+actualResult);
        String expectedResult="http://173.212.222.155:4200/devices";
        Assert.assertEquals(actualResult,expectedResult,"Error Message: link login is wrong");

    }

}
