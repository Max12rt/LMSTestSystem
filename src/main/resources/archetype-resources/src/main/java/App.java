import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WebDriver browser;
        // Set the system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/maks_plv/Downloads/chromedriver-mac-arm64/chromedriver");
        browser = new ChromeDriver();
        browser.get("https://lms.khpi.ucode-connect.study/login");
        browser.findElement(By.id("mat-input-0")).sendKeys("mtsjupa");
        browser.findElement(By.id("mat-input-1")).sendKeys("Qzwxec1234");
        browser.findElement(By.xpath("//button[@class='mat-focus-indicator mat-button mat-button-base']")).click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(60));
        try {
            WebElement buttonToClick = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[@class='mat-focus-indicator topbar-button mat-button mat-button-base ng-star-inserted'])[3]")));
            buttonToClick.click();
        } catch (NoSuchWindowException e) {
            e.printStackTrace();
            browser.quit();
        }

        List<WebElement> tbodyList = browser.findElements(By.xpath("//table[contains(@class, 'fc-scrollgrid-sync-table')]//tbody"));
        int clicks = 0;

        // Iterate over each tbody and click on the first tr
        for (WebElement tbody : tbodyList) {
            List<WebElement> timeSlots = tbody.findElements(By.xpath(".//tr[td[contains(@class, 'fc-timegrid-slot fc-timegrid-slot-lane') and @data-time]]"));

            for (WebElement timeSlot : timeSlots) {
                try {
                    WebElement tdToClick = wait.until(ExpectedConditions.elementToBeClickable(timeSlot.findElement(By.tagName("td"))));
                    tdToClick.click();
                    clicks++;

                    // Break the loop after clicking 8 times
                    if (clicks >= 8) {
                        break;
                    }
                } catch (TimeoutException | NoSuchElementException e) {
                    e.printStackTrace();
                }
            }

            // Break the outer loop after clicking 8 times
            if (clicks >= 8) {
                break;
            }
        }
        System.out.println("Hello world!");
    }
}
