import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * @author D'Andréa William
 */
public class LoginPage extends MicrosoftPageBase {

    public final static String MAIL = "sqat-dandrea-william@hotmail.com";
    public final static String PASSWORD = "dqeM?pSkgPGHtXpYDX5FD9D&aQ@!#ryNoq?3LKAe";

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
