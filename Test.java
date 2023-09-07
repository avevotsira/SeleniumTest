import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class Test {
    public static void main(String[] args) {
        WebDriver driver;

        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\chant\\Desktop\\coding\\Aupp\\geckodriver-v0.33.0-win64\\geckodriver.exe");

        driver = new FirefoxDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://opensource-demo.orangehrmlive.com/");

        // Login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector(".orangehrm-login-button")).click();
        ;

        // Navigate to Leave List
        wait.until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("li.oxd-main-menu-item-wrapper:nth-child(3)"))).click();

        // Set search criteria
        WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                "div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)")));

        dateInput.clear();

        dateInput.sendKeys("2022-04-01");

        WebElement dateToInput = driver.findElement(By.cssSelector(
                "div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));

        dateToInput.sendKeys(Keys.CONTROL + "a");

        dateToInput.sendKeys("2023-03-31");

        driver.findElement(By.cssSelector("span.oxd-chip:nth-child(1) > i:nth-child(1)")).click();

        WebElement dropdown = driver
                .findElement(By.cssSelector(".oxd-multiselect-wrapper > div:nth-child(1) > div:nth-child(1)"));
        dropdown.click();
        // Locate the list of options in the drop-down
        WebElement takenOption = driver.findElement(By.cssSelector("div.oxd-select-option:nth-child(5)"));
        takenOption.click();

        WebElement leaveType = driver.findElement(By.cssSelector(
                "div.oxd-grid-item:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));

        leaveType.click();

        WebElement leaveCan = driver
                .findElement(By.cssSelector("div.oxd-select-option:nth-child(5) > span:nth-child(1)"));

        leaveCan.click();
        // Search
        driver.findElement(By.cssSelector("button.oxd-button:nth-child(3)")).click();

        // Get and print the number of results

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String totalRecord = driver.findElement(By.cssSelector("span.oxd-text:nth-child(1)")).getText();
        System.out.println(totalRecord);

        driver.quit();
    }
}
