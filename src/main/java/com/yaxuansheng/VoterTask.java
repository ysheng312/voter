package com.yaxuansheng;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.TimerTask;

/**
 * Created by Yaxuan on 1/18/2016.
 */
public class VoterTask extends TimerTask {
    @Override
    public void run() {
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.xingfuniao.org/toupiao/ShowItem.asp?id=100");

        WebElement link = driver.findElement(By.cssSelector("a[href*='toupiao.asp']"));

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 5000)");

        link.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.quit();
    }
}
