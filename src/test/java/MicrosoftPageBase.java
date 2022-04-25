import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


/**
 * @author D'Andréa William
 */
public class MicrosoftPageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String page;
    protected Properties prop;


    public MicrosoftPageBase(WebDriver driver, String page) throws IOException {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);

        this.prop = new Properties();
        FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
        prop.load(ip);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    public void switchPage() {
        if (this.page != null) {
            this.driver.switchTo().window(page);
        }
    }

    public void closeTab() {
        this.driver.close();


        for (String wdw : this.driver.getWindowHandles()) {
            driver.switchTo().window(wdw);
            break;
        }


    }

}
