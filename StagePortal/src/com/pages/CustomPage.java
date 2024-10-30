package com.pages;

import java.io.File;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.Configurator;
import com.utils.OpenAppln;

public class CustomPage extends OpenAppln {
	public static JavascriptExecutor js;

	public static void checkout(String is_login) throws Exception {
		String comp_name = "", f_name = "", l_name = "", adrs1_bill = "", city_bill = "", state_bill = "",
				postal_bill = "",
				adrs1_ship = "", city_ship = "", state_ship = "", postal_ship = "", ship_name = "",
				phone = "", mail = "";
		boolean is_empty = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Enter Phone Number']")));
			try {
				driver.findElement(By.xpath("//*[text() = 'No Products Found From Cart']")).isDisplayed();
				is_empty = true;
			} catch (Exception e) {
				is_empty = false;
			}
			if (is_empty) {
				driver.findElement(By.xpath("//*[contains(@src, 'chevron_left')]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Products']")));
				driver.navigate().to(
						"https://staging-store.iidm.com/yaskawa-m-2/p1b2d007-p1000-bypass-nema-12-208v-7-5a-p-854221");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[text() = ' Compare this Product']")));
				driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(1).click();
				driver.navigate().to("https://staging-store.iidm.com/index.php?route=checkout/cart");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Checkout']")));
				driver.findElement(By.xpath("//*[text() = 'Checkout']")).click();
			} else {

			}
			if (is_login.equals("true")) {
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Enter Phone Number']")));
				String ph = driver.findElement(By.xpath("//*[@placeholder = 'Enter Phone Number']"))
						.getAttribute("value");
				if (ph.equals("")) {
					driver.findElement(By.xpath("//*[@placeholder = 'Enter Phone Number']"))
							.sendKeys("1234567891");
				} else {

				}
				driver.findElement(By.xpath("//*[text()='Next']")).click();
				driver.findElement(By.xpath("//*[text()='Next']")).click();
				driver.findElement(By.xpath("//*[text()='Next']")).click();
			} else {
				comp_name = Configurator.readData("comp_name");
				f_name = Configurator.readData("first_name");
				l_name = Configurator.readData("last_name");
				mail = Configurator.readData("email");
				adrs1_bill = Configurator.readData("adrs1_billing");
				city_bill = Configurator.readData("city_billing");
				state_bill = Configurator.readData("state_billing");
				postal_bill = Configurator.readData("postal_billing");
				adrs1_ship = Configurator.readData("adrs1_shipping");
				city_ship = Configurator.readData("city_shipping");
				state_ship = Configurator.readData("state_shipping");
				postal_ship = Configurator.readData("postal_shipping");
				ship_name = Configurator.readData("ship_name");
				phone = Configurator.readData("phone");
				Actions act = new Actions(driver);
				// Company information
				driver.findElement(By.xpath("//*[@name='customer_name']")).sendKeys(comp_name);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@placeholder='Enter First Name']")).sendKeys(f_name);
				
