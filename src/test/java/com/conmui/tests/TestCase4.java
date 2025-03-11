package com.conmui.tests;

import com.conmui.User;
import com.conmui.pages.HomePage;
import com.conmui.pages.SignupLoginPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 4: Logout User
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase4 extends BaseTest {
    @Test
    public void userLogout() {
        HomePage homePage = new HomePage(driver);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String loggedInAsText = "Logged in as " + user.getUsername();

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();

//        5. Verify 'Login to your account' is visible
        assertTrue(signupLoginPage.isLoginHeaderVisible());
        assertEquals(SIGNUPLOGIN_LOGIN_HEADER, signupLoginPage.getLoginHeaderText());

//        6. Enter correct email address and password
        signupLoginPage.fillLogin(user.getEmail(), user.getPassword());

//        7. Click 'login' button
        homePage = signupLoginPage.clickLogin();

//        8. Verify that 'Logged in as username' is visible
        assertTrue(homePage.isLoggedInVisible());
        assertEquals(loggedInAsText, homePage.getLoggedInText());

//        9. Click 'Logout' button
        signupLoginPage = homePage.clickLogout();

//        10. Verify that user is navigated to login page
        verifyPageVisible(SIGNUPLOGIN_URL, SIGNUPLOGIN_TITLE);
    }
}
