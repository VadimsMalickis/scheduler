package lv.rvt;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        WebDriver driver = scheduler.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        scheduler.clickOnClasses();
        List<WebElement> classes = driver.findElements(By.cssSelector(".dropDownPanel > li > a"));
        scheduler.clickOnTeachers();
        List<WebElement> teachers = driver.findElements(By.cssSelector(".dropDownPanel > li > a"));
        

        driver.quit();
    }
}