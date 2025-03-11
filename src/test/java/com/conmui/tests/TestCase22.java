package com.conmui.tests;

import com.conmui.Product;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 22: Add to cart from Recommended items
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase22 extends BaseTest {
    @Test
    public void addToCartRecommendedItems() {
        HomePage homePage = new HomePage(driver);
        int productId = 1;

//        3. Scroll to bottom of page
        homePage.scrollToBottom();

//        4. Verify 'RECOMMENDED ITEMS' are visible
        assertTrue(homePage.isRecommendedItemsHeaderVisible());
        assertEquals(HOME_RECOMMENDEDITEMS_HEADER, homePage.getRecommendedItemsHeaderText());

//        5. Click on 'Add To Cart' on Recommended product
        homePage.addToCartRecommendedItem(productId);

        Product recommendedItem = homePage.saveRecommendedItem(productId, homePage);

//        6. Click on 'View Cart' button
        CartPage cartPage = homePage.clickViewCart();

//        7. Verify that product is displayed in cart page
        verifyRecommendedItemWithCartProduct(productId, recommendedItem, cartPage);
    }

    public void verifyRecommendedItemWithCartProduct(int productId, Product recommendedItem, CartPage cartPage) {
        WebElement cartProduct = driver.findElement(By.id("product-" + productId));

        assertEquals(recommendedItem.getName(), cartPage.getCartProductName(cartProduct));
        assertEquals(recommendedItem.getPrice(), cartPage.getCartProductPrice(cartProduct));
        assertEquals(recommendedItem.getQuantity(), cartPage.getCartProductQuantity(cartProduct));
        assertEquals(recommendedItem.getTotal(), cartPage.getCartProductTotal(cartProduct));
    }
}
