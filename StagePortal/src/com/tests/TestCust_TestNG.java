package com.tests;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.CustomPage;
import com.utils.OpenAppln;

public class TestCust_TestNG extends OpenAppln {

	String url = "https://staging-store.iidm.com/";

	// use billing adrs as shipping adrs by selecting checkbox
	@Test(enabled = false, priority = 0)
	public void test_use_billing_as_shipping() throws Exception {
		// add products, go to checkout page without login
		OpenAppln.open_app();
		OpenAppln.openCart("false");
		boolean res = CustomPage.use_billing_as_shipping();
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}
	//request for payterms as guest
	@Test(enabled = false, priority = 1)
	public void test_Requeste_For_Payterms() throws Exception {
		// add products, go to checkout page without login
		OpenAppln.open_app();
		OpenAppln.openCart("false");
		CustomPage.checkout("false");
		// request for pay terms
		boolean res = CustomPage.request_for_payterms();
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		// if (res) {
		// OpenAppln.openCart("false");
		// CustomPage.checkout("false");
		// this.test2_Credit_Card_without_Login();
		// } else {
		// OpenAppln.openCart("false");
		// this.test2_Credit_Card_without_Login();
		// }
		Assert.assertTrue(res);
	}

	// credit card payment as guest
	@Test(enabled = false, priority = 2)
	public void test_Credit_Card_without_Login() throws Exception {

		// add products, go to checkout page without login
		// OpenAppln.open_app();
		OpenAppln.openCart("false");
		CustomPage.checkout("false");
		// credit card payment without login
		boolean res = CustomPage.credit_card_or_net30("credit_card");
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	// request quote for price without login
	@Test(enabled = false, priority = 3)
	public void test_request_for_quote_guest() throws Exception {
		// request quote for price as guest
		System.out.println("here code is running here................................................");
		//OpenAppln.open_app();
		boolean res = CustomPage.request_for_quote_displayed_or_not_without_login(false);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
		Thread.sleep(1500);
	}

	// submit repair as guest
	@Test(enabled = false, priority = 4)
	public void test_submit_repair_as_guest() throws Exception {
		// submit repair as guest
		// OpenAppln.open_app();
		boolean res = CustomPage.submit_repair(false);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	// submit repair as login
	@Test(enabled = false, priority = 5)
	public void test_submit_repair_as_login() throws Exception {
		// create submit repair from store login
		// OpenAppln.open_app();
		OpenAppln.login();
		boolean res = CustomPage.submit_repair(true);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	// request quote for price as login
	@Test(enabled = false, priority = 6)
	public void test_request_for_quote_login() throws Exception {
		// request quote for price as login
		//OpenAppln.open_app();
		boolean res = CustomPage.request_for_quote_displayed_or_not_without_login(true);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
		Thread.sleep(1500);
	}

	// net 30 payment as login
	@Test(enabled = true, priority = 7)
	public void test_net30() throws Exception {
		OpenAppln.open_app();
		OpenAppln.openCart("true");
		CustomPage.checkout("true");
		// NET 30 payment
		CustomPage.credit_card_or_net30("NET 30");
		driver.switchTo().newWindow(WindowType.TAB).get(url);
	}

	// credit card payment as login
	@Test(enabled = true, priority = 8)
	public void test_Credit_Card_with_Login() throws Exception {
		// add products, go to checkout page without login
		// OpenAppln.open_app();
		OpenAppln.openCart("true");
		CustomPage.checkout("true");
		// credit card payment with login
		boolean res = CustomPage.credit_card_or_net30("credit_card");
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	// quote approve from client
	@Test(enabled = true, priority = 9)
	public void test_approve_quote_client() throws Exception {
		// quote approve from client
		//OpenAppln.open_app();

		boolean res = CustomPage.approve_quote(true);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
		Thread.sleep(1500);
	}

	// quote reject from client
	@Test(enabled = true, priority = 10)
	public void test_reject_quote_client() throws Exception {
		// quote reject from client
		//OpenAppln.open_app();
	
		boolean res = CustomPage.approve_quote(false);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
		Thread.sleep(1500);
	}

	// User Registration
	@Test(enabled = true, priority = 11)
	public void test_user_registration() throws Exception {
		// User Register
		CustomPage.registration();
	}

	// repair search By RMA id
	@Test(enabled = false, priority = 12)
	public void test_repair_search() throws Exception {
		// repair search
		//OpenAppln.open_app();
		OpenAppln.login();
		boolean res = CustomPage.repairs_search("RMA ID", "315701");
		Thread.sleep(1500);
		res = CustomPage.repairs_search("Item", "VX-1212");
		Thread.sleep(1500);
		res = CustomPage.repairs_search("PO Number", "TestPONumber_1");
		Thread.sleep(1500);
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	@Test(enabled = false, priority = 13)
	public void test_total_orders_value() throws Exception {
		// verify total count of open orders
		
		boolean res = CustomPage.verify_total_open_orders_value();
		driver.switchTo().newWindow(WindowType.TAB).get(url);
		Assert.assertTrue(res);
	}

	@Test(enabled = false)
	public void test6_create_sales_order() throws Exception {
		// create sales order from store login
		boolean res = CustomPage.create_sales_order_from_store();
		Assert.assertTrue(res);
	}
}
