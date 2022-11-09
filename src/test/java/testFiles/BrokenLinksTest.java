package testFiles;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonComponents.BaseTest;
import commonComponents.TestRetry;
import pages.BrokenLinks;

public class BrokenLinksTest extends BaseTest{

    @Test(retryAnalyzer = TestRetry.class)
    public void brokenLink() throws MalformedURLException, IOException{
        BrokenLinks brokenLink = new BrokenLinks(driver);
        List<WebElement> links = brokenLink.links;
        System.out.println(links);
        SoftAssert test = new SoftAssert();
        for (WebElement link : links){
            String url= link.getAttribute("href");
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            Integer statusCode = conn.getResponseCode();
            System.out.println(statusCode);
            test.assertFalse(statusCode>400, link.getText() + " link is broken");
        }
       test.assertAll(); 
    System.out.println("tests");
    }
}
