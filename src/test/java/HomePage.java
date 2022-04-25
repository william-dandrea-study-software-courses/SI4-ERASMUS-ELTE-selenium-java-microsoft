import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

/**
 * @author D'Andréa William
 */
public class HomePage extends MicrosoftPageBase {


    public HomePage(WebDriver driver, String page) throws IOException {
        super(driver, page);

        if (Objects.equals(page, "UNDEF")) {
            this.driver.get(this.prop.getProperty("initialUrl"));
        } else {
            this.switchPage();
        }

    }

    public LoginPage goToLoginPage() throws IOException {

        // Go to login page
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        return new LoginPage(this.driver, this.page);
    }

    public void acceptCookies() {
        this.waitAndReturnElement(By.xpath("//*[@id=\"wcpConsentBannerCtrl\"]/div[2]/button[1]")).click();
    }

    public String connectedPopup() throws InterruptedException {
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        Thread.sleep(1000);
        return this.getBodyText();
    }

    public HomePage disconnect() throws IOException {
        this.switchPage();
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_headerPicture\"]")).click();
        this.waitAndReturnElement(By.xpath("//*[@id=\"mectrl_body_signOut\"]")).click();
        return new HomePage(this.driver, "UNDEF");
    }

    public OutlookPage goToOutlookPage() throws InterruptedException, IOException {

        this.waitAndReturnElement(By.xpath("//*[@id=\"uhf-c-nav\"]/ul/li/div/button")).click();
        Thread.sleep(1000);
        WebElement outlookLink = this.waitAndReturnElement(By.xpath("//*[@id=\"shellmenu_11\"]"));
        Thread.sleep(1000);

        new Actions(driver).keyDown(Keys.COMMAND).click(outlookLink).keyUp(Keys.COMMAND).build().perform();

        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        String page = (String) tabs.get(tabs.size()-1);

        return new OutlookPage(this.driver, page);
    }




}
