import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * @author D'Andréa William
 */
public class WriteEmail extends MicrosoftPageBase {

    private final String DESTINATION_EMAIL = this.prop.getProperty("DESTINATION_EMAIL");
    private final String DESTINATION_CONTENT = this.prop.getProperty("DESTINATION_CONTENT");
    private final String DESTINATION_OBJECT = this.prop.getProperty("DESTINATION_OBJECT");


    public WriteEmail(WebDriver driver, String page) throws IOException {
        super(driver, page);
    }


    public OutlookPage sendNewEmail() throws InterruptedException, IOException {
        this.waitAndReturnElement(By.xpath("//*[@id=\"docking_InitVisiblePart_0\"]/div[1]/div[1]/div[1]/div/div[3]/div/div/div/div/div/div[1]/div/div/input")).sendKeys(DESTINATION_EMAIL, Keys.ENTER);

        this.waitAndReturnElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[3]/div/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div/div/input")).sendKeys(DESTINATION_OBJECT, Keys.ENTER);

        // Now we will select all the content from the Content input for changing the police
        this.waitAndReturnElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[3]/div/div/div[3]/div[1]/div/div/div/div[1]/div[3]/div[1]/div/div[3]/div/button[2]/span/i")).click();
        this.waitAndReturnElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/div/div/ul/li[14]/button/div/div/div")).click();
        this.waitAndReturnElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[3]/div/div/div[3]/div[1]/div/div/div/div[1]/div[2]/div/div/div")).sendKeys(DESTINATION_CONTENT, Keys.ENTER);

        // Now we send the email
        this.waitAndReturnElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div[2]/div/div/div[3]/div/div/div[3]/div[1]/div/div/div/div[1]/div[3]/div[2]/div[1]/div/div/span/button[1]/span/span/span")).click();
        Thread.sleep(1000);

        return new OutlookPage(driver, page);
    }
}
