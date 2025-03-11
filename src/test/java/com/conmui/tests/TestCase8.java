package com.conmui.tests;

import com.conmui.Product;
import com.conmui.pages.HomePage;
import com.conmui.pages.ProductDetailsPage;
import com.conmui.pages.ProductsPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

//        Test Case 8: Verify All Products and product detail page
//        1. Launch browser
//        2. Navigate to url 'http://automationexercise.com'
public class TestCase8 extends BaseTest {
    @Test
    public void allProductsProductDetail() {
        HomePage homePage = new HomePage(driver);
        Product product1 = new Product(1, "Blue Top", 500, 1);
        String expectedProductDetailsURL = "https://automationexercise.com/product_details/" + product1.getId();

//        3. Verify that home page is visible successfully
        verifyPageVisible(HOME_URL, HOME_TITLE);

//        4. Click on 'Products' button
        ProductsPage productsPage = homePage.navigateToProductsPage();

//        5. Verify user is navigated to ALL PRODUCTS page successfully
        verifyPageVisible(PRODUCTS_URL, PRODUCTS_TITLE);

//        6. The products list is visible with products
        assertTrue(productsPage.isProductsListVisible());
        assertTrue(productsPage.isProductsListFilled());

//        7. Click on 'View Product' of first product
        ProductDetailsPage productDetailsPage = productsPage.clickViewProduct(product1.getId());

//        8. User is landed to product detail page
        verifyPageVisible(expectedProductDetailsURL, PRODUCTDETAILS_TITLE);

//        9. Verify that product details is visible: product name, category, price, availability, condition, brand
        verifyProductDetailsVisible(productDetailsPage);
    }

    public void verifyProductDetailsVisible(ProductDetailsPage productDetailsPage) {
        assertTrue(productDetailsPage.isProductNameVisible());
        assertTrue(productDetailsPage.isProductCategoryVisible());
        assertTrue(productDetailsPage.isProductPriceVisible());
        assertTrue(productDetailsPage.isProductAvailabilityVisible());
        assertTrue(productDetailsPage.isProductConditionVisible());
        assertTrue(productDetailsPage.isProductBrandVisible());
    }
}
