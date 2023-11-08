package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    WebDriver driver;

    By titleText = By.cssSelector("h2");
    By mobileMenuLink = By.linkText("MOBILE");
    By sortByDropdown = By.cssSelector("select[title=\"Sort By\"]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(titleText).getText();
    }

    public void clickMobileMenu() {
        driver.findElement(mobileMenuLink).click();
    }

    public void selectSortByName() {
        new Select(driver.findElement(sortByDropdown)).selectByVisibleText("Name");
    }

    public boolean verifyDemoSiteTitle() {
        String demoSite = driver.findElement(titleText).getText();
        return demoSite.equals("This is demo site for ");
    }

}
