package BAI_TAP;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import POM.RegisterPage;
import POM.SharedPage;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Test05 {
    @Test
    public static void testTc05() {
        // Init web driver session
        String firstName="Nguyen";
        String middleName="Minh Thien";
        String lastName="Phuc";
        String emailAddress = "phuc890@gmail.com";
        String password= "123456";
        String confirmPassword= password;
        String emailShared= "share@gmail.com";
        String message = "Check out my wishlist!";

        WebDriver driver = driverFactory.getChromeDriver();
        try {
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
            String successMessage = driver.findElement(By.cssSelector("li.success-msg span")).getText();
            String successWelcome = driver.findElement(By.cssSelector("p[class='welcome-msg']")).getText();
            if (successMessage.contains("Thank you for registering with Main Website Store.") && successWelcome.contains("WELCOME, " + firstName.toUpperCase() +" "+ middleName.toUpperCase() +" " + lastName.toUpperCase() + "!")) {
                System.out.println("Account registration done.");
                captureScreenshot(driver, "Account_Register_Success");
            } else {
                System.out.println("Account registration failed.");
            }
            Thread.sleep(2000);

            // Bước 6. Go to TV menu
            driver.findElement(By.linkText("TV")).click();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 7. Add product in your wish list - use product - LG LCD
            driver.findElement(By.xpath("(//a[@class='link-wishlist'][normalize-space()='Add to Wishlist'])[1]")).click();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 8. Click SHARE WISHLIST
            driver.findElement(By.xpath("(//span[contains(text(),'Share Wishlist')])[1]")).click();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);

            // Bước 9. In the next page, enter Email and a message, and click SHARE WISHLIST

            SharedPage sharedPage = new SharedPage(driver);
            sharedPage.enterEmailShared(emailShared);
            sharedPage.enterMessageShared(message);
            driver.findElement(By.cssSelector("button[title='Share Wishlist']")).click();
            Thread.sleep(2000);


            // Bước 10. Check wishlist is shared. Expected wishlist shared successfully.
            String successMessage2 = driver.findElement(By.xpath("(//span[normalize-space()='Your Wishlist has been shared.'])[1]")).getText();
            if (successMessage2.contains("Your Wishlist has been shared.")) {
                System.out.println("Wishlist shared successfully.");
                captureScreenshot(driver, "Wishlist_Sharing_Success");
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

    private static void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String screenshotFileName = screenshotName + "_"  + ".png";
        File destination = new File(System.getProperty("user.dir") + "/screenshots/" + screenshotFileName);
        try {
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + screenshotFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
