package com.conmui.tests;

import com.conmui.pages.CategoryProductsPage;
import com.conmui.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 18: View Category Products
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase18 extends BaseTest {
    @Test
    public void verifyCategoryProductsListing() {
        HomePage homePage = new HomePage(driver);
        String category1Name = "Women";
        int subcategory1Id = 1;
        String subcategory1Name = "Dress";
        String category2Name = "Men";
        int subcategory2Id = 3;
        String subcategory2Name = "Tshirts";

//        3. Verify that categories are visible on left sidebar
        assertTrue(homePage.isCategorySectionVisible());
        assertTrue(homePage.isCategoryHeaderVisible());
        assertEquals(HOME_CATEGORY_HEADER, homePage.getCategoryHeaderText());
        assertTrue(homePage.isCategorySectionFilled());

//        4. Click on 'Women' category
//        5. Click on any category link under 'Women' category, for example: Dress
        CategoryProductsPage categoryProductsPage = homePage.clickCategorySubcategory(category1Name, subcategory1Id);

//        6. Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
        verifyCategoryPage(subcategory1Id, subcategory1Name);

//        7. On left sidebar, click on any sub-category link of 'Men' category
        categoryProductsPage.clickCategorySubcategory(category2Name, subcategory2Id);

//        8. Verify that user is navigated to that category page
        verifyCategoryPage(subcategory2Id, subcategory2Name);
    }

    public void verifyCategoryPage(int subcategoryId, String subcategoryName) {
        String expectedURL = "https://automationexercise.com/category_products/" + subcategoryId;
        String expectedTitle = "Automation Exercise - " + subcategoryName + " Products";

        verifyPageVisible(expectedURL, expectedTitle);
    }
}
