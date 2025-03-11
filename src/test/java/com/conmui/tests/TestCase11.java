package com.conmui.tests;

import com.conmui.User;
import com.conmui.pages.CartPage;
import com.conmui.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 11: Verify Subscription in Cart page
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase11 extends BaseTest {
    @Test
    public void subscribeOnCartPage() {
        HomePage homePage = new HomePage(driver);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String successMessage = "You have been successfully subscribed!";

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click 'Cart' button
        CartPage cartPage = homePage.navigateToCartPage();

//        5. Scroll down to footer
        cartPage.scrollToBottom();

//        6. Verify text 'SUBSCRIPTION'
        assertTrue(cartPage.isFooterHeaderVisible());
        assertEquals(FOOTER_SUBSCRIPTION_HEADER, cartPage.getFooterHeaderText());

//        7. Enter email address in input and click arrow button
        cartPage.subscribe(user.getEmail());

//        8. Verify success message 'You have been successfully subscribed!' is visible
        assertTrue(cartPage.isSubscribedAlertVisible());
        assertEquals(successMessage, cartPage.getSubscribedAlertText());
    }
}
