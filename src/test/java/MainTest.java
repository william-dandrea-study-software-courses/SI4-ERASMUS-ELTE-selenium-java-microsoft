import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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



        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

    }


    @Test
    public void launchTest() throws InterruptedException, IOException {
        HomePage unlogedHomePage = new HomePage(this.driver, "UNDEF");
        unlogedHomePage.acceptCookies();
        Assert.assertTrue(unlogedHomePage.getBodyText().contains("Microsoft 2022"));



        LoginPage loginPage = unlogedHomePage.goToLoginPage();
        Assert.assertTrue(driver.getTitle().contains("Microsoft"));
        loginPage.fillMail();
        HomePage loggedHomePage = loginPage.fillPassword();
        Assert.assertTrue(loggedHomePage.connectedPopup().contains("sqat-dandrea"));

        OutlookPage outlookPage = loggedHomePage.goToOutlookPage();
        new WebDriverWait(driver, Duration.ofSeconds(3).toSeconds()).until(driver -> driver.findElement(By.id("app")));

        WriteEmail writeEmail = outlookPage.goToNewEmail();

        OutlookPage outlookPageAfterEmail =  writeEmail.sendNewEmail();
        outlookPageAfterEmail.closeTab();

        HomePage endSessionHomePage = loggedHomePage.disconnect();
        Assert.assertFalse(endSessionHomePage.connectedPopup().contains("sqat-dandrea"));

        Thread.sleep(5000);
    }



    @After
    public void afterClass() throws Exception {
        driver.manage().deleteAllCookies();
        Thread.sleep(1000);
        this.driver.quit();
    }
}
