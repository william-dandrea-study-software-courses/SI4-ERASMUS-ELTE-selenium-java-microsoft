import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


/**
 * @author D'Andréa William
 */
public class OutlookPage extends MicrosoftPageBase {

    private final static String DESTINATION_EMAIL = "williapile@gmail.com";
    private final static String DESTINATION_CONTENT = "Welcome from Selenium";
    private final static String DESTINATION_OBJECT = "Selenium Test";

    public OutlookPage(WebDriver driver, String page) throws IOException {
        super(driver, page);

        if (page != null) {
            this.driver.switchTo().window(page);
        }
    }

    public WriteEmail goToNewEmail() throws IOException, InterruptedException {

        this.waitAndReturnElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[1]/div/div/div/div/div/div[1]/div[1]/div/div/button")).click();

        return new WriteEmail(driver, page);
    }






}