				act.moveToElement(driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']"))).perform();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']")).sendKeys(l_name);
				driver.findElement(By.xpath("//*[@placeholder = 'Enter Phone Number']")).sendKeys(phone);
				driver.findElement(By.xpath("//*[@placeholder = 'Enter Email ID']")).sendKeys(mail);
				driver.findElement(By.xpath("//*[text()='Next']")).click();
				// Billing information
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).sendKeys(adrs1_bill);
				driver.findElement(By.xpath("//*[@placeholder='Enter City']")).sendKeys(city_bill);
				driver.findElement(By.xpath("//*[text() = 'Select State']")).click();
				act.sendKeys(state_bill).perform();
				act.sendKeys(Keys.ENTER).perform();
				act.sendKeys(Keys.TAB).perform();
				act.sendKeys(postal_bill).perform();
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
				Thread.sleep(1000);
				act.sendKeys(Keys.ENTER).perform();
				driver.findElement(By.xpath("//*[text()='Next']")).click();
				// Shipping information
				driver.findElement(By.xpath("//*[@placeholder='Enter Ship To Name']")).sendKeys(ship_name);
				driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).sendKeys(adrs1_ship);
				driver.findElement(By.xpath("//*[@placeholder='Enter City']")).sendKeys(city_ship);
				driver.findElement(By.xpath("//*[text() = 'Select State']")).click();
				act.sendKeys(state_bill).perform();
				act.sendKeys(Keys.ENTER).perform();
				act.sendKeys(Keys.TAB).perform();
				act.sendKeys(postal_ship).perform();
				wait.until(ExpectedConditions
						.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
				Thread.sleep(1000);
				act.sendKeys(Keys.ENTER).perform();
				act.moveToElement(driver.findElement(By.xpath("//*[text()='Next']"))).build().perform();
				driver.findElement(By.xpath("//*[text()='Next']")).click();
				Thread.sleep(5000);
			}
		} catch (Exception e1) {

		}
	}

	public static void company_details() throws Exception {
		String comp_name = Configurator.readData("comp_name");
		String f_name = Configurator.readData("first_name");
		String l_name = Configurator.readData("last_name");
		String mail = Configurator.readData("email");
		String adrs1_bill = Configurator.readData("adrs1_billing");
		String city_bill = Configurator.readData("city_billing");
		String state_bill = Configurator.readData("state_billing");
		String postal_bill = Configurator.readData("postal_billing");
		String adrs1_ship = Configurator.readData("adrs1_shipping");
		String city_ship = Configurator.readData("city_shipping");
		String state_ship = Configurator.readData("state_shipping");
		String postal_ship = Configurator.readData("postal_shipping");
		String ship_name = Configurator.readData("ship_name");
		String phone = Configurator.readData("phone");
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		// Company information
		driver.findElement(By.xpath("//*[@name='customer_name']")).sendKeys(comp_name);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@placeholder='Enter First Name']")).sendKeys(f_name);
		act.moveToElement(driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']"))).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']")).sendKeys(l_name);
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Phone Number']")).sendKeys(phone);
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Email ID']")).sendKeys(mail);
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		// Billing information
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).sendKeys(adrs1_bill);
		driver.findElement(By.xpath("//*[@placeholder='Enter City']")).sendKeys(city_bill);
		driver.findElement(By.xpath("//*[text() = 'Select State']")).click();
		act.sendKeys(state_bill).perform();
		act.sendKeys(Keys.ENTER).perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys(postal_bill).perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		// Shipping information
		driver.findElement(By.xpath("//*[@placeholder='Enter Ship To Name']")).sendKeys(ship_name);
		driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).sendKeys(adrs1_ship);
		driver.findElement(By.xpath("//*[@placeholder='Enter City']")).sendKeys(city_ship);
		driver.findElement(By.xpath("//*[text() = 'Select State']")).click();
		act.sendKeys(state_bill).perform();
		act.sendKeys(Keys.ENTER).perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys(postal_ship).perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
		act.moveToElement(driver.findElement(By.xpath("//*[text()='Next']"))).build().perform();
		driver.findElement(By.xpath("//*[text()='Next']")).click();
	}

	public static boolean credit_card_or_net30(String payment_type) throws Exception {
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@value='" + payment_type + "']")).click();
		double sub_total = 0.0;
		double act_total = 0.0; double act_tax = 0.0; double exp_tax = 0.0;
		boolean res = false;
		double exp_total = 0.0;
		String time = java.time.LocalDateTime.now().toString().substring(11, 19).replace(":", "");
		WebElement container = driver.findElement(By.xpath("//*[contains(@class, 'Total_container')]"));
//		try {
			System.err.println("kfjdskjfndskjnfdkjsnf..................try --> "+ container.getText());
			sub_total = Double.parseDouble(container.findElements(By.tagName("h4")).get(1).getText()
							.replace("$", "").replace(",",""));
			act_total = Double.parseDouble(container.findElements(By.tagName("h4")).get(5).getText()
							.replace("$", "").replace(",",""));
			DecimalFormat dc = new DecimalFormat("0.00");
			act_tax = Double.parseDouble(container.findElements(By.tagName("h4")).get(3).getText()
						.replace("$", "").replace(",", ""));
			exp_tax = Double.parseDouble(dc.format(sub_total * 0.085));
			exp_total = sub_total+ Double.parseDouble(dc.format(exp_tax));
			System.out.println("sub total is "+sub_total);
			System.out.println("act total is "+act_total);
			System.out.println("exp total is "+exp_total);
			System.out.println("act tax is "+act_tax);
			System.out.println("exp tax is "+exp_tax);
			System.exit(0);
//		} catch (Exception e) {
			System.err.println("kfjdskjfndskjnfdkjsnf..................catch");
