package com.conmui.tests;

import com.conmui.User;
import com.conmui.pages.HomePage;
import com.conmui.pages.SignupLoginPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 5: Register User with existing email
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase5 extends BaseTest {
    @Test
    public void registerWithExistingEmail() {
        HomePage homePage = new HomePage(driver);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String errorMessage = "Email Address already exist!";

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();

//        5. Verify 'New User Signup!' is visible
        assertTrue(signupLoginPage.isSignupHeaderVisible());
        assertEquals(SIGNUPLOGIN_SIGNUP_HEADER, signupLoginPage.getSignupHeaderText());

//        6. Enter name and already registered email address
        signupLoginPage.fillSignup(user.getUsername(), user.getEmail());

//        7. Click 'Signup' button
        signupLoginPage.clickSignup();

//        8. Verify error 'Email Address already exist!' is visible
        assertTrue(signupLoginPage.isSignupErrorMessageVisible());
        assertEquals(errorMessage, signupLoginPage.getSignupErrorMessageText());
    }
}
