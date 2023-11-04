package BAI_TAP;

import POM.CartPage;
import POM.CheckOutPage;
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
public class Test06 {

    public static void testTC06() {

        String emailAddress = "nhom6@gmail.com";
        String password = "123456";
        String zip = "5000";
        String address="New Address";
        String firstName ="Group";
        String lastName ="Six";
        String address1 ="NVH";
        String city ="HCM";
        String state ="Florida";
        String country ="United States";
        String telephone ="1234567890";
        String company ="HCM";

        //init web driver session
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

            //Click on MY WISHLIST link
            cartPage.clickMyWishList();

            //timing
            Thread.sleep(2000);

            //In next page, Click ADD TO CART link

            cartPage.clickAddToCart();

            //timing
            Thread.sleep(2000);

            //Enter general shipping country, state/province and zip for the shipping cost estimate
            cartPage.chooseCountry();

            //timing
            Thread.sleep(1000);

            cartPage.chooseState();

            //timing
            Thread.sleep(1000);

            cartPage.zipEmail(zip);

            //timing
            Thread.sleep(1000);

            //Click Estimate
            cartPage.clickEstimateLink();

            //Verify Shipping cost generated

            //Step 8. Verify Shipping cost generated
            cartPage.verifyShippingCost("Fixed - " + cartPage.flatRateMoneyCheck());

            Thread.sleep(2000);
            //Screenshot
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC06" + "_1.png");
            FileUtils.copyFile(scrFile, new File(png));

            //Select Shipping Cost, Update Total
            cartPage.clickFlatRate();

            //timing
            Thread.sleep(2000);

            cartPage.clickUpdateButton();

            //timing
            Thread.sleep(2000);

            //Step10. Verify shipping cost is added to total
            cartPage.verifyGrandTotal();


            //Screenshot
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC06" + "_2.png");
            FileUtils.copyFile(scrFile, new File(png));
            Thread.sleep(2000);

            //Step11. Click "Proceed to Checkout"
            cartPage.clickProcedCheckOut();
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }


            //Step12a. Enter Billing Information, and click Continue
            CheckOutPage checkoutPage = new CheckOutPage(driver);
            checkoutPage.differenceAddressClick();
            Thread.sleep(2000);
            checkoutPage.selectAddress(address);
            Thread.sleep(4000);
            //Step12b. Enter Shipping Information, and click Continue
            checkoutPage.enterFirstName(firstName);
            checkoutPage.enterLastName(lastName);
            checkoutPage.enterCompany(company);
            checkoutPage.enterAddress1(address1);
            checkoutPage.enterCity(city);
            checkoutPage.selectState(state);
            checkoutPage.enterZip(zip);
            checkoutPage.selectCountry(country);
            checkoutPage.enterTelephone(telephone);
            Thread.sleep(4000);
            checkoutPage.clickBillingButton();
            Thread.sleep(4000);
            checkoutPage.clickSaveShippingInformation();
            Thread.sleep(2000);

            //Step13. In Shipping Method, Click Continue
            checkoutPage.clickSaveShippingMethod();
            Thread.sleep(4000);

            //Step14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            checkoutPage.selectPaymentMethod();
            checkoutPage.clickPaymentButton();
            Thread.sleep(4000);

            //Step15. Click 'PLACE ORDER' button
            checkoutPage.clickPlaceOrder();
            Thread.sleep(3000);

            //Step16. Verify Oder is generated. Note the order number
            checkoutPage.verifyOrder();

            Thread.sleep(2000);
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC06" + "_3.png");
            FileUtils.copyFile(scrFile, new File(png));
            Thread.sleep(2000);
            checkoutPage.orderNumber();

        } catch (Exception e){
            e.printStackTrace();
        }

        //Quit browser session
        driver.quit();

    }
}
