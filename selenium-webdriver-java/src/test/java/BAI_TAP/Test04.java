package BAI_TAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test04 {
    @Test
    public void testTc04() {
        int scc = 0;
        String testCaseName = "Test04";

        // Init web driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try {
            // Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            Thread.sleep(1000);

            // Bước 2. Click on MOBILE menu
            driver.findElement(By.linkText("MOBILE")).click();

            // Timing
            Thread.sleep(1000);

            // Bước 3. In mobile products list, click on "Add To Compare" for Sony Xperia & iPhone
            WebElement sonyXperiaCompare = driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[2]"));
            sonyXperiaCompare.click();

            WebElement iPhoneCompare = driver.findElement(By.xpath("(//a[@class='link-compare'][normalize-space()='Add to Compare'])[3]"));
            iPhoneCompare.click();

            Thread.sleep(1000);

            // Bước 4. Click on "COMPARE" button. A popup window opens
            driver.findElement(By.xpath("(//span[contains(text(),'Compare')])[2]")).click();

            // Chuyển tới cửa sổ popup
            String parentWindowHandle = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(parentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            Thread.sleep(1000);

            // Bước 5. Verify the pop-up window and check that the products are reflected in it
            WebElement popupHeading = driver.findElement(By.xpath("(//h1[normalize-space()='Compare Products'])[1]"));
            if (popupHeading.isDisplayed()) {
                System.out.println("Popup window opened with heading: " + popupHeading.getText());
            } else {
                System.out.println("Popup window not displayed.");

            }

            WebElement sonyXperiaInPopup = driver.findElement(By.xpath("(//a[normalize-space()='Sony Xperia'])[1]"));
            WebElement iPhoneInPopup = driver.findElement(By.xpath("(//a[normalize-space()='IPhone'])[1]"));
            if (sonyXperiaInPopup.isDisplayed() && iPhoneInPopup.isDisplayed()) {
                System.out.println("Products are reflected in the popup window:" + sonyXperiaInPopup.getText() + " " + iPhoneInPopup.getText());
            } else {
                System.out.println("Products are not reflected in the popup window.");

            }
            captureScreenshot(driver, "popup_window");

            Thread.sleep(1000);

            // Bước 6. Close the Popup Window
            driver.close();

            // Chuyển lại cửa sổ gốc
            driver.switchTo().window(parentWindowHandle);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // End
            driver.quit();
        }
    }

    private void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotFileName = screenshotName + "_" + timeStamp + ".png";
        File destination = new File(System.getProperty("user.dir") + "/screenshots/" + screenshotFileName);
        try {
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + screenshotFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
