package com.conmui.tests;

import com.conmui.Product;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductsPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//        Test Case 12: Add Products in Cart
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase12 extends BaseTest {
    @Test
    public void addProductsInCart() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        Product product2 = new Product(2, "Men Tshirt", 400, 1);

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        5. Hover over first product and click 'Add to cart'
        productsPage.addProductToCart(product1.getId());

//        6. Click 'Continue Shopping' button
        productsPage.clickContinueShopping();

//        7. Hover over second product and click 'Add to cart'
        productsPage.addProductToCart(product2.getId());

//        8. Click 'View Cart' button
        CartPage cartPage = productsPage.clickViewCart();

//        9. Verify both products are added to Cart
        assertEquals(2, cartPage.getNumProductsInCart());

//        10. Verify their prices, quantity and total price
        verifyProductDetails(cartPage, product1);
        verifyProductDetails(cartPage, product2);
    }
}
