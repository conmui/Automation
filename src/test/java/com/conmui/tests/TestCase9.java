package com.conmui.tests;

import java.util.List;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductsPage;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 9: Search Product
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase9 extends BaseTest {
    @Test
    public void searchProductTest() {
        HomePage homePage = new HomePage(driver);
        String searchText = "Jeans";

//        3. Verify that home page is visible successfully
        assertEquals("https://automationexercise.com/", homePage.getUrl());
        assertEquals("Automation Exercise", homePage.getPageTitle());

//        4. Click on 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        5. Verify user is navigated to ALL PRODUCTS page successfully
        assertEquals("https://automationexercise.com/products", productsPage.getUrl());
        assertEquals("Automation Exercise - All Products", productsPage.getPageTitle());

//        6. Enter product name in search input and click search button
        productsPage.searchForProducts(searchText);

//        7. Verify 'SEARCHED PRODUCTS' is visible
        assertTrue(productsPage.isHeaderVisible());
        assertEquals("SEARCHED PRODUCTS", productsPage.getHeaderText());

//        8. Verify all the products related to search are visible
//        List<WebElement> searchResults = driver.findElements(By.cssSelector(".productinfo > p"));
        List<WebElement> searchResults = productsPage.getSearchResults();

        for (WebElement product : searchResults) {
            assertTrue(productsPage.verifySearchResult(product, searchText));
        }
    }
}
