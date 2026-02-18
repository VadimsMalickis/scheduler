package lv.rvt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scheduler {
    private WebDriver driver;
    private static final String URL = "https://pikcrvt.edupage.org/timetable/";

    public Scheduler() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void clickOnClasses() {
        driver.findElement(By.xpath("//span[@title='Klases']")).click();
    }

    public void clickOnTeachers() {
        driver.findElement(By.xpath("//span[@title='Skolotāji']")).click();
    }

    public void clickOnSelectTimeTablesWeek() {
        driver.findElement(By.xpath("//span[@title='Ikdienas saraksts']")).click();

    }
}
