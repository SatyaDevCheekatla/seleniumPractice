package org.satya;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LaunchingBrowser {

    public static  void main(String [] args){
        try {
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Wait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            driver.get("https://rahulshettyacademy.com/");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//div[@class ='header-upper']/div/div/div/nav/div/ul/li/a[text() ='Practice']")).click();
            driver.findElement(By.xpath("//input[@id = 'name']")).sendKeys("Satya Dev");
            driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("cheekatlasatyadev@gmail.com");
            driver.findElement(By.xpath("//button[@id = 'form-submit']")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//a[contains(text(),'Automation Practise')]"));
            Actions action = new Actions(driver);
            for (WebElement element : elements) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
            }

           String parentWindow =  driver.getWindowHandle();
           Set<String> windows =driver.getWindowHandles();
            Iterator <String> itr = windows.iterator();

            while (itr.hasNext()){
                driver.switchTo().window(itr.next());
                System.out.println(driver.getTitle());

            }
            driver.switchTo().window(parentWindow);
            for(String window: windows){
                driver.switchTo().window(window);
                if(driver.getTitle().equalsIgnoreCase("ProtoCommerce")){
                    break;
                }
            }
            driver.findElement(By.xpath("//form/div/input[@name='name']")).sendKeys("Satya");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@class='ng-pristine ng-valid ng-touched']"))));
            String text = driver.findElement(By.xpath("//input[@class='ng-pristine ng-valid ng-touched']")).getText();
            System.out.println(text);
            driver.quit();
        }catch (Exception e){
            throw e;
        }
    }
}
