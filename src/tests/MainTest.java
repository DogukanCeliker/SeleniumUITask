package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.CrossBrowser;

import java.io.File;
import java.io.IOException;

public class MainTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void before() {
        driver = CrossBrowser.getBrowser("Chrome");
        wait = new WebDriverWait(driver, 20);
    }

    @AfterMethod(alwaysRun = true)
    public void after(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./ss/" + result.getName() + ".png"));
        }
        driver.quit();
    }
}
