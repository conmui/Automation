package com.conmui.tests;

import com.conmui.User;
import com.conmui.pages.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 1: Register User
//        1. Launch browser
//        2. Navigate to url 'https://automationexercise.com/'
public class TestCase1 extends BaseTest {
    @Test
    public void registerUser() {
        HomePage homePage = new HomePage(driver);
        User user = new User("dayman", "charliekelly@email.com", "Mr", "itsalwayssunny", "9", "February", "1976", "Charlie", "Kelly", "Paddy's Pub", "544 Mateo Street", "", "United States", "California", "Los Angeles", "90013", "2136265731", "1111222211112222", "178", "10", "2030");
        String loggedInAsText = "Logged in as " + user.getUsername();

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Signup / Login' button
        SignupLoginPage signupLoginPage = homePage.navigateToSignupLoginPage();

//        5. Verify 'New User Signup!' is visible
        assertTrue(signupLoginPage.isSignupHeaderVisible());
        assertEquals(SIGNUPLOGIN_SIGNUP_HEADER, signupLoginPage.getSignupHeaderText());

//        6. Enter name and email address
        signupLoginPage.fillSignup(user.getUsername(), user.getEmail());

//        7. Click 'Signup' button
        SignupPage signupPage = signupLoginPage.clickSignup();

//        8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        assertTrue(signupPage.isHeaderVisible());
        assertEquals(SIGNUP_HEADER, signupPage.getHeaderText());

//        9. Fill details: Title, Name, Email, Password, Date of birth
        signupPage.fillAccountInformation(user.getTitle(), user.getUsername(), user.getPassword(), user.getDay(), user.getMonth(), user.getYear());

//        10. Select checkbox 'Sign up for our newsletter!'
        signupPage.selectSignupNewsletterCheckbox();

//        11. Select checkbox 'Receive special offers from our partners!'
        signupPage.selectReceiveOffersCheckbox();

//        12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        signupPage.fillAddressInformation(user.getFirstName(), user.getLastName(), user.getCompany(), user.getAddress(), user.getAddress2(), user.getCountry(), user.getState(), user.getCity(), user.getZipCode(), user.getMobileNumber());

//        13. Click 'Create Account button'
        AccountCreatedPage accountCreatedPage = signupPage.clickCreateAccount();

//        14. Verify that 'ACCOUNT CREATED!' is visible
        assertTrue(accountCreatedPage.isHeaderVisible());
        assertEquals(ACCOUNTCREATED_HEADER, accountCreatedPage.getHeaderText());

//        15. Click 'Continue' button
        homePage = accountCreatedPage.clickContinue();

//        16. Verify that 'Logged in as username' is visible
        assertTrue(homePage.isLoggedInVisible());
        assertEquals(loggedInAsText, homePage.getLoggedInText());

//        17. Click 'Delete Account' button
        AccountDeletedPage accountDeletedPage = homePage.clickDeleteAccount();

//        18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        assertTrue(accountDeletedPage.isHeaderVisible());
        assertEquals(ACCOUNTDELETED_HEADER, accountDeletedPage.getHeaderText());

        accountDeletedPage.clickContinue();
    }
}