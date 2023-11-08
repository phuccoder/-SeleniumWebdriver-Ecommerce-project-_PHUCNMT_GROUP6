package BAI_TAP;

import POM.DetailPage;
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
import static org.testng.AssertJUnit.assertEquals;


@Test
public class Test02 {
    public static void testTC02() {


        //init web driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            DetailPage detailPage = new DetailPage(driver);
            //Bước 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //Bước 2. Click on  MOBILE  menu
            detailPage.moblieClick();
            //timing
            Thread.sleep(1000);

            //Bước 3.In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            String XPeriaPrice = detailPage.xpirePriceText();
            System.out.println(XPeriaPrice);
            //timing
            Thread.sleep(1000);

            //Bước 4. Click on Sony Xperia mobile
            detailPage.xpiraImageClick();
            //timing
            Thread.sleep(1000);

            //Bước 5. Read the Sony Xperia mobile from detail page.
            String detailPrice = detailPage.xpireDetailText();
            //Bước 6. Compare Product value in list and details page should be equal ($100).
            assertEquals(XPeriaPrice, detailPrice);
            if (XPeriaPrice.equals(detailPrice))
                System.out.printf("XPeriaPrice = detailPrice and price is: %s", detailPrice);
            //timing
            Thread.sleep(1000);

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(FILE);
            String png = ("E:\\selenium-webdriver-java\\screenshots\\" + "TC02" + ".png");
            FileUtils.copyFile(scrFile, new File(png));
        }catch (Exception e){
            e.printStackTrace();
        }

        // end
        driver.quit();

    }
}
