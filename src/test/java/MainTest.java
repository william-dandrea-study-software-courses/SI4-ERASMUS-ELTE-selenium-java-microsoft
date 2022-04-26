import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


/**
 * @author D'Andréa William
 */
public class MainTest {

    public WebDriver driver;

    @Before
    public void setup() throws FileNotFoundException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");

        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();

    }


    public HomePage startHomePage() throws IOException {
        HomePage unlogedHomePage = new HomePage(this.driver, "UNDEF");
        unlogedHomePage.acceptCookies();
        return unlogedHomePage;
    }

    public HomePage login(HomePage unlogedHomePage) throws IOException, InterruptedException {
        LoginPage loginPage = unlogedHomePage.goToLoginPage();
        Assert.assertTrue(driver.getTitle().contains("Microsoft"));
        loginPage.fillMail();
        return loginPage.fillPassword();
    }

    public OutlookPage goToOutlookFromHomePage(HomePage loggedHomePage) throws IOException, InterruptedException {
        OutlookPage outlookPage = loggedHomePage.goToOutlookPage();
        new WebDriverWait(driver, Duration.ofSeconds(3).toSeconds()).until(driver -> driver.findElement(By.id("app")));
        return outlookPage;
    }

    public OutlookPage writeAndSendEmail(OutlookPage outlookPage) throws IOException, InterruptedException {
        WriteEmail writeEmail = outlookPage.goToNewEmail();
        return writeEmail.sendNewEmail();
    }



    @Test
    public void launchTest() throws InterruptedException, IOException {

        HomePage unlogedHomePage = startHomePage();
        Assert.assertTrue(unlogedHomePage.getBodyText().contains("Microsoft 2022"));

        HomePage loggedHomePage = login(unlogedHomePage);
        Assert.assertTrue(loggedHomePage.connectedPopup().contains("sqat-dandrea"));

        OutlookPage outlookPage = goToOutlookFromHomePage(loggedHomePage);
        OutlookPage outlookPageAfterEmail = writeAndSendEmail(outlookPage);
        outlookPageAfterEmail.closeTab();

        HomePage endSessionHomePage = loggedHomePage.disconnect();
        Assert.assertFalse(endSessionHomePage.connectedPopup().contains("sqat-dandrea"));
    }



    @After
    public void afterClass() throws Exception {
        driver.manage().deleteAllCookies();
        Thread.sleep(1000);
        this.driver.quit();
    }
}
