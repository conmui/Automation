package com.conmui.tests;

import com.conmui.pages.HomePage;
import com.conmui.pages.TestCasesPage;
import org.junit.jupiter.api.Test;

//        Test Case 7: Verify Test Cases Page
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase7 extends BaseTest {
    @Test
    public void verifyTestCasesPage() {
        HomePage homePage = new HomePage(driver);

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Test Cases' button
        TestCasesPage testCasesPage = homePage.navigateToTestCasesPage();

//        5. Verify user is navigated to test cases page successfully
        verifyPageVisible(TESTCASES_URL, TESTCASES_TITLE);
    }
}
