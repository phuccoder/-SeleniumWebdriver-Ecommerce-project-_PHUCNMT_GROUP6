package BAI_TAP;

import POM.HomePage;
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

import static org.openqa.selenium.OutputType.FILE;

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

            HomePage homePage = new HomePage(driver);

            // Bước 2. Verify Title of the page
            String demoSite = homePage.getTitleText();
            System.out.println(demoSite);

            try {
                AssertJUnit.assertTrue(homePage.verifyDemoSiteTitle());
            } catch (Error e) {
                verificationError.append(e.toString());
            }


            // timing
            Thread.sleep(1000);

            // Bước 3. Click on -> MOBILE -> menu
            homePage.clickMobileMenu();
            // timing
            Thread.sleep(1000);

            // Bước 4. In the list of all mobile, select SORT BY -> dropdown as name
            homePage.selectSortByName();

            // timing
            Thread.sleep(1000);

            // Bước 5. Verify all products are sorted by name
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC01" + ".png");
            FileUtils.copyFile(scrFile, new File(png));
//
        } catch (Exception e) {
            e.printStackTrace();
        }

        // End
        driver.quit();
    }

}
