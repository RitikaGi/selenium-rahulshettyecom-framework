package tests.auth;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class LoginTest extends BaseTest{
	
	@Test(priority=1, description="TC001: Verify user can login with valid credentials")
	public void testValidLogin() {
		landingPage.loginApplication("keshu@gmail.com", "Keshri@123");
		Assert.assertEquals("Login Successfully", landingPage.getLoginMessage());
	}
	
	@Test(priority=2, description="TC002: Verify login fails with invalid email")
	public void testLoginWithInvalidEmail() {
		landingPage.loginApplication("keinvalid@gmail.com", "Keshri@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginMessage());
	}
	
	@Test(priority=3, description="TC003: Verify login fails with invalid password")
	public void testLoginWithInvalidPassword() {
		landingPage.loginApplication("keshu@gmail.com", "Keshri");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginMessage());
	}
	
	@Test(priority=4, description="TC004: Verify login fails with empty credentials")
	public void testLoginWithEmptyCredentials() {
		landingPage.loginToApplication("", "");
		Assert.assertTrue(landingPage.emailValidationMessageIsVisible());
		Assert.assertTrue(landingPage.passwordValidationMessageIsVisible());
	}
	
	@Test(priority=5, description="TC005: Verify forgot password link functionality")
	public void testForgetPasswordLink() {
		landingPage.clickForgetPassword();
		Assert.assertTrue(driver.getCurrentUrl().contains("password-new"));
	}
	
	@Test(priority=6, description="TC006: Verify logout functionality")
	public void testLogout() {
		landingPage.loginApplication("keshu@gmail.com", "Keshri@123");
		landingPage.signOut();
		Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"));
	}

}