//		}
		if (act_total == exp_total) {
			driver.findElement(By.xpath("//*[text()='Proceed']")).click();
			Thread.sleep(1600);
			// entering the card details
			if (payment_type.equals("credit_card")) {
				driver.findElement(By.xpath("//*[@placeholder='Enter Name on the Card']"))
						.sendKeys("john");
				driver.findElement(By.xpath("//*[@placeholder='Enter Card Number']"))
						.sendKeys("4111 1111 1111 1111");
				driver.findElement(By.xpath("//*[@placeholder='MM / YY']")).sendKeys("1225");
				driver.findElement(By.xpath("//*[@placeholder='Enter CVC']")).sendKeys("111");

				driver.findElement(By.xpath("//*[text()='Proceed To Payment']")).click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[text() = 'Thanks for your order!']")));
				CustomPage.takesScreenshot("cc_order_summary.png");
				try {
					driver.findElement(By.xpath("//*[text() = 'Convenience Fee']")).isDisplayed();
					System.out.println("Convenience Fee is Displayed for Credit card");
					res = true;
				} catch (Exception e) {
					res = false;
				}
			} else if (payment_type.equals("NET 30")) {
				driver.findElement(By.xpath("//*[@name='po_number']")).sendKeys("PONum#" + time);
				driver.findElement(By.xpath("//*[text()='Approve']")).click();
				System.exit(0);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//*[text() = 'Thanks for your order!']")));
				CustomPage.takesScreenshot("cc_order_summary.png");
				try {
					driver.findElement(By.xpath("//*[text() = 'Convenience Fee']")).isDisplayed();
					System.out.println("Convenience Fee is Displayed for " + payment_type);
					res = false;
				} catch (Exception e) {
					res = true;
				}
			}

		} else {
			System.out.println("Total valus displaying is wrong....!");
			System.out.println("Sub total value is " + sub_total);
			System.out.println("Actual Total is " + act_total);
			System.out.println("Expected Total is " + exp_total);
		}
		return res;
		// ................
	}

	public static boolean request_for_payterms() throws Exception {
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.findElement(By.xpath("//*[@value='req_pay_terms']")).click();
		driver.findElement(By.xpath("//*[text() ='Proceed']")).click();
		// Form submission
		driver.findElement(By.xpath("//*[@placeholder ='Enter Legal Name of Company']"))
				.sendKeys(Configurator.readData("legal_comp_name"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Federal Tax ID']"))
				.sendKeys(Configurator.readData("fed_tax_id"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Years in Business']"))
				.sendKeys(Configurator.readData("years_in_bus"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Main Number']"))
				.sendKeys(Configurator.readData("main_num"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Fax']")).sendKeys(Configurator.readData("fax"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Shipping Agency']"))
				.sendKeys(Configurator.readData("shipp_agency_act_num"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Billing Address....']"))
				.sendKeys(Configurator.readData("bill_adrs"));
		driver.findElement(By.xpath("//*[@placeholder ='Enter Shipping Address... ']"))
				.sendKeys(Configurator.readData("ship_adrs"));
		driver.findElement(By.xpath("//*[@name ='blling_address1']"))
				.sendKeys(Configurator.readData("bill_city"));
		driver.findElement(By.xpath("//*[@name ='blling_address2']"))
				.sendKeys(Configurator.readData("bill_state"));
		driver.findElement(By.xpath("//*[@name ='billing_zip_code']"))
				.sendKeys(Configurator.readData("bill_zip"));
		driver.findElement(By.xpath("//*[@name ='shipping_address1']"))
				.sendKeys(Configurator.readData("ship_city"));
		driver.findElement(By.xpath("//*[@name ='shipping_address2']"))
				.sendKeys(Configurator.readData("ship_state"));
		driver.findElement(By.xpath("//*[@name ='shipping_zip_code']"))
				.sendKeys(Configurator.readData("ship_zip"));
		driver.findElement(By.xpath("//*[@name ='all_dbas']")).sendKeys(Configurator.readData("all_dbas"));
		// contact for account payable
		driver.findElement(By.xpath("//*[@name ='name']")).sendKeys(Configurator.readData("name"));
		driver.findElement(By.xpath("//*[@name ='accounts_payable_phone']"))
				.sendKeys(Configurator.readData("phone"));
		driver.findElement(By.xpath("//*[text() = 'Select Invoice delivery method']")).click();
		act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[@name = 'email_address_or_fax']"))
				.sendKeys(Configurator.readData("email_adrs"));
		// Trade references
		driver.findElement(By.xpath("//*[@name = 'name_of_company1']"))
				.sendKeys(Configurator.readData("trade_comp1_name"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_contact1']"))
				.sendKeys(Configurator.readData("trade_comp1_cont"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_email1']"))
				.sendKeys(Configurator.readData("trade_comp1_email"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_city1']"))
				.sendKeys(Configurator.readData("trade_comp1_city"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_state1']"))
				.sendKeys(Configurator.readData("trade_comp1_state"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_zip_code1']"))
				.sendKeys(Configurator.readData("trade_comp1_zip"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_address1']"))
				.sendKeys(Configurator.readData("trade_comp1_adrs"));
		driver.findElement(By.xpath("//*[@name = 'contact_name1']"))
				.sendKeys("trade ref contact name 1");

		driver.findElement(By.xpath("//*[@name = 'name_of_company2']"))
				.sendKeys(Configurator.readData("trade_comp2_name"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_contact2']"))
				.sendKeys(Configurator.readData("trade_comp2_cont"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_email2']"))
				.sendKeys(Configurator.readData("trade_comp2_email"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_city2']"))
				.sendKeys(Configurator.readData("trade_comp2_city"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_state2']"))
				.sendKeys(Configurator.readData("trade_comp2_state"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_zip_code2']"))
				.sendKeys(Configurator.readData("trade_comp2_zip"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_address2']"))
				.sendKeys(Configurator.readData("trade_comp2_adrs"));
		driver.findElement(By.xpath("//*[@name = 'contact_name2']"))
				.sendKeys("trade ref contact name 2");
		driver.findElement(By.xpath("//*[@name = 'name_of_company3']"))
				.sendKeys(Configurator.readData("trade_comp3_name"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_contact3']"))
				.sendKeys(Configurator.readData("trade_comp3_cont"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_email3']"))
				.sendKeys(Configurator.readData("trade_comp3_email"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_city3']"))
				.sendKeys(Configurator.readData("trade_comp3_city"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_state3']"))
				.sendKeys(Configurator.readData("trade_comp3_state"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_zip_code3']"))
				.sendKeys(Configurator.readData("trade_comp3_zip"));
		driver.findElement(By.xpath("//*[@name = 'trade_ref_address3']"))
				.sendKeys(Configurator.readData("trade_comp3_adrs"));
		driver.findElement(By.xpath("//*[@name = 'contact_name3']"))
				.sendKeys("trade ref contact name 3");
		// Bank references
		driver.findElement(By.xpath("//*[@name = 'name_of_bank']"))
				.sendKeys(Configurator.readData("bank_name"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_contact']"))
				.sendKeys(Configurator.readData("bank_cont"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_email']"))
				.sendKeys(Configurator.readData("bank_email"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_street_address']"))
				.sendKeys(Configurator.readData("bank_strs_adrs"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_phone']"))
				.sendKeys(Configurator.readData("bank_phone"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_fax']")).sendKeys(Configurator.readData("bank_fax"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_city']"))
				.sendKeys(Configurator.readData("bank_city"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_state']"))
				.sendKeys(Configurator.readData("bank_state"));
		driver.findElement(By.xpath("//*[@name = 'bank_ref_zip_code']"))
				.sendKeys(Configurator.readData("bank_zip"));
		driver.findElement(By.xpath("//*[@name = 'is_authorization']")).click();
		driver.findElement(By.xpath("//*[@name = 'authorization_name']"))
				.sendKeys(Configurator.readData("name"));
		// act.contextClick().perform();
		act.sendKeys(Keys.F12).perform();
		// Thread.sleep(1000);
		// act.sendKeys(Keys.ARROW_UP).build().perform();
		// Thread.sleep(1000);
		// act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[text() = 'Request']")).click();
		boolean res = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(17));
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text() = 'Thanks for your order!']")));
			CustomPage.takesScreenshot("rp_order_summary.png");
			// driver.findElement(By.xpath("//*[contains(@src, 'chevron_left')]")).click();
			Thread.sleep(2000);
			try {
				driver.findElement(By.xpath("//*[text() = 'Convenience Fee']")).isDisplayed();
				System.out.println("Convenience Fee is Displayed for Request for Pay terms also");
			} catch (Exception e) {
				res = true;
			}
		} catch (Exception e) {
			CustomPage.takesScreenshot("req_pay_issue.png");
			driver.findElement(By.xpath("//*[@title = 'close']")).click();
		}
		return res;
	}

	public static boolean verify_total_open_orders_value() throws Exception {
		DecimalFormat decfor = new DecimalFormat("0.00");
		OpenAppln.menu();
		Thread.sleep(1400);
		driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
		Thread.sleep(1300);
		driver.findElement(By.xpath("//*[text() = 'Dashboard']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text() = 'Need Your Attention ']")));
		double total_value = Double.parseDouble(driver
				.findElement(By.xpath(
						"//*[@id=\"root\"]/div/div[2]/div[1]/section/div[3]/div[1]/div[2]"))
				.getText().replace("$", "").replace(",", ""));
		driver.findElement(By.xpath("//*[text() = 'Orders']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'OPEN ORDER']")));
		List<WebElement> orders = driver.findElements(By.xpath("//*[text() = 'OPEN ORDER']"));
		double val = 0.0;
		double total_val = 0.0;
		System.out.println("count of open orders is " + orders.size());
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text() = 'Total Price:']")));
			val = val + Double.parseDouble(
					driver.findElement(By.xpath("//*[contains(@class, 'total-sum-details')]"))
							.findElements(By.tagName("h4")).get(2).getText()
							.replace("$", "").replace(",", ""));
			total_val = Double.parseDouble(decfor.format(val));
			driver.findElement(By.xpath("//*[text() = 'Orders']")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[text() = 'OPEN ORDER']")));
			orders = driver.findElements(By.xpath("//*[text() = 'OPEN ORDER']"));
		}
		boolean res = false;
		if (total_val == total_value) {
			res = true;
		} else {
			res = false;
		}
		System.out.println("Total orders value at dashboard is " + total_value);
		System.out.println("Total orders value at orders is " + total_val);
		return res;
	}
	public static void name() throws Exception {
		OpenAppln.menu();
		Thread.sleep(1400);
		driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
		Thread.sleep(1300);
		driver.findElement(By.xpath("//*[text() = 'Dashboard']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[text() = 'Need Your Attention ']")));
		try {
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div[3]"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static boolean create_sales_order_from_store() throws Exception {
		 OpenAppln.open_app();
		// OpenAppln.login();
		driver.navigate().to(
				"https://staging-store.iidm.com/yaskawa-m-2/p1b2d007-p1000-bypass-nema-12-208v-7-5a-p-854221");
		driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(1).click();
		Thread.sleep(1500);
		OpenAppln.menu();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class = 'cart-icon']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Checkout']")));
		driver.findElement(By.xpath("//*[text() = 'Checkout']")).click();
		CustomPage.checkout("true");
		CustomPage.credit_card_or_net30("NET 30");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[contains(@src, 'chevron_left')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Approved')]")));
		String store_quote_url = driver.getCurrentUrl();
		String quote_id = store_quote_url.substring(47);
		String quote_id_buzz = "https://www.staging-buzzworld.iidm.com/all_quotes/";
		driver.switchTo().newWindow(WindowType.WINDOW).navigate().to(quote_id_buzz + quote_id);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys("defaultuser@enterpi.com");
		driver.findElement(By.id("password")).sendKeys("Enter@4321");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[3]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Create Sales Order']")));
		driver.findElement(By.xpath("//*[text() = 'Create Sales Order']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Enter PO Number']")));
		driver.findElement(By.xpath("//*[@placeholder = 'Enter PO Number']")).sendKeys("TestPO#9872");
		driver.findElement(By.xpath("//*[text() = 'Select Shipping Instructions']")).click();
		Actions act = new Actions(driver);
		act.sendKeys("U").perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'UPS GRD DIRECT']")));
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[4]/div/div[1]/div/div[3]/div/button/span"))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Sales Order Information']")));
		String order_id = driver.findElement(By.xpath("//*[@class = 'id-num']")).getText().replace("#", "");
		List<WebElement> items = driver.findElements(By.xpath("//*[@style = 'padding: 16px;']"));
		double count = 0.0;
		for (int i = 0; i < items.size(); i++) {
			count = count + Double.parseDouble(driver
					.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div/div/div[1]/div[1]/div[2]/div[2]/div[" + i
							+ 1 + "]/div/div[2]/div[1]/div[6]/div"))
					.getText().replace("$", "").replace(",", ""));

		}
		String buz_ord_val = String.valueOf(count);
		Set<String> ids = driver.getWindowHandles();
		Object[] id = ids.toArray();
		String store_id = id[0].toString();
		String buzz_id = id[1].toString();
		driver.switchTo().window(store_id).navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = '" + order_id + "']")));
		driver.findElement(By.xpath("//*[text() = '" + order_id + "']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Orders Information']")));
		String store_ord_val = driver.findElement(By.xpath("//*[contains(@class, 'total-sum-details')]"))
				.findElements(By.tagName("h4")).get(2).getText().replace("$", "").replace(",", "").replace(" ", "");
		boolean res = false;
		if (store_ord_val.equals(buz_ord_val)) {
			res = true;
		} else {
			res = false;
		}
		System.out.println("Store order value is " + store_ord_val);
		System.out.println("Buzzworld order value is " + buz_ord_val);
		return res;
	}
	public static boolean use_billing_as_shipping() throws Exception {
		
		String comp_name = Configurator.readData("comp_name");
		String f_name = Configurator.readData("first_name");
		String l_name = Configurator.readData("last_name");
		String mail = Configurator.readData("email");
		String adrs1_bill = Configurator.readData("adrs1_billing");
		String city_bill = Configurator.readData("city_billing");
		String state_bill = Configurator.readData("state_billing");
		String postal_bill = Configurator.readData("postal_billing");
		
		String phone = Configurator.readData("phone");
		Actions act = new Actions(driver); boolean res = false;
		// Company information
		Thread.sleep(1000);
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys(comp_name).perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		act.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@placeholder='Enter First Name']")).sendKeys(f_name);
		
		act.moveToElement(driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']"))).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@placeholder='Enter Last Name']")).sendKeys(l_name);
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Phone Number']")).sendKeys(phone);
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Email ID']")).sendKeys(mail);
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		// Billing information
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).sendKeys(adrs1_bill);
		driver.findElement(By.xpath("//*[@placeholder='Enter City']")).sendKeys(city_bill);
		driver.findElement(By.xpath("//*[text() = 'Select State']")).click();
		act.sendKeys(state_bill).perform();
		act.sendKeys(Keys.ENTER).perform();
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys(postal_bill).perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		Thread.sleep(1000);
		act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[@name = 'checkbox']")).click();
		driver.findElement(By.xpath("//*[text() = 'Shipping Information']")).click();
		String adrs1_ship = driver.findElement(By.xpath("//*[@placeholder='Enter Address1']")).getAttribute("value");
		String city_ship = driver.findElement(By.xpath("//*[@placeholder='Enter City']")).getAttribute("value");
		String state_ship = driver.findElements(By.xpath("//*[contains(@class, 'react-select__single-value')]")).get(3).getText();
		String postal_ship = driver.findElements(By.xpath("//*[contains(@class, 'react-select__single-value')]")).get(4).getText();
		System.out.println(adrs1_bill);System.out.println(adrs1_ship);
		System.out.println(city_bill);System.out.println(city_ship);
		System.out.println(state_bill);System.out.println(state_ship);
		System.out.println(postal_bill);System.out.println(postal_ship);
		
		if (adrs1_ship.equals(adrs1_bill) && city_ship.equals(city_bill) && state_ship.equals(state_bill) && postal_ship.equals(postal_bill)) {
			res = true;
		} else {
			res = false;
		}
		return res;
	}
	public static boolean submit_repair(boolean is_login) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		CustomPage.menu();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Submit Repair']")));
		driver.findElement(By.xpath("//*[text() = 'Submit Repair']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Manufacturer']")));
		driver.findElements(By.xpath("//*[@name = 'search']")).get(1).sendKeys("000-0735-03");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@onClick, 'addProduct')]")));
		driver.findElement(By.xpath("//*[contains(@onClick, 'addProduct')]")).click();
		Actions act = new Actions(driver);
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 2000);");
		Thread.sleep(3000);
		// act.moveToElement(driver.findElement(By.xpath("//*[contains(text(),
		// 'Proceed')]"))).perform();
		driver.findElement(By.xpath("//*[contains(text(), 'Proceed')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, 'repairFirstName')]")));
		if (is_login) {
		} else {
			driver.findElement(By.xpath("//*[contains(@name, 'repairFirstName')]"))
					.sendKeys(Configurator.readData("first_name"));
			driver.findElement(By.xpath("//*[contains(@name, 'repairLastName')]"))
					.sendKeys(Configurator.readData("last_name"));
			driver.findElements(By.xpath("//*[text() = 'Search Company']")).get(1).click();
			act.sendKeys("Chump Change Automation").perform();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text() = 'Searching…']")));
			act.sendKeys(Keys.ENTER).perform();
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormEmail')]"))
					.sendKeys(Configurator.readData("email"));
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormContactNo')]")).sendKeys("8896587458");
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormAddress')]")).sendKeys("Test Address");
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormCity')]")).sendKeys("Columbia");
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormState')]")).sendKeys("District");
			act.sendKeys(Keys.ENTER).perform();
			driver.findElement(By.xpath("//*[contains(@name, 'repairFormZip')]")).sendKeys("77705");

		}
		boolean res = false;
		js.executeScript("window.scrollBy(0, 2000);");
		Thread.sleep(1200);
		driver.findElement(By.xpath("//*[@onclick = 'SubmitFormData();']")).click();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Download PDF']")));
			String repair_id = driver.findElement(By.xpath("//*[contains(@class, 'text-primary')]")).getText()
					.replace("#", "");
			System.out.println("Repair created with repair id " + repair_id);
			res = true;
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

	public static boolean repairs_search(String search_type, String search_val) throws Exception {
		boolean res = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		if (search_type.equals("RMA ID")) {
			driver.findElement(By.xpath("//*[contains(@class, 'custom-dropdown-trigger')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[contains(text(), 'Dashboard')]")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Need Your Attention')]")));
			driver.findElement(By.xpath("//*[contains(text(), 'Repairs')]")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'ag-react-container')]")));
			try {
				driver.findElement(By.xpath(
						"//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']"))
						.isDisplayed();
				driver.findElement(By.xpath(
						"//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']"))
						.click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'ag-react-container')]")));
			} catch (Exception e) {

			}

		} else {
			try {
				driver.findElement(By.xpath(
						"//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']"))
						.isDisplayed();
				driver.findElement(By.xpath(
						"//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']"))
						.click();
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'ag-react-container')]")));
			} catch (Exception e) {

			}
		}
		WebElement placeholder = driver
				.findElement(By.xpath("//*[contains(@placeholder, 'RMA ID / Item / PO Number')]"));
		placeholder.sendKeys(search_val);
		try {
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + search_val + "')]")));
			String act_text = driver.findElement(By.xpath("//*[contains(text(), '" + search_val + "')]")).getText();
			System.out.println("actual text is " + act_text);
			System.out.println("expected text is " + search_val);
			res = true;
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

	public static boolean request_for_quote_displayed_or_not_without_login(boolean is_login) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Thread.sleep(1500);
		boolean res = false;
		boolean is_check = false;
		Actions act = new Actions(driver);
		if (is_login) {
			// if it is login
			OpenAppln.login();
		} else {

		}		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@class = 'iidm-manufacturers-logos-wrapper']")));
		Thread.sleep(1000);
		for (int i = 0; i < 2; i++) {
			if (i == 1) {
				// verify request for quote single item which is not having price
				driver.navigate().to("https://staging-store.iidm.com/376834-4t-p-56671");
				wait.until(ExpectedConditions
						.visibilityOf(driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(1)));
				Thread.sleep(1000);
				driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(1).click();
				Thread.sleep(1500);
				is_check = true;
			} else {
				// verify request for quote two or more items those are having and not having
				// prices
				for (int s = 1; s < 3; s++) {
					act.moveToElement(
							driver.findElements(By.xpath("//*[@class = 'product-thumb image-top']")).get(1 + s))
							.perform();
					Thread.sleep(1500);
					act.click(driver.findElements(By.xpath("//*[contains(@class, 'shopping-cart')]")).get(s)).perform();
					Thread.sleep(1500);
					is_check = true;
				}
			}

			if (is_check) {

				driver.navigate().to("https://staging-store.iidm.com/index.php?route=checkout/cart");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Checkout']")));
				driver.findElement(By.xpath("//*[text() = 'Checkout']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='customer_name']")));
				if (is_login) {
					WebElement phone = driver.findElement(By.xpath("//*[@name = 'phone']"));
					if (phone.getAttribute("value") == "") {
						phone.sendKeys("8978654327");
					} else {

					}
					driver.findElement(By.xpath("//*[text() = 'Next']")).click(); Thread.sleep(1000);
					driver.findElement(By.xpath("//*[text() = 'Next']")).click(); Thread.sleep(1000);
					//driver.findElement(By.xpath("//*[text() = 'Next']")).click(); Thread.sleep(1000);
					// Notes
					driver.findElement(By.xpath("//*[@name = 'notes']")).sendKeys("Notes are Enter here with login");
				} else {
					CustomPage.company_details();
					// Notes
					driver.findElement(By.xpath("//*[@name = 'notes']")).sendKeys("Notes are Enter here without login");
				}
				
				try {
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[text() = 'Request Quote For Price']")));
					driver.findElement(By.xpath("//*[text() = 'Request Quote For Price']")).isDisplayed();
					driver.findElement(By.xpath("//*[text() = 'Request Quote For Price']")).click();
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Quote Number')]")));
					res = true;
				} catch (Exception e) {
					res = false;
				}
				if (i == 0) {
					driver.navigate().to("https://staging-store.iidm.com/");
					Thread.sleep(2000);
				} else {

				}
			} else {
				System.out.println("No item is added to cart.! and value of i is " + i);
				res = false;
			}
		}
		return res;
	}

	public static boolean approve_quote(boolean is_approve) throws Exception {
		CustomPage.login();
		CustomPage.menu();
		driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
		Thread.sleep(1300);
		driver.findElement(By.xpath("//*[contains(text(), 'Dashboard')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean res = false;
		boolean status = false;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(22));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'ag-react-container']")));
			res = true;
		} catch (Exception e) {
			System.out.println("No Pending Approval Quotes are there in Dashboard.!");
		}
		if (res) {
			driver.findElement(By.xpath("//*[@class = 'ag-react-container']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Reject']")));
			Thread.sleep(3000);
			if (is_approve) {
				driver.findElements(By.xpath("//*[text() = 'Approve']")).get(0).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='customer_name']")));
				WebElement phone = driver.findElement(By.xpath("//*[@name = 'phone']"));
				if (phone.getAttribute("value") == "") {
					phone.sendKeys("8978654327");
				} else {

				}
				driver.findElement(By.xpath("//*[text() = 'Next']")).click();
				driver.findElement(By.xpath("//*[text() = 'Next']")).click();
				driver.findElement(By.xpath("//*[text() = 'Next']")).click();
				// Notes
				driver.findElement(By.xpath("//*[@name = 'notes']"))
						.sendKeys("Notes are Enter here with login from Approve");
				driver.findElement(By.xpath("//*[text() = 'Proceed']")).click();
				try {

					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath("//*[@placeholder='Enter Name on the Card']")));
					driver.findElement(By.xpath("//*[@placeholder='Enter Name on the Card']"))
							.sendKeys("jhon test");
					driver.findElement(By.xpath("//*[@placeholder='Enter Card Number']"))
							.sendKeys("4111 1111 1111 1111");
					driver.findElement(By.xpath("//*[@placeholder='MM / YY']")).sendKeys("1225");
					driver.findElement(By.xpath("//*[@placeholder='Enter CVC']")).sendKeys("111");

					driver.findElement(By.xpath("//*[text()='Proceed To Payment']")).click();
				} catch (Exception e) {
					driver.findElement(By.xpath("//*[@placeholder='Enter PO Number']"))
							.sendKeys("jhon test");
					driver.findElement(By.xpath("//*[text() = 'Approve']")).click();
				}
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[text() = 'Thanks for your order!']")));
				status = true;
			} else {
				driver.findElement(By.xpath("//*[text() = 'Reject']")).click();
				Thread.sleep(1200);
				driver.findElement(By.xpath("//*[text() = 'Proceed']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Rejected']")));
				status = true;
			}
		} else {
			status = false;
		}
		return status;
	}

	public static void registration() throws Exception {
		//OpenAppln.open_app();
		OpenAppln.menu();
		try {
			driver.findElement(By.xpath("//*[text() = 'Login']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = 'Login']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
			Thread.sleep(1300);
			driver.findElement(By.xpath("//*[contains(text(), 'Logout')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Login']")));
			driver.findElement(By.xpath("//*[text() = 'Login']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = 'Login']")).click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'register')]")));
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'register')]"))).perform();
		driver.findElement(By.xpath("//*[contains(@class, 'register')]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, 'first_name')]")));
		// customer
		act.sendKeys(Keys.TAB).perform();
		act.sendKeys("Chump Change Automation").perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter First Name')]"))
				.sendKeys(Configurator.readData("first_name"));
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter Last Name')]"))
				.sendKeys(Configurator.readData("last_name"));
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter Email ID')]"))
				.sendKeys(Configurator.readData("email"));
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter Phone Number')]"))
				.sendKeys(Configurator.readData("phone"));
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter Address1')]")).sendKeys("Test Adrs1");
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Enter City')]"))
				.sendKeys(Configurator.readData("city_billing"));
		driver.findElement(By.xpath("//*[contains(text(), 'Select State')]")).click();
		act.sendKeys(Configurator.readData("state_shipping")).perform();
		act.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.xpath("//*[contains(text(), 'Search By Postal Code')]")).click();
		act.sendKeys(Configurator.readData("postal_shipping")).perform();
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[contains(@class, 'loadingIndicator')]")));
		act.sendKeys(Keys.ENTER).perform();
		System.exit(0);

	}
	
	public static void takesScreenshot(String file_name) throws Exception {
		File src_file = driver.findElement(By.tagName("body")).getScreenshotAs(OutputType.FILE);
		File dest_file = new File(file_name);
		FileHandler.copy(src_file, dest_file);
	}
}
