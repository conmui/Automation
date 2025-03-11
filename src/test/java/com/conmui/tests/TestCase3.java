package com.conmui.tests;

import com.conmui.User;
import com.conmui.pages.HomePage;
import com.conmui.pages.SignupLoginPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCase3 extends BaseTest {
//        Test Case 3: Login User with incorrect email and password
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
    @Test
    public void userLoginNegativeTest() {
        HomePage homePage = new HomePage(driver);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        User userIncorrect = new User("nightman", "jackkelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String errorMessage = "Your email or password is incorrect!";

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();

//        5. Verify 'Login to your account' is visible
        assertTrue(signupLoginPage.isLoginHeaderVisible());
        assertEquals(SIGNUPLOGIN_LOGIN_HEADER, signupLoginPage.getLoginHeaderText());

//        6. Enter incorrect email address and password
        signupLoginPage.fillLogin(userIncorrect.getEmail(), userIncorrect.getPassword());
//        signupLoginPage.fillLogin(userIncorrect.getEmail(), user.getPassword());
//        signupLoginPage.fillLogin(user.getEmail(), userIncorrect.getPassword());

//        7. Click 'login' button
        signupLoginPage.clickLogin();

//        8. Verify error 'Your email or password is incorrect!' is visible
        assertTrue(signupLoginPage.isLoginErrorMessageVisible());
        assertEquals(errorMessage, signupLoginPage.getLoginErrorMessageText());
    }
}
