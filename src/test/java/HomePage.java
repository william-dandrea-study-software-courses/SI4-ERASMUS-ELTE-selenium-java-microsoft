import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author D'Andréa William
 */
public class HomePage extends MicrosoftPageBase {


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.microsoft.com/");
    }

    public LoginPage goToLoginPage() {

        // Go to login page
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        return new LoginPage(this.driver);
    }

    public void acceptCookies() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"wcpConsentBannerCtrl\"]/div[2]/button[1]")).click();
    }

    public String connectedPopup() throws InterruptedException {
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        Thread.sleep(1000);
        return this.getBodyText();
    }

    public HomePage disconnect() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_body_signOut\"]")).click();
        return new HomePage(this.driver);
    }



}
