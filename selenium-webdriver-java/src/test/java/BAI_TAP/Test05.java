package BAI_TAP;

import POM.ShoppingCartPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import POM.RegisterPage;
import POM.SharedPage;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.OutputType.FILE;

public class Test05 {
    @Test
    public static void testTc05() {
        // Init web driver session
        String firstName="Nguyen";
        String middleName="Minh Thien";
        String lastName="Phuc";
        String emailAddress = "phuc892@gmail.com";
        String password= "123456";
        String confirmPassword= password;
        String emailShared= "share@gmail.com";
        String message = "Check out my wishlist!";

        WebDriver driver = driverFactory.getChromeDriver();
        try {
            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            SharedPage sharedPage = new SharedPage(driver);
            // Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            Thread.sleep(2000);

            // Bước 2. Click on my account link
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickMyAccountLink();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 3. Click Create an Account link and fill New User information excluding the registered Email ID
            registerPage.clickCreateAccountLink();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            registerPage.enterFirstName(firstName);
            registerPage.enterMiddleName(middleName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(emailAddress);
            registerPage.enterPassword(password);
            registerPage.enterConfirmPassword(confirmPassword);


            Thread.sleep(2000);


            // Bước 4: click on Register
            registerPage.clickRegister();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 5. Verify Registration is done. Expected account registration done.
            registerPage.registerWelcomeText();
            registerPage.registerMessageText();
            if (registerPage.registerMessageText().contains("Thank you for registering with Main Website Store.") && registerPage.registerWelcomeText().contains("WELCOME, " + firstName.toUpperCase() +" "+ middleName.toUpperCase() +" " + lastName.toUpperCase() + "!")) {
                System.out.println("Account registration done.");
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
                String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC05" + "-Register-Success.png");
                FileUtils.copyFile(scrFile, new File(png));
            } else {
                System.out.println("Account registration failed.");
            }
            Thread.sleep(2000);

            // Bước 6. Go to TV menu
            shoppingCartPage.TVMenuClick();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 7. Add product in your wish list - use product - LG LCD
            sharedPage.AddToWishListClick();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 8. Click SHARE WISHLIST
            shoppingCartPage.WishListClick();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 9. In the next page, enter Email and a message, and click SHARE WISHLIST


            sharedPage.enterEmailShared(emailShared);
            sharedPage.enterMessageShared(message);
            sharedPage.ShareWishListClick();
            Thread.sleep(2000);


            // Bước 10. Check wishlist is shared. Expected wishlist shared successfully.
            sharedPage.ShareWishListMessageText();
            if (sharedPage.ShareWishListMessageText().contains("Your Wishlist has been shared.")) {
                System.out.println("Wishlist shared successfully.");

            } else {
                System.out.println("Wishlist sharing failed.");

            }
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // End
            driver.quit();
        }
    }


}
