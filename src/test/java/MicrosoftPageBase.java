import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author D'Andréa William
 */
public class MicrosoftPageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public MicrosoftPageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

}
