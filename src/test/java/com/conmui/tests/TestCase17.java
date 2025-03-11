package com.conmui.tests;

import com.conmui.Product;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 17: Remove Products From Cart
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase17 extends  BaseTest {
    @Test
    public void removeProductsFromCart() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        Product product2 = new Product(2, "Men Tshirt", 400, 1);

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Add products to cart
        homePage.addProductToCart(product1.getId());
        homePage.clickContinueShopping();

        homePage.addProductToCart(product2.getId());

//        5. Click 'Cart' button
        CartPage cartPage = homePage.clickViewCart();

//        6. Verify that cart page is displayed
        verifyPageVisible(CART_URL, CART_TITLE);

//        7. Click 'X' button corresponding to particular product
        cartPage.removeProduct(product1.getId());

//        8. Verify that product is removed from the cart
        assertTrue(cartPage.isProductRemoved(product1.getId()));
    }
}
