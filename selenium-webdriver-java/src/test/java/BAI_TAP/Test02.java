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

import static org.testng.AssertJUnit.assertEquals;


@Test
public class Test02 {
    public static void testTC02() {
        int scc = 0;
        String testCaseName ="Test02";

        StringBuffer verificationError = new StringBuffer();
        //init web driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Bước 2. Click on  MOBILE  menu
            driver.findElement(By.linkText("MOBILE")).click();
            //timing
            Thread.sleep(1000);

            //Bước 3.In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
            System.out.println(XPeriaPrice);
            //timing
            Thread.sleep(1000);

            //Bước 4. Click on Sony Xperia mobile
            driver.findElement(By.id("product-collection-image-1")).click();
            //timing
            Thread.sleep(1000);

            //Bước 5. Read the Sony Xperia mobile from detail page.
            String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
            //Bước 6. Compare Product value in list and details page should be equal ($100).
            assertEquals(XPeriaPrice, detailPrice);
            if (XPeriaPrice.equals(detailPrice))
                System.out.printf("XPeriaPrice = detailPrice and price is: %s", detailPrice);
            //timing
            Thread.sleep(1000);

            String png = (testCaseName + ".png");

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(scrFile, new File(png));
        }catch (Exception e){
            e.printStackTrace();
        }

        // end
        driver.quit();

    }
}
