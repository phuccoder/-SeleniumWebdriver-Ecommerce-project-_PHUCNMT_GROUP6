package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CartPage {

    WebDriver driver;
    By addToCartButton = By.xpath("//span[contains(text(),'Add to Cart')]");
    By countryDropdown = By.id("country");
    By stateDropdown = By.id("region_id");
    By zipInput = By.id("postcode");
    By estimateLink = By.xpath("//span[contains(text(),'Estimate')]");
    By tickFlatRate = By.cssSelector("label[for='s_method_flatrate_flatrate']");
    By updateTotalButton = By.xpath("//span[contains(text(),'Update Total')]");

    By subtotalText = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)");

    By shippingText = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(2) > td:nth-child(2) > span:nth-child(1)");

    By grandTotal = By.cssSelector("strong span[class='price']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void chooseCountry() {
        WebElement dropdownElement = driver.findElement(countryDropdown);
        Select selectOption = new Select(dropdownElement);
        selectOption.selectByVisibleText("United States");
    }

    public void chooseState() {
        WebElement dropdownElement = driver.findElement(stateDropdown);
        Select selectOption = new Select(dropdownElement);
        selectOption.selectByVisibleText("Florida");
    }

    public void zipEmail(String zip) {
        WebElement emailElement = driver.findElement(zipInput);
        emailElement.clear();
        emailElement.sendKeys(zip);
    }

    public void clickEstimateLink() {
        driver.findElement(estimateLink).click();
    }
    public void clickFlatRate() {
        driver.findElement(tickFlatRate).click();
    }
    public void clickUpdateButton() {
        driver.findElement(updateTotalButton).click();
    }

    public String getSubtotal() {
        return driver.findElement(subtotalText).getText();
    }

    public String getShipping() {
        return driver.findElement(shippingText).getText();
    }

    public String getGrandTotal() {
        return driver.findElement(grandTotal).getText();
    }

}
