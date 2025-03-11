package com.conmui.tests;

import com.conmui.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase26 extends BaseTest {
    @Test
    public void verifyScrollUsingArrow() {
        HomePage homePage = new HomePage(driver);

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Scroll down page to bottom
        homePage.scrollToBottom();

//        5. Verify 'SUBSCRIPTION' is visible
        assertTrue(homePage.isFooterHeaderVisible());
        assertEquals(FOOTER_SUBSCRIPTION_HEADER, homePage.getFooterHeaderText());

//        6. Scroll up page to top
        homePage.scrollToTop();

//        7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        assertTrue(homePage.isMainHeaderVisible());
        assertEquals(HOME_HEADER, homePage.getMainHeaderText());
    }
}
