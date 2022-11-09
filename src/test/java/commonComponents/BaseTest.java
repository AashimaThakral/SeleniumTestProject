package commonComponents;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    String browser = null;
    String url = null;
    public WebDriver driver ;

    @BeforeTest
    public void initializeDriver() throws FileNotFoundException {
        
        try {
            Properties prop = new Properties();
        FileInputStream stream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/testData/GlobalData.properties");
            prop.load(stream);
            browser = prop.getProperty("browser");
            url = prop.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (browser.equalsIgnoreCase("chrome")){
            //System.setProperty("webdriver.chrome.driver", "/Users/username/Downloads/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);

            Proxy proxy = new Proxy();
            proxy.setHttpProxy("ipAddress:4444");
            options.setCapability("proxy", proxy);

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            
        }
        else if (browser.equalsIgnoreCase("firefox")){
            //System.setProperty("webdriver.gecko.driver", "/Users/username/Downloads/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        }
        else if (browser.equalsIgnoreCase("edge")) {
            //System.setProperty("webdriver.edge.driver", "/Users/username/Downloads/msedgedriver");
            EdgeOptions options = new EdgeOptions();
            options.setAcceptInsecureCerts(true);
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(options);
        }
        else if (browser.equalsIgnoreCase("safari")) {
            //System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public String getScreenshot (String testCaseName, WebDriver driver) throws IOException {
        String filePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(filePath));
        return filePath;
    }

    @AfterTest
    public void closeDriver(){
        driver.manage().deleteAllCookies();
        driver.manage().deleteCookieNamed("sessionKey");
        driver.close();
    }
    
}
