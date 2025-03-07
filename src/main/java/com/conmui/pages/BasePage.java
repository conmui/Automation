package com.conmui.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    private final By homeLink = By.linkText("Home");
    private final By productsLink = By.partialLinkText("Products");
    private final By cartLink = By.linkText("Cart");
    private final By signupLoginLink = By.linkText("Signup / Login");
    private final By logoutLink = By.linkText("Logout");
    private final By deleteAccountLink = By.linkText("Delete Account");
    private final By testCasesLink = By.linkText("Test Cases");
    private final By contactUsLink = By.linkText("Contact us");
    private final By footerHeader = By.cssSelector(".footer-widget h2");
    private final By successAlert = By.id("success-subscribe");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isElementVisible(By locator) {
        WebElement element = driver.findElement(locator);

        return element.isDisplayed();
    }

    public String getElementText(By locator) {
        WebElement element = driver.findElement(locator);

        return element.getText();
    }

    public String getElementText(WebElement locator) {
        return locator.getText();
    }

    public void clickButton(By locator) {
        WebElement button = driver.findElement(locator);

        button.click();
    }

    public void clickButton(WebElement locator) {
        locator.click();
    }

    public void fillInput(By locator, String fieldValue) {
        WebElement input = driver.findElement(locator);

        input.clear();
        input.sendKeys(fieldValue);
    }

    public void selectOption(String fieldValue) {
        WebElement option = driver.findElement(By.cssSelector("input[value='" + fieldValue + "']"));
        boolean isChecked = option.isSelected();

        if (!isChecked) option.click();
    }

    public void selectOption(By locator) {
        WebElement option = driver.findElement(locator);
        boolean isChecked = option.isSelected();

        if (!isChecked) option.click();
    }

    public void selectDropdown(By locator, String fieldValue) {
        Select dropdown = new Select(driver.findElement(locator));

        dropdown.selectByVisibleText(fieldValue);
    }

    public void clickModalButton(By locator) {
        WebElement button = driver.findElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(button));
        clickButton(locator);
    }

    public int extractNumValue(By locator) {
        String text = getElementText(locator);

        return Integer.parseInt(text.replaceAll("\\D+", ""));
    }

    public int extractNumValue(WebElement locator) {
        String text = locator.getText();

        return Integer.parseInt(text.replaceAll("\\D+", ""));
    }

    //Navbar
    public HomePage navigateToHomePage() {
        clickButton(homeLink);

        return new HomePage(driver);
    }

    public ProductsPage navigateToProductsPage() {
        clickButton(productsLink);

        return new ProductsPage(driver);
    }

    public CartPage navigateToCartPage() {
        clickButton(cartLink);

        return new CartPage(driver);
    }

    public SignupLoginPage navigateToSignupLoginPage() {
        clickButton(signupLoginLink);

        return new SignupLoginPage(driver);
    }

    public SignupLoginPage clickLogout() {
        clickButton(logoutLink);

        return new SignupLoginPage(driver);
    }

    public AccountDeletedPage clickDeleteAccount() {
        clickButton(deleteAccountLink);

        return new AccountDeletedPage(driver);
    }

    public TestCasesPage navigateToTestCasesPage() {
        clickButton(testCasesLink);

        return new TestCasesPage(driver);
    }

    public ContactUsPage navigateToContactUsPage() {
        clickButton(contactUsLink);

        return new ContactUsPage(driver);
    }

    //Footer
    public void scrollToFooter() {
        WebElement footer = driver.findElement(By.tagName("footer"));

        new Actions(driver).scrollToElement(footer).perform();
    }

    public boolean isFooterHeaderVisible() {
        return isElementVisible(footerHeader);
    }

    public String getFooterHeaderText() {
        return getElementText(footerHeader);
    }

    public void subscribe(String email) {
        fillInput(By.id("susbscribe_email"), email);

        clickButton(By.id("subscribe"));
    }

    public boolean isSubscribedAlertVisible() {
        return isElementVisible(successAlert);
    }

    public String getSubscribedAlertText() {
        return getElementText(successAlert);
    }
}
