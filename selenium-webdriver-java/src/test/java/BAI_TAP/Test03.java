package BAI_TAP;

import POM.ShoppingCartPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.OutputType.FILE;

public class Test03 {
    @Test
    public static void testTc03() {
        String quantity = "1000";

        StringBuffer verificationError = new StringBuffer();
        // Init web driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            // Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            Thread.sleep(1000);

            // Bước 2. Click on MOBILE menu
            shoppingCartPage.MobileClick();
            Thread.sleep(1000);

            // Bước 3. Click on "ADD TO CART" for Sony Xperia mobile
            shoppingCartPage.AddToCartClick();
            Thread.sleep(1000);

            // Bước 4. Change "QTY" value to 1000 and click "UPDATE" button
            shoppingCartPage.changeQuantity(quantity);
            shoppingCartPage.UpdateClick();
            Thread.sleep(1000);

            // Bước 5. Verify the error message

            if (shoppingCartPage.VerifyErrorMessage()) {
                System.out.println("Error message displayed: " + shoppingCartPage.GetErrorMessage());
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
                String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC03" + "-error-message.png");
                FileUtils.copyFile(scrFile, new File(png));
            } else {
                verificationError.append("Error message is not displayed. ");
            }
            Thread.sleep(1000);
            // Bước 6. Click on "EMPTY CART" link in the footer of list of all mobiles
            shoppingCartPage.clickEmptyCartLink();
            Thread.sleep(1000);

            // Bước 7. Verify cart is empty

            if (shoppingCartPage.verifyEmptyCart()) {
                System.out.println("Shopping cart is empty message displayed.");
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
                String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC03" + "-empty-cart.png");
                FileUtils.copyFile(scrFile, new File(png));
            } else {
                verificationError.append("Shopping cart is not empty. ");
            }
            Thread.sleep(1000);

            // Check for any verification errors
            if (verificationError.length() > 0) {
                throw new AssertionError("Test case failed: " + verificationError.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // End
            driver.quit();
        }
    }
}
