package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.ConfirmationPage;
import pages.OrdersPage;
import pages.ProductCatalogue;
import testComponents.BaseTest;

public class CreateOrderTest extends BaseTest{
	String productName="ADIDAS ORIGINAL";
	
	@Test(groups="Smoke Tests", dataProvider="getData")
	public void createOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		Assert.assertEquals(productCatalogue.getToastMessage(), "Product Added To Cart");
		CartPage cartPage=productCatalogue.goToCartPage();  //method in abstract class which has been extended in productCatalogue page
		Boolean match=cartPage.verifyProductIsDisplayed(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		
	}
	
	@Test(dependsOnMethods="createOrder")
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("keshu@gmail.com", "Keshri@123");
		OrdersPage ordersPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderIsDisplayed(productName));
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"ritusingh@gmail.com","Keshri@123","ADIDAS ORIGINAL"},{"keshu@gmail.com", "Keshri@123","ADIDAS ORIGINAL"},};
//	}
	
	//If we have 15-20 variables then better use key value pair and send it to test
	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "keshu@gmail.com");
		map.put("password", "Keshri@123");
		map.put("productName", "ADIDAS ORIGINAL");
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "ritusingh@gmail.com");
		map1.put("password", "Keshri@123");
		map1.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] {{map},{map1}};
		}
	
	
	}
	
	


