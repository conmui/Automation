package com.conmui.tests;

import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 15: Place Order: Register before Checkout
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase15 extends BaseTest {
    @Test
    public void placeOrderRegisterBeforeCheckout() {
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

//        5. Fill all details in Signup and create account
        signupLoginPage.fillSignup(user.getUsername(), user.getEmail());

        SignupPage signupPage = signupLoginPage.clickSignup();

        signupPage.fillAccountInformation(user.getTitle(), user.getUsername(), user.getPassword(), user.getDay(), user.getMonth(), user.getYear());
        signupPage.fillAddressInformation(user.getFirstName(), user.getLastName(), user.getCompany(), user.getAddress(), user.getAddress2(), user.getCountry(), user.getState(), user.getCity(), user.getZipCode(), user.getMobileNumber());

        AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();

//        6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        assertTrue(accountCreatedPage.isHeaderVisible());
        assertEquals(ACCOUNTCREATED_HEADER, accountCreatedPage.getHeaderText());

        homePage = accountCreatedPage.clickContinue();

//        7. Verify ' Logged in as username' at top
        assertTrue(homePage.isLoggedInVisible());
        assertEquals(loggedInAsText, homePage.getLoggedInText());

//        8. Add products to cart
        homePage.addProductToCart(product1.getId());

        homePage.clickContinueShopping();

        homePage.addProductToCart(product2.getId());

//        9. Click 'Cart' button
        CartPage cartPage = homePage.clickViewCart();

//        10. Verify that cart page is displayed
        verifyPageVisible(CART_URL, CART_TITLE);

//        11. Click Proceed To Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

//        12. Verify Address Details and Review Your Order
        verifyAddressDetails(user, checkoutPage, "delivery");
        verifyAddressDetails(user, checkoutPage, "invoice");

        verifyProductDetails(product1, checkoutPage);
        verifyProductDetails(product2, checkoutPage);

        assertEquals(cartTotal, checkoutPage.getCartTotal());

//        13. Enter description in comment text area and click 'Place Order'
        checkoutPage.fillComment(comment);

        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

//        14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPayment(user.getFullName(), user.getCardNumber(), user.getCvc(), user.getExpiryMonth(), user.getExpiryYear());

//        15. Click 'Pay and Confirm Order' button
        OrderPlacedPage orderPlacedPage = paymentPage.clickPayConfirmOrder();

//        16. Verify success message 'Your order has been placed successfully!'
        assertTrue(paymentPage.isSuccessMessageVisible());
        assertEquals(successMessage, paymentPage.getSuccessMessageText());

        homePage = orderPlacedPage.clickContinue();

//        17. Click 'Delete Account' button
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();

//        18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        assertTrue(accountDeletedPage.isHeaderVisible());
        assertEquals(ACCOUNTDELETED_HEADER, accountDeletedPage.getHeaderText());
    }
}
