import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author D'Andréa William
 */
public class LoginPage extends MicrosoftPageBase {

    public final static String MAIL = "sqat-dandrea-william@hotmail.com";
    public final static String PASSWORD = "dqeM?pSkgPGHtXpYDX5FD9D&aQ@!#ryNoq?3LKAe";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillMail() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"i0116\"]")).sendKeys(MAIL + "\n");
    }

    public HomePage fillPassword() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys(PASSWORD + "\n");
        return new HomePage(this.driver);
    }
}
