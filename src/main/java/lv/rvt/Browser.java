package lv.rvt;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
    private WebDriver driver;
    private static final String URL = "https://pikcrvt.edupage.org/timetable/";

    public Browser(boolean headless) {
        WebDriverManager.chromedriver().setup();
        if (headless) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // modern headless mode
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-gpu"); // mostly for Windows stability
            options.addArguments("--no-sandbox"); // often useful in CI
            options.addArguments("--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else {
            driver = new ChromeDriver();
        }
        driver.get(URL);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));

    }

    public void minimizeBrowser() {
        driver.manage().window().minimize();
    }

    public void quit() {
        driver.quit();
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

    public List<String> getAllClasses() {
        this.clickOnClasses();
        List<String> classes = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".dropDownPanel > li > a"));
        for (WebElement webElement : elements) {
            classes.add(webElement.getText());
        }
        return classes;
    }

    public List<String> getAllTeachers() {
        this.clickOnTeachers();
        List<String> teachers = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".dropDownPanel > li > a"));
        for (WebElement webElement : elements) {
            teachers.add(webElement.getText());
        }
        return teachers;
    }

    public List<String> getAvailableTimeTableWeeks() {
        this.clickOnSelectTimeTablesWeek();
        List<String> timeTableWeeks = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector(".dropDownPanel > li > a"));
        for (WebElement webElement : elements) {
            timeTableWeeks.add(webElement.getText());
        }
        return timeTableWeeks;
    }

    public WebElement getSVGTimeTable() {
        WebElement svgElement = driver.findElement(By.cssSelector("svg"));
        return svgElement;
    }
}
