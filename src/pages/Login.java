package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login extends Main {

    public By loginBtn = By.cssSelector(".account-button.login");
    public By xBtn = By.cssSelector("a[title='Kapat']");
    public By loginPopup = By.cssSelector(".popup-form-main");
    public By notLoginContainer = By.cssSelector("#not-logged-in-container");
    public By loginContainer = By.cssSelector("#logged-in-container");
    public By emailLabel = By.cssSelector("#email");
    public By passwordLabel = By.cssSelector("#password");
    public By submitBtn = By.cssSelector(".login-submit.send-button");
    public By errorBox = By.cssSelector("#errorBox");
    public By bigBoutiqueLinks = By.cssSelector(".butik-large-image > a");
    public By imageLinks = By.cssSelector(".butik-large-image > a > img");

    public Login(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //method for clicking to element
    public void click(By submitBtn) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));
        driver.findElement(submitBtn).click();
    }
    //method for opening the website
    public void openWebsite() {
        driver.get("https://www.trendyol.com");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }
    //method for hover on an element
    public void hoverTo(By hover) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hover));
        Actions act = new Actions(driver);
        WebElement element = driver.findElement(hover);
        act.moveToElement(element).build().perform();
    }
    //method for checking whether the element is displayed or not on the screen
    public void checkElementDisplayed(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    //method for inputting e-mail password
    public void typeCredentials(By locator, String input) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(input);
    }
    //method for whether error message is matching with the expected message or not
    public void checkErrorMessageMatching(By element, String input)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        String elementValue = driver.findElement(element).getText();
        Assert.assertEquals(input,elementValue);
    }
    //method for using while login
    public void LoginMethod(Login login)
    {
        login.openWebsite();
        login.click(login.xBtn);
        login.hoverTo(login.notLoginContainer);
        login.click(login.loginBtn);
        login.checkElementDisplayed(login.loginPopup);
        login.checkElementDisplayed(login.emailLabel);
    }
    //method for writing data into csv file
    public File WriteToCSV(File file,StringBuffer sb)
    {
        try (PrintWriter writer = new PrintWriter(file)){

            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
    //method for getting all image loading times and response codes as a string
    public StringBuffer boutiqueImageLoadTime(List<WebElement> imageLinks,StringBuffer sb)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        sb.append("ImageLoadTime, Response" + "\n");

        for(int i=0; i < imageLinks.size();i++)
        {
            js.executeScript("window.scrollBy(0,250)", "");
            RestAssured.baseURI = "https://www.trendyol.com";
            RequestSpecification request = RestAssured.given();
            Response response = request.get(imageLinks.get(i).getAttribute("src"));
            long time = response.getTime();
            int statusCode = response.statusCode();
            System.out.println(time + "\n");

            sb.append("\"" + time + "\"" + ",");
            sb.append("\"" + statusCode + "\"" + "\n");
        }
        return sb;
    }
    //method for getting all boutique links and response codes as a string
    public StringBuffer boutiqueLinkResponse(List<WebElement> allLinks,StringBuffer sb) {

        sb.append("Boutique Links, Response" + "\n");

        for (int i = 0; i < allLinks.size(); i++) {

            RestAssured.baseURI = "https://www.trendyol.com";
            RequestSpecification request = RestAssured.given();
            Response response = request.get(allLinks.get(i).getAttribute("href"));
            int statusCode = response.statusCode();

            sb.append("\"" + allLinks.get(i).getAttribute("href") + "\"" + ",");
            sb.append("\"" + statusCode + "\"" + "\n");
        }
        return sb;
    }
}