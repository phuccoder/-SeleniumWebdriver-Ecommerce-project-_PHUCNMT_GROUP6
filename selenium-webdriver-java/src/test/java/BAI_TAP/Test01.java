package BAI_TAP;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class Test01 {
    public static void testTC01() {
        int scc = 0;
        String testCaseName = "Test01"; // Tên test case

        StringBuffer verificationError = new StringBuffer();
        // init web driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // Bước 2. Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(demoSite);

            try {
                AssertJUnit.assertEquals("This is demo site for ", demoSite);
            } catch (Error e) {
                verificationError.append(e.toString());
            }

            // timing
            Thread.sleep(1000);

            // Bước 3. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();
            // timing
            Thread.sleep(1000);

            // Bước 4. In the list of all mobile, select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");

            // timing
            Thread.sleep(1000);

            // Bước 5. Verify all products are sorted by name
            scc = (scc + 1);

            // Đặt tên cho file ảnh png dựa trên tên của test case
            String png = (testCaseName + ".png");

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(scrFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // End
        driver.quit();
    }
}
