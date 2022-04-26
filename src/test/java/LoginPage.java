import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * @author D'Andréa William
 */
public class LoginPage extends MicrosoftPageBase {

    public final String MAIL = this.prop.getProperty("mail");
    public final String PASSWORD = this.prop.getProperty("password");

    public LoginPage(WebDriver driver, String page) throws IOException {
        super(driver, page);
    }

    public void fillMail() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"i0116\"]")).sendKeys(MAIL + "\n");
    }

    public HomePage fillPassword() throws InterruptedException, IOException {
        this.waitAndReturnElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys(PASSWORD + "\n");

        // We arrive now on the page who ask for saving the account for the next sessions, we click on No
        this.waitAndReturnElement(By.xpath("//*[@id=\"idBtn_Back\"]")).click();

        return new HomePage(this.driver, driver.getWindowHandle());
    }
}
