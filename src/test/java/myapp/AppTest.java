package myapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class AppTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Automatically manage ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Set ChromeOptions to prevent auto-closing
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("detach", true); // Keeps browser open
        options.addArguments("--remote-allow-origins=*"); // Fix for WebDriver security issue

        driver = new ChromeDriver(options);
    }

    @Test
    public void testMicrosoftTeams() {
        System.out.println("Opening Microsoft Teams...");
        driver.get("https://teams.microsoft.com/");

        // Increase explicit wait time to avoid early termination
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.titleContains("Microsoft Teams"));

        System.out.println("Microsoft Teams loaded successfully.");

        // Keep browser open for user interaction
        try {
            Thread.sleep(120000); // Wait for 2 minutes before closing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        // Don't close the browser immediately
        if (driver != null) {
            System.out.println("Test completed, but browser will remain open.");
        }
    }
}
