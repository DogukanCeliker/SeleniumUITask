package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import java.io.*;
import java.util.List;

public class BoutiqueResponseTest extends MainTest {

    //Testcase for getting boutique links and their response status codes
    @Test
    public void linkResponseTest() throws IOException {

        String filepath = "files/boutiqueLinkResponse.csv";

        Login login = new Login(driver, wait);
        login.openWebsite();
        login.click(login.xBtn);

        List<WebElement> allLinks = driver.findElements(login.bigBoutiqueLinks);

        StringBuffer sb = new StringBuffer();

        login.boutiqueLinkResponse(allLinks,sb);
        File file = new File(filepath);
        file = login.WriteToCSV(file,sb);
        Assert.assertTrue(file.length()!=0);
    }
}