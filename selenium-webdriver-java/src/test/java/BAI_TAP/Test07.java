
package BAI_TAP;

import POM.CartPage;
import POM.LoginPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

@Test
public class Test07 {
    public static void testTC06() {
        String emailAddress = "nhom6@gmail.com";
        String password = "123456";

        WebDriver driver = driverFactory.getChromeDriver();
        try {

            CartPage cartPage = new CartPage(driver);
            //Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //timing
            Thread.sleep(2000);

            //Click on my account link
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickMyAccountLink();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //timing
            Thread.sleep(2000);

            //Login in application using previously created credential
            loginPage.enterEmail(emailAddress);

            //timing
            Thread.sleep(1000);

            loginPage.enterPassword(password);

            //timing
            Thread.sleep(1000);

            //Click Login
            loginPage.clickLogin();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //timing
            Thread.sleep(2000);

            //Click on 'My Orders'
            cartPage.clickOrder();

            //timing
            Thread.sleep(2000);

            //Click on 'View Order'
            cartPage.clickViewOrder();

            //timing
            Thread.sleep(2000);

            //Click on 'Print Order' link
            cartPage.clickPrintOrder();

            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //Verify that you will be able to save previously placed order as a pdf file
            // Không biết verify cái gì =)))

            //Screenshot
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(FILE);
            String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC07" + "_1.png");
            FileUtils.copyFile(scrFile, new File(png));

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}

