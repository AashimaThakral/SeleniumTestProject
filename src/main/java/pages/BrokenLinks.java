package pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinks {

    WebDriver driver;
    public BrokenLinks(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(tagName="a")
    public List<WebElement> links;
}
