package com.conmui.tests;

import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 14: Place Order: Register while Checkout
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase14 extends BaseTest {
    @Test
    public void placeOrderRegisterWhileCheckout() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        Product product2 = new Product(2, "Men Tshirt", 400, 1);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731");
        int expectedCartTotal = product1.getTotal() + product2.getTotal();
        String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        String nameOnCard = user.getFirstName() + " " + user.getLastName();
        String cardNumber = "1111222277778888";
        String cvc = "178";
        String expiryMonth = "10";
        String expiryYear = "2030";

//        3. Verify that home page is visible successfully
        verifyPageVisible(EXPECTED_HOME_URL, EXPECTED_HOME_TITLE);

//        4. Add products to cart
        homePage.addProductToCart(product1.getId());
        homePage.clickContinueShopping();
        homePage.addProductToCart(product2.getId());

//        5. Click 'Cart' button
        CartPage cartPage = homePage.clickViewCart();

//        6. Verify that cart page is displayed
        verifyPageVisible(EXPECTED_CART_URL, EXPECTED_CART_TITLE);

//        7. Click Proceed To Checkout
        cartPage.clickProceedToCheckout();

//        8. Click 'Register / Login' button
        SignupLoginPage signupLoginPage = cartPage.clickRegisterLogin();

//        9. Fill all details in Signup and create account
        signupLoginPage.fillSignup(user.getUsername(), user.getEmail());
        SignupPage signupPage = signupLoginPage.clickSignup();

        signupPage.fillAccountInformation(user.getTitle(), user.getUsername(), user.getPassword(), user.getDay(), user.getMonth(), user.getYear());
        signupPage.fillAddressInformation(user.getFirstName(), user.getLastName(), user.getCompany(), user.getAddress(), user.getAddress2(), user.getCountry(), user.getState(), user.getCity(), user.getZipCode(), user.getMobileNumber());
        AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();

//        10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        assertTrue(accountCreatedPage.isHeaderVisible());
        assertEquals("ACCOUNT CREATED!", accountCreatedPage.getHeaderText());
        homePage = accountCreatedPage.clickContinue();

//        11. Verify ' Logged in as username' at top
        assertTrue(homePage.isLoggedInVisible());
        assertEquals("Logged in as " + user.getUsername(), homePage.getLoggedInText());

//        12. Click 'Cart' button
        cartPage = homePage.navigateToCartPage();

//        13. Click 'Proceed To Checkout' button
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

//        14. Verify Address Details and Review Your Order
        verifyAddressDetails(user, checkoutPage, "delivery");
        verifyAddressDetails(user, checkoutPage, "invoice");

        verifyProductDetails(product1, checkoutPage);
        verifyProductDetails(product2, checkoutPage);

        assertEquals(expectedCartTotal, checkoutPage.getCartTotal());

//        15. Enter description in comment text area and click 'Place Order'
        checkoutPage.fillComment(comment);

        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();

//        16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        paymentPage.fillPayment(nameOnCard, cardNumber, cvc, expiryMonth, expiryYear);

//        17. Click 'Pay and Confirm Order' button
        OrderPlacedPage orderPlacedPage = paymentPage.clickPayConfirmOrder();

//        18. Verify success message 'Your order has been placed successfully!'
        assertTrue(paymentPage.isSuccessMessageVisible());
        assertEquals("Your order has been placed successfully!", paymentPage.getSuccessMessageText());

//        19. Click 'Delete Account' button
        AccountDeletedPage accountDeletedPage = orderPlacedPage.clickDeleteAccount();

//        20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        assertTrue(accountDeletedPage.isHeaderVisible());
        assertEquals("ACCOUNT DELETED!", accountDeletedPage.getHeaderText());
    }
}
