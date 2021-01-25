package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.MainPage;
import org.junit.Assert;

public class StepsDef extends StepsBase{

    private final String username = fileUtils.getRandomAlphaNumString(8);
    private final String password = fileUtils.getRandomAlphaNumString(8);


    @Before
    public void setup(){
        setUp();
    }

    @Given("sign up an user with username and password")
    public void signUpAnUserWithAnd() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signInUser(username,password);
    }

    @And("login and logout with the user")
    public void loginAndLogoutWithTheUser() {
        MainPage mainPage = new MainPage(driver);
        mainPage.logInUser(username,password);
        mainPage.logOutUser();
    }

    @When("add {string} from {string} to the Cart")
    public void addFromToTheCart(String product, String category) {
        MainPage mainPage = new MainPage(driver);
        mainPage.logInUser(username,password);
        mainPage.addCart(product,category);

    }

    @Then("the cart had the {string} added")
    public void theCartHadTheAdded(String product) {
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue("The Cart doesn't have the product: "+ product,mainPage.ProductIsOnCart(product));
    }
    @After("@SmokeTest")
    public void closeAndClean(){
        driver.close();
        driver.quit();
    }
}
