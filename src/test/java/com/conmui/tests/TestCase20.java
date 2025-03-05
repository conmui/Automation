package com.conmui.tests;

import java.util.ArrayList;
import java.util.List;
import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductsPage;
import com.conmui.pages.SignupLoginPage;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 20: Search Products and Verify Cart After Login
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase20 extends BaseTest {
    @Test
    public void test() {
        HomePage homePage = new HomePage(driver);
        String searchText = "Jeans";
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731");

//        3. Click on 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        4. Verify user is navigated to ALL PRODUCTS page successfully
        assertEquals("https://automationexercise.com/products", productsPage.getUrl());
        assertEquals("Automation Exercise - All Products", productsPage.getPageTitle());
        assertTrue(productsPage.isHeaderVisible());
        assertEquals("ALL PRODUCTS", productsPage.getHeaderText());

//        5. Enter product name in search input and click search button
        productsPage.searchForProducts(searchText);

//        6. Verify 'SEARCHED PRODUCTS' is visible
        assertTrue(productsPage.isHeaderVisible());
        assertEquals("SEARCHED PRODUCTS", productsPage.getHeaderText());

//        7. Verify all the products related to search are visible
        List<WebElement> searchResults = productsPage.getSearchResults();
        verifySearchResults(searchResults, productsPage, searchText);

//        8. Add those products to cart
        List<Product> searchResultsList = addSearchResultsToCartAndList(searchResults, productsPage);

//        9. Click 'Cart' button and verify that products are visible in cart
        CartPage cartPage = productsPage.clickViewCart();

        List<WebElement> cartProducts = cartPage.getCartProducts();
        verifyCartWithSearchResults(cartProducts, searchResultsList, cartPage);

//        10. Click 'Signup / Login' button and submit login details
        SignupLoginPage signupLoginPage = cartPage.navigateToSignupLoginPage();
        signupLoginPage.fillLogin(user.getEmail(), user.getPassword());
        homePage = signupLoginPage.clickLogin();

//        11. Again, go to Cart page
        cartPage = homePage.navigateToCartPage();

//        12. Verify that those products are visible in cart after login as well
        verifyCartWithSearchResults(cartPage.getCartProducts(), searchResultsList, cartPage);
    }

    public void verifySearchResults(List<WebElement> searchResults, ProductsPage productsPage, String searchText) {
        for (WebElement product : searchResults) {
            assertTrue(productsPage.verifySearchResult(product, searchText));
        }
    }

    public List<Product> addSearchResultsToCartAndList(List<WebElement> searchResults, ProductsPage productsPage) {
        List<Product> searchResultsList = new ArrayList<>();

        for (int i = 0; i < searchResults.size(); i++) {
            WebElement product = searchResults.get(i);
            boolean isLastProduct = i == searchResults.size() - 1;

            //searchResultsList made for verifying products on next page (CartPage)
            int productId = productsPage.getProductId(product);
            String productName = productsPage.getProductName(product);
            int productPrice = productsPage.getProductPrice(product);
            searchResultsList.add(new Product(productId, productName, productPrice));

            productsPage.addSearchResultToCart(product);
            if (!isLastProduct) {
                productsPage.clickContinueShopping();
            }
        }
        return searchResultsList;
    }

    public void verifyCartWithSearchResults(List<WebElement> cartProducts, List<Product> searchResultsList, CartPage cartPage) {
        for (int i = 0; i < cartProducts.size(); i++) {
            WebElement cartProduct = cartProducts.get(i);
            Product searchResultProduct = searchResultsList.get(i);

            assertEquals(searchResultProduct.getName(), cartPage.getCartProductName(cartProduct));
            assertEquals(searchResultProduct.getPrice(), cartPage.getCartProductPrice(cartProduct));
            assertEquals(searchResultProduct.getQuantity(), cartPage.getCartProductQuantity(cartProduct));
            assertEquals(searchResultProduct.getTotal(), cartPage.getCartProductTotal(cartProduct));
        }
    }
}
