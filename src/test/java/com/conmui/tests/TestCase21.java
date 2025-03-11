package com.conmui.tests;

import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductDetailsPage;
import com.conmui.pages.ProductsPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 21: Add review on product
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase21 extends BaseTest {
    @Test
    public void addProductReview() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");

        String reviewMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String successMessage = "Thank you for your review.";

//        3. Click on 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        4. Verify user is navigated to ALL PRODUCTS page successfully
        verifyPageVisible(PRODUCTS_URL, PRODUCTS_TITLE);

        assertTrue(productsPage.isHeaderVisible());
        assertEquals(PRODUCTS_HEADER, productsPage.getHeaderText());

//        5. Click on 'View Product' button
        ProductDetailsPage productDetailsPage = productsPage.clickViewProduct(product1.getId());

//        6. Verify 'Write Your Review' is visible
        assertTrue(productDetailsPage.isReviewHeaderVisible());
        assertEquals(PRODUCTDETAILS_HEADER, productDetailsPage.getReviewHeaderText());

//        7. Enter name, email and review
        productDetailsPage.fillReview(user.getFullName(), user.getEmail(), reviewMessage);

//        8. Click 'Submit' button
        productDetailsPage.submitReview();

//        9. Verify success message 'Thank you for your review.'
        assertTrue(productDetailsPage.isSubmitMessageVisible());
        assertEquals(successMessage, productDetailsPage.getSubmitMessageText());

    }
}
