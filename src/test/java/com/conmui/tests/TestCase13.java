package com.conmui.tests;

import com.conmui.Product;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductDetailsPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//        Test Case 13: Verify Product quantity in Cart
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase13 extends BaseTest {
    @Test
    public void verifyProductQuantityInCart() {
        HomePage homePage = new HomePage(driver);
        Product product = new Product(1, "Blue Top", 500, 4);
        String expectedProductDetailsURL = "https://automationexercise.com/product_details/" + product.getId();

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click 'View Product' for any product on home page
        ProductDetailsPage productDetailsPage = homePage.viewProduct(product.getId());

//        5. Verify product detail is opened
        verifyPageVisible(expectedProductDetailsURL, PRODUCTDETAILS_TITLE);

//        6. Increase quantity to 4
        productDetailsPage.increaseQuantity(4);

//        7. Click 'Add to cart' button
        productDetailsPage.clickAddToCart();

//        8. Click 'View Cart' button
        CartPage cartPage = productDetailsPage.clickViewCart();

//        9. Verify that product is displayed in cart page with exact quantity
        assertEquals(1, cartPage.getNumProductsInCart());
        verifyProductDetails(cartPage, product);
    }
}
