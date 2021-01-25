package model;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends PageObject {

    @FindBy(id = "signin2")
    private WebElement signInButton;
    @FindBy(id = "sign-username")
    private WebElement signUsername;
    @FindBy(xpath = "//a[contains(@onclick,'notebook')]")
    private WebElement laptopsCategory;
    @FindBy(xpath = "//a[contains(@onclick,'phone')]")
    private WebElement phonesCategory;
    @FindBy(xpath = "//a[contains(@onclick,'monitors')]")
    private WebElement monitorsCategory;
    @FindBy(id = "tbodyid")
    private WebElement resultsTable;
    private By tableItems = By.xpath("//div[@id='tbodyid']/div");
    @FindBy(id = "cartur")
    private WebElement cartButton;

    //Sign In Alert
    @FindBy(id = "sign-password")
    private WebElement signPassword;
    @FindBy(xpath = "//button[@onclick='register()']")
    private WebElement signUpButton;
    @FindBy(id = "login2")
    private WebElement logInButton;
    @FindBy(id = "logout2")
    private WebElement logOutButton;

    //LogIn Alert
    @FindBy(id = "loginusername")
    private WebElement loginUsername;
    @FindBy(id = "loginpassword")
    private WebElement loginPassword;
    @FindBy(xpath = "//button[@onclick='logIn()']")
    private WebElement loginButtonAlert;

    //Add Cart Page
    private By addToCartButton = By.xpath("//a[contains(text(),'Add to cart')]");


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void signInUser(String username, String password) {
        signInButton.click();
        signUsername.sendKeys(username);
        signPassword.sendKeys(password);
        signUpButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }


    public void logInUser(String username, String password) {
        logInButton.click();
        loginUsername.sendKeys(username);
        loginPassword.sendKeys(password);
        loginButtonAlert.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@onclick='logIn()']")));
    }

    public void logOutUser() {
        logOutButton.click();
    }


    public void addCart(String product, String category) {
        selectCategory(category);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(tableItems)));
        List<WebElement> itemResult = resultsTable.findElements(tableItems).stream().filter(item -> item.findElement(By.className("card-title")).getText().equals(product)).collect(Collectors.toList());
        itemResult.forEach(item -> item.findElement(By.className("card-title")).click());
        wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public boolean ProductIsOnCart(String product) {
        cartButton.click();
        return resultsTable.findElements(By.xpath("//tr/td")).stream().anyMatch(item -> item.getText().contains(product));
    }

    private void selectCategory(String category) {
        switch (category) {
            case Constants.PHONES:
                phonesCategory.click();
                break;
            case Constants.LAPTOPS:
                laptopsCategory.click();
                break;
            case Constants.MONITORS:
                monitorsCategory.click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
    }
}
