package com.conmui.tests;

import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 16: Place Order: Login before Checkout
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase16 extends BaseTest {
    @Test
    public void placeOrderLoginBeforeCheckout() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        Product product2 = new Product(2, "Men Tshirt", 400, 1);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");

        String loggedInAsText = "Logged in as " + user.getUsername();
        int cartTotal = product1.getTotal() + product2.getTotal();
        String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String successMessage = "Your order has been placed successfully!";

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();

//        5. Fill email, password and click 'Login' button
        signupLoginPage.fillLogin(user.getEmail(), user.getPassword());
        homePage = signupLoginPage.clickLogin();

//        6. Verify 'Logged in as username' at top
        assertTrue(homePage.isLoggedInVisible());
        assertEquals(loggedInAsText, homePage.getLoggedInText());

//        7. Add products to cart
        homePage.addProductToCart(product1.getId());
        homePage.clickContinueShopping();
        homePage.addProductToCart(product2.getId());

//        8. Click 'Cart' button
        CartPage cartPage = homePage.clickViewCart();

//        9. Verify that cart page is displayed
        verifyPageVisible(CART_URL, CART_TITLE);

//        10. Click Proceed To Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

//        11. Verify Address Details and Review Your Order
        verifyAddressDetails(user, checkoutPage, "delivery");
        verifyAddressDetails(user, checkoutPage, "invoice");

        verifyProductDetails(product1, checkoutPage);
        verifyProductDetails(product2, checkoutPage);

        assertEquals(cartTotal, checkoutPage.getCartTotal());

//        12. Enter description in comment text area and click 'Place Order'
        checkoutPage.fillComment(comment);

        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

//        13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPayment(user.getFullName(), user.getCardNumber(), user.getCvc(), user.getExpiryMonth(), user.getExpiryYear());

//        14. Click 'Pay and Confirm Order' button
        OrderPlacedPage orderPlacedPage = paymentPage.clickPayConfirmOrder();

//        15. Verify success message 'Your order has been placed successfully!'
        assertTrue(paymentPage.isSuccessMessageVisible());
        assertEquals(successMessage, paymentPage.getSuccessMessageText());

        homePage = orderPlacedPage.clickContinue();

//        16. Click 'Delete Account' button
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();

//        17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        assertTrue(accountDeletedPage.isHeaderVisible());
        assertEquals(ACCOUNTDELETED_HEADER, accountDeletedPage.getHeaderText());
    }
}
