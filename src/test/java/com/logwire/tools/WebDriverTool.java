package com.logwire.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class WebDriverTool {

    static public WebDriver driver;

    static public void setUpDriver(){
        String browser = System.getProperty("browser", "chrome");
        switch (browser.toLowerCase()) {
            case "chrome":
                // Créer un répertoire temporaire unique pour chaque session de Chrome
                try {
                    Path tempDir = Files.createTempDirectory("chrome-user-data-dir");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--user-data-dir=" + tempDir.toAbsolutePath().toString());
                    driver = new ChromeDriver(options);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;
                
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
    }

    static public WebDriver getDriver(){
        return driver;
    }

    static public void tearDown(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
