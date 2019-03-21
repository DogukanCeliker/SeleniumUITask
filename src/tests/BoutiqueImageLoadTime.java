package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BoutiqueImageLoadTime extends MainTest {

    //Testcase for getting boutique image response times and their response status codes
    @Test
    public void responseTimeTest() throws IOException {


        String filepath = "files/boutiqueImageTimes.csv";
        StringBuffer sb = new StringBuffer();


        Login login = new Login(driver, wait);
        login.openWebsite();
        login.click(login.xBtn);

        List<WebElement> imageLinks = driver.findElements(login.imageLinks);
        sb = login.boutiqueImageLoadTime(imageLinks,sb);

        File file = new File(filepath);
        file = login.WriteToCSV(file,sb);
        Assert.assertTrue(file.length()!=0);
    }
}
