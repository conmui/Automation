package com.conmui.tests;

import com.conmui.Product;
import com.conmui.User;
import com.conmui.pages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 23: Verify address details in checkout page
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase23 extends BaseTest {
    @Test
    public void verifyAddressDetailsOnCheckoutPage() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String loggedInAsText = "Logged in as " + user.getUsername();

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();
        signupLoginPage.fillSignup(user.getUsername(), user.getEmail());
        SignupPage signupPage = signupLoginPage.clickSignup();

//        5. Fill all details in Signup and create account
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

//        9. Click 'Cart' button
        CartPage cartPage = homePage.navigateToCartPage();

//        10. Verify that cart page is displayed
        verifyPageVisible(CART_URL, CART_TITLE);

//        11. Click Proceed To Checkout
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

//        12. Verify that the delivery address is same address filled at the time registration of account
        verifyAddressDetails(user, checkoutPage, "delivery");

//        13. Verify that the billing address is same address filled at the time registration of account
        verifyAddressDetails(user, checkoutPage, "invoice");

//        14. Click 'Delete Account' button
        AccountDeletedPage accountDeletedPage = checkoutPage.clickDeleteAccount();

//        15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
        assertTrue(accountDeletedPage.isHeaderVisible());
        assertEquals(ACCOUNTDELETED_HEADER, accountDeletedPage.getHeaderText());

        accountDeletedPage.clickContinue();
    }
}
