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
    public void searchProductsTest() {
        HomePage homePage = new HomePage(driver);
        String searchText = "Jeans";

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        5. Verify user is navigated to ALL PRODUCTS page successfully
        verifyPageVisible(PRODUCTS_URL, PRODUCTS_TITLE);

//        6. Enter product name in search input and click search button
        productsPage.searchForProducts(searchText);

//        7. Verify 'SEARCHED PRODUCTS' is visible
        assertTrue(productsPage.isHeaderVisible());
        assertEquals(PRODUCTS_SEARCHED_HEADER, productsPage.getHeaderText());

//        8. Verify all the products related to search are visible
        verifyProductsRelatedToSearch(productsPage, searchText);
    }

    public void verifyProductsRelatedToSearch(ProductsPage productsPage, String searchText) {
        List<WebElement> searchResults = productsPage.getSearchResults();

        for (WebElement product : searchResults) {
            assertTrue(productsPage.verifySearchResult(product, searchText));
        }
    }
}
