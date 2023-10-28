package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    By myAccountLink = By.linkText("MY ACCOUNT");
    By createAccountLink = By.linkText("CREATE AN ACCOUNT");
    By firstNameInputLocator = By.linkText("firstname");
    By lastNameInputLocator = By.linkText("lastname");
    By emailInputLocator = By.linkText("email_address");
    By passwordInputLocator = By.id("password");
    By confirmPasswordInputLocator = By.id("confirmation");
    By registerButton = By.xpath("//button[@title='Register']");

    public RegisterPage(WebDriver driver){
        this.driver =driver;
    }

    public void clickMyAccountLink(){
        driver.findElement(myAccountLink).click();
    }
    public void clickCreateAccountLink() {
        driver.findElement(createAccountLink).click();
    }

    public void enterFirstName(String firstName){
        WebElement firstNameElement = driver.findElement(firstNameInputLocator);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        WebElement lastNameElement = driver.findElement(lastNameInputLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void enterEmail (String email){

    }

}
