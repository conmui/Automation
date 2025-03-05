package com.conmui.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductsPage extends BasePage {
    private final WebElement searchInput = driver.findElement(By.id("search_product"));
    private final By searchButton = By.id("submit_search");
    private final By productsList = By.cssSelector(".features_items");
    private final By header = By.cssSelector(".features_items > .title");
    private final List<WebElement> products = driver.findElements(By.cssSelector(".single-products"));
    private final By viewProduct = By.linkText("View Product");
    private final By continueShopping = By.cssSelector(".modal-content button");
    private final By viewCart = By.linkText("View Cart");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void searchForProducts(String searchText) {
        searchInput.sendKeys(searchText);
        clickButton(searchButton);
    }

    public boolean isHeaderVisible() {
        return isElementVisible(header);
    }

    public String getHeaderText() {
        return getElementText(header);
    }

    public boolean isProductsListVisible() {
        return isElementVisible(productsList);
    }

    public boolean isProductsListFilled() {
        return !products.isEmpty();
    }

    public ProductDetailsPage clickViewProduct() {
        clickButton(viewProduct);
        return new ProductDetailsPage(driver);
    }

    public void addProductToCart(int productId) {
        WebElement product = driver.findElement(By.xpath("(//div[contains(@class, 'productinfo')])[" + productId + "]//p"));
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
        clickButton(By.cssSelector("a[data-product-id='" + productId + "']"));
    }

    //Modal Popup
    public void clickContinueShopping() {
        clickModalButton(continueShopping);
    }

    public CartPage clickViewCart() {
        clickModalButton(viewCart);
        return new CartPage(driver);
    }

    //Search Results Products
    public boolean verifySearchResult(WebElement product, String searchText) {
        String productName = getElementText(product.findElement(By.cssSelector(".productinfo > p")));
        return productName.contains(searchText);
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.cssSelector(".single-products"));
    }

    public void addSearchResultToCart(WebElement product) {
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
        clickButton(product.findElement(By.cssSelector(".overlay-content > .add-to-cart")));
    }

    public String getProductName(WebElement product) {
        return getElementText(product.findElement(By.cssSelector(".productinfo > p")));
    }

    public int getProductPrice(WebElement product) {
        return extractNumValue(product.findElement(By.cssSelector(".productinfo > h2")));
    }

    public int getProductId(WebElement product) {
        WebElement element = product.findElement(By.cssSelector(".productinfo > a"));
        return Integer.parseInt(element.getDomAttribute("data-product-id"));
    }
}
