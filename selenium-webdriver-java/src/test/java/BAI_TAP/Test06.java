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
            driver.findElement(By.linkText("MY WISHLIST")).click();

            //timing
            Thread.sleep(2000);

            //In next page, Click ADD TO CART link
            CartPage cartPage = new CartPage(driver);
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
            String successMessage = driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate']")).getText();
            String flatRateMoneyText = cartPage.flatRateMoneyCheck();
            if (successMessage.contains("Fixed - "+flatRateMoneyText)) {
                System.out.println("Shipping cost generated success");
                System.out.println(successMessage);

            } else {
                System.out.println("shipping cost generated unsuccessfully.");
            }
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

            String subtotal = cartPage.getSubtotal();
            String shipping = cartPage.getShipping();
            String grandTotal = cartPage.getGrandTotal();

            double subtotalValue = Double.parseDouble(subtotal.replaceAll("[^\\d.]+", ""));
            double shippingValue = Double.parseDouble(shipping.replaceAll("[^\\d.]+", ""));
            double grandTotalValue = Double.parseDouble(grandTotal.replaceAll("[^\\d.]+", ""));

            if (subtotalValue + shippingValue == grandTotalValue) {
                System.out.println("Shipping cost is added to total. Grand Total matches Subtotal + Shipping.");
                System.out.println(grandTotalValue);
            } else {
                System.out.println("Shipping cost is not added to total. Grand Total does not match Subtotal + Shipping.");
            }


            //Screenshot
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC06" + "_2.png");
            FileUtils.copyFile(scrFile, new File(png));
            Thread.sleep(2000);

            //Step11. Click "Proceed to Checkout"
            driver.findElement(By.cssSelector("li[class='method-checkout-cart-methods-onepage-bottom'] button[title='Proceed to Checkout']")).click();
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
            driver.findElement(By.cssSelector("button[onclick='billing.save()']")).click();
            Thread.sleep(4000);
            driver.findElement(By.cssSelector("button[onclick='shipping.save()'] span span")).click();
            Thread.sleep(2000);

            //Step13. In Shipping Method, Click Continue
            driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']")).click();
            Thread.sleep(4000);

            //Step14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            driver.findElement(By.xpath("//label[normalize-space()='Check / Money order']")).click();
            driver.findElement(By.xpath("//button[@onclick='payment.save()']")).click();
            Thread.sleep(4000);

            //Step15. Click 'PLACE ORDER' button
            driver.findElement(By.xpath("//button[@title='Place Order']")).click();
            Thread.sleep(3000);

            //Step16. Verify Oder is generated. Note the order number
            String successOrder = driver.findElement(By.xpath("//h1[normalize-space()='Your order has been received.']")).getText();
            String orderNumber = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > p:nth-child(3)")).getText();
            if (successOrder.contains("YOUR ORDER HAS BEEN RECEIVED")) {
                System.out.println("ORDER SUCCESS");
                System.out.println(successOrder);
                System.out.println("YOUR ORDER NUMBER IS : " + orderNumber);

            } else {
                System.out.println("ORDER FAILED");
            }
            Thread.sleep(2000);
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC06" + "_3.png");
            FileUtils.copyFile(scrFile, new File(png));
            Thread.sleep(2000);

        } catch (Exception e){
            e.printStackTrace();
        }

        //Quit browser session
        driver.quit();

    }
}
