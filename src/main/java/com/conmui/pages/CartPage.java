package com.conmui.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private final List<WebElement> cartProducts = driver.findElements(By.xpath("//tr[contains(@id, 'product-')]"));
    private final By proceedToCheckout = By.cssSelector(".check_out");
    private final By registerLogin = By.cssSelector(".modal-body a[href='/login']");
    private final By signupLoginButton = By.linkText("Signup / Login");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumProductsInCart() {
        return cartProducts.size();
    }

    public String getProductName(int productId) {
        return getElementText(By.cssSelector("#product-" + productId + " h4"));
    }

    public int getProductPrice(int productId) {
        return extractNumValue(By.cssSelector("#product-" + productId + " .cart_price"));
    }

    public int getProductQuantity(int productId) {
        return extractNumValue(By.cssSelector("#product-" + productId + " .cart_quantity"));
    }

    public int getProductTotal(int productId) {
        return extractNumValue(By.cssSelector("#product-" + productId + " .cart_total_price"));
    }

    public CheckoutPage clickProceedToCheckout() {
        clickButton(proceedToCheckout);
        return new CheckoutPage(driver);
    }

    public SignupLoginPage clickRegisterLogin() {
        clickButton(registerLogin);
        return new SignupLoginPage(driver);
    }

    public void removeProduct(int productId) {
        clickButton(By.cssSelector("#product-" + productId + " .cart_quantity_delete"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("product-" + productId)));
    }

    public boolean isProductRemoved(int productId) {
        try {
            driver.findElement(By.id("product-" + productId));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public List<WebElement> getCartProducts() {
        return driver.findElements(By.xpath("//tr[contains(@id, 'product-')]"));
    }

    public String getCartProductName(WebElement product) {
        return getElementText(product.findElement(By.cssSelector(".cart_description > h4")));
    }

    public int getCartProductPrice(WebElement product) {
        return extractNumValue(product.findElement(By.cssSelector(".cart_price > p")));
    }

    public int getCartProductId(WebElement product) {
        WebElement element = product.findElement(By.cssSelector(".cart_delete > a"));
        return Integer.parseInt(element.getDomAttribute("data-product-id"));
    }

    public int getCartProductQuantity(WebElement product) {
        return extractNumValue(product.findElement(By.cssSelector(".cart_quantity > button")));
    }

    public int getCartProductTotal(WebElement product) {
        return extractNumValue(product.findElement(By.cssSelector(".cart_total_price")));
    }

    public SignupLoginPage navigateToSignupLoginPage() {
        clickButton(signupLoginButton);
        return new SignupLoginPage(driver);
    }
}
