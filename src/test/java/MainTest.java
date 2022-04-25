import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * @author D'Andréa William
 */
public class MainTest {

    public WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();

    }


    @Test
    public void launchTest() throws InterruptedException {
        HomePage unlogedHomePage = new HomePage(this.driver);
        unlogedHomePage.acceptCookies();
        Assert.assertTrue(unlogedHomePage.getBodyText().contains("Microsoft 2022"));

        LoginPage loginPage = unlogedHomePage.goToLoginPage();
        loginPage.fillMail();
        HomePage loggedHomePage = loginPage.fillPassword();
        Assert.assertTrue(loggedHomePage.connectedPopup().contains("sqat-dandrea"));



        HomePage endSessionHomePage = loggedHomePage.disconnect();
        Assert.assertFalse(endSessionHomePage.connectedPopup().contains("sqat-dandrea"));
    }


}
